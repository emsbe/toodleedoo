package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualTest {

    private Scanner scanner = new Scanner(System.in);
    private InputUse input = new InputUse();
    private ManageInput manageInput = new ManageInput();
    private TaskList taskList = new TaskList();
    private TaskListOrganizer toDo = new ToDo();
    private TaskListOrganizer doing = new Doing();
    private TaskListOrganizer done = new Done();
    private FileLoading fileLoading = new FileLoading();
    private KanbanBoard kanbanBoard;

    /*
    Begrüßung --> "Welcome to toodleedoo <3"
    Tasks für heute anzeigen --> "Deine Tasks für heute sind: "
    "Enter your next command: " (Liste für alle möglichen commands)
    z.B.: addTask, moveTask, deleteTask etc.
    alle commands anzeigen --> type "What can i do?"
     */

    @Test
    //@Disabled("manual test")
    void manualTest() {
        loadAllFiles();
        System.out.println("Welcome to toodleedoo <3 ");
        System.out.println("These are your tasks for today: ");

        System.out.println("Enter your next command: ");
        enterCommand();

    }

    private void loadAllFiles() {
        taskList = fileLoading.loadFile("taskList");
        toDo = fileLoading.loadFile("toDo");
        doing = fileLoading.loadFile("doing");
        done = fileLoading.loadFile("done");
        kanbanBoard = new KanbanBoard(toDo, doing, done);
    }

    private void enterCommand() {
        String command = scanner.nextLine();
        if (Objects.equals(command, "enter task")) {
            enterTask();
        } else if (Objects.equals(command, "delete task")) {
            deleteTask();
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
                toDo.add(task);
                kanbanBoard.update(toDo, "toDo");
            } else if (kanbanLabel.equals("doing")) {
                doing.add(task);
                kanbanBoard.update(doing, "doing");
            } else if (kanbanLabel.equals("done")) {
                done.add(task);
                kanbanBoard.update(done, "done");
            } else {
                System.out.println("Invalid label! ");
                // TODO: Wie schaffen wir hier, dass alles neu gestartet wird?
            }
        }
    }

    private void deleteTask() {
        System.out.println("Which task do you want to delete? ");
        String taskName = scanner.nextLine();

    }

    private void editTask() {
    }

    private void kanban() {
    }

    private void showTasks() {
    }

    private void showAllCommands() {
    }

    private void quit() {
        // save files
    }
}

