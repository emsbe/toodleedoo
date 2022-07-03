package hwr.oop.toodleedoo;

import hwr.oop.toodleedoo.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualTest {

    private Scanner scanner = new Scanner(System.in);
    private InputUse input = new InputUse();
    private ManageInput manageInput = new ManageInput();
    private TaskList taskList = new TaskList();
    private TaskListOrganizer toDo = new KanbanCategory();
    private TaskListOrganizer doing = new KanbanCategory();
    private TaskListOrganizer done = new KanbanCategory();
    private FileLoading fileLoading = new FileLoading();
    private FileSaving fileSaving = new FileSaving();
    private KanbanBoard kanbanBoard;
    private Display display = new Display();

    private boolean continueProcess = true;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /*
    Begrüßung --> "Welcome to toodleedoo <3"
    Tasks für heute anzeigen --> "Deine Tasks für heute sind: "
    "Enter your next command: " (Liste für alle möglichen commands)
    z.B.: addTask, moveTask, deleteTask etc.
    alle commands anzeigen --> type "What can i do?"
     */

    // TODO: haben bisher noch nichts mit Deadlines gemacht: das auch noch anzeigen
    // TODO: Liste
    /*
    -	ManualTest: Funktionalitäten auslagern
-	ManualTest: als ManualTest markieren
-	Alles in Package toodleedoo und Unterpackages packen
-	TaskListOrganizer umbenennen -> z.B. TaskList in TaskManager, TaskListOrganizer in TaskList
-	TaskList.java sortBy() in Sorting aufrufen
-	Refactoring im Allgemeinen
-	Interfaces anwenden?
-	Mehr Tests schreiben
-	UML-Diagramm
-	README

     */

    @Test
    //@Disabled("manual test")
    void manualTest() throws IOException {
        loadAllFiles();
        System.out.println("Welcome to toodleedoo <3 ");
        System.out.println("These are your tasks for today: ");
        display.showTaskAt(taskList, LocalDate.now().format(formatter));
        while (continueProcess) {
            enterCommand();
        }

    }

    private void loadAllFiles() {
        kanbanBoard = new KanbanBoard();
        taskList = fileLoading.loadFile("taskList");
        kanbanBoard.loadToBoard(fileLoading.loadFile("toDo"), "to do");
        kanbanBoard.loadToBoard(fileLoading.loadFile("doing"), "doing");
        kanbanBoard.loadToBoard(fileLoading.loadFile("done"), "done");
    }

    private void enterCommand() throws IOException {
        System.out.println("Enter your next command: ");
        showAllCommands();
        String command = scanner.nextLine();
        if (Objects.equals(command, "enter task")) {
            enterTask();
        } else if (Objects.equals(command, "delete task")) {
            deleteTask();
        } else if (Objects.equals(command, "task done")) {
            taskDone();
        } else if (Objects.equals(command, "edit task")) {
            editTask();
        } else if (Objects.equals(command, "kanban")) {
            kanban();
        } else if (Objects.equals(command, "show tasks")) {
            showTasks();
        } else if (Objects.equals(command, "what can I do?")) {
            showAllCommands();
        } else if (Objects.equals(command, "quit")) {
            quit();
        } else {
            System.out.println("Command not found!");
        }

    }

    private void enterTask() {
        Task task = manageInput.createTask();
        taskList.add(task);

        System.out.println("Do you want to save the task to kanban? (yes/no) ");
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("yes")) {
            System.out.println("Which label: to do, doing, done? ");
            String kanbanLabel = scanner.nextLine().toLowerCase();
            if (kanbanLabel.equals("to do")) {
                kanbanBoard.addToBoard("to do", task);
            } else if (kanbanLabel.equals("doing")) {
                kanbanBoard.addToBoard("doing", task);
            } else if (kanbanLabel.equals("done")) {
                kanbanBoard.addToBoard("done", task);
            } else {
                System.out.println("Invalid label! ");
                // TODO: Wie schaffen wir hier, dass alles neu gestartet wird? (nur hier drin)
            }
        }
    }

    private void deleteTask() {
        System.out.println("Which task do you want to delete? ");
        String taskName = scanner.nextLine();
        List<Task> requestedTasks = taskList.getTaskWithName(taskName);
        if (requestedTasks.size() == 1) {
            taskList.delete(requestedTasks.get(0));
            System.out.println("Task successfully deleted. ");
        } else if (requestedTasks.size() == 0) {
            System.out.println("The task with the name "+taskName+" doesn't exist. ");
        } else {
            System.out.println("Which task do you want to delete? ");
            int indexFromUser = whichTask(requestedTasks);
            if (indexFromUser < requestedTasks.size()) {
                taskList.delete(requestedTasks.get(indexFromUser));
                System.out.println("Task successfully deleted. ");
            } else {
                System.out.println("Task couldn't be deleted. Index out of bounds. ");
            }
        }
    }

    private int whichTask(List<Task> listOfTasks) {
        int length = listOfTasks.size();
        for (int index = 0; index < length; index++) {
            Task taskAtIndex = listOfTasks.get(index);
            System.out.format("%d - %s, date: %s, deadline: %s", index+1, taskAtIndex.getTaskName(), taskAtIndex.getDate().toString(), taskAtIndex.getDeadline().toString());
        }
        return Integer.parseInt(scanner.nextLine());
    }

    private void taskDone() {
    }

    private void editTask() {
        System.out.println("Name the task you want to edit: ");
        String taskToEdit = scanner.nextLine();
        List<Task> requestedTasks = taskList.getTaskWithName(taskToEdit);
        if (requestedTasks.size() == 0) {
            System.out.println("Task doesn't exist in your task list.");
        } else if (requestedTasks.size() > 1) {
            int indexFromUser = whichTask(requestedTasks);
            if (indexFromUser < requestedTasks.size()) {
                taskList.delete(requestedTasks.get(indexFromUser));
                taskList.add(getUpdatedTaskFromUser());
                System.out.println("Your task update was successful. ");
            } else System.out.println("Couldn't edit task. Index out of bounds. ");
        } else {
            taskList.delete(requestedTasks.get(0));
            taskList.add(getUpdatedTaskFromUser());
            System.out.println("Your task update was successful. ");
        }

    }

    private Task getUpdatedTaskFromUser() {
        System.out.println("Please enter the updated information for your task: ");
        return manageInput.createTask();
    }

    private void kanban() {
        System.out.println("What do you want to do with kanban: show or move task ");
        String userAnswer = scanner.nextLine();
        if (Objects.equals(userAnswer, "show")) {
            kanbanBoard.show();
        } else if (Objects.equals(userAnswer, "move task")) {
            System.out.println("Which task do you want to move? ");
            String taskToMove = scanner.nextLine();
            List<Task> requestedTasks = taskList.getTaskWithName(taskToMove);
            if (requestedTasks.size() > 1) {
                int indexFromUser = whichTask(requestedTasks);
                String newKanbanLabel = getLabel();
                kanbanBoard.move(requestedTasks.get(indexFromUser), newKanbanLabel);
                kanbanBoard.show();
            } else if (requestedTasks.size() == 1) {
                String newKanbanLabel = getLabel();
                kanbanBoard.move(requestedTasks.get(0), newKanbanLabel);
                kanbanBoard.show();
            } else System.out.println("The task doesn't exist. Sorry. ");
        } else {
            System.out.println("Invalid command. Please try again. ");
        }
    }

    private String getLabel() {
        System.out.println("To which label do you want to move your task? ");
        return scanner.nextLine();
    }

    private TaskListOrganizer getKanbanCategoryList(String kanbanLabel) {
        if (Objects.equals(kanbanLabel, "to do")) {
            return toDo;
        } else if (Objects.equals(kanbanLabel, "doing")) {
            return doing;
        } else if (Objects.equals(kanbanLabel, "done")) {
            return done;
        } else return null; // TODO: ERROR
    }

    private void showTasks() {
        System.out.println("Which tasks do you want to see? Enter: today, this week, all ");
        String tasksToShow = scanner.nextLine();
        LocalDate today = LocalDate.now();
        if (Objects.equals(tasksToShow, "today")) {
            display.showTaskAt(taskList, today.format(formatter));
        } else if (Objects.equals(tasksToShow, "this week")) {
            List<String> weekDays = new ArrayList<>(List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"));
            int lengthWeek = weekDays.size() -1;
            DayOfWeek currentWeekDay = LocalDate.now().getDayOfWeek();
            int daysInWeekLeft = 0;
            for (int i = 0; i < weekDays.size(); i++) {
                if (Objects.equals(weekDays.get(i), currentWeekDay.toString())) {
                    daysInWeekLeft = lengthWeek - i;
                }
            }
            display.showTaskAt(taskList, today.format(formatter));
            if (daysInWeekLeft != 0) {
                for (int i = 1; i <= daysInWeekLeft; i++) {
                    display.showTaskAt(taskList, today.plusDays(i).format(formatter));
                }
            } else System.out.println("Nothing more to show. The week is already over. ");

        } else if (Objects.equals(tasksToShow, "all")) {
            display.showAllTasksIn(taskList);
        } else {
            System.out.println("Invalid input. Please enter: today, this week, this month, all. ");
        }

    }

    private void showAllCommands() {
        System.out.println("-> enter task, delete task, task done, edit task, kanban, show tasks, what can I do?, quit");
    }

    private void quit() throws IOException {
        fileSaving.saveToFile(taskList, "taskList");
        fileSaving.saveToFile(kanbanBoard.getToDo(), "toDo");
        fileSaving.saveToFile(kanbanBoard.getDoing(), "doing");
        fileSaving.saveToFile(kanbanBoard.getDone(), "done");
        continueProcess = false;
    }
}

