package hwr.oop.toodleedoo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;


public class FileLoading {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public <T> T loadFile(String listType) {
        if (Objects.equals(listType, "taskList")) {
            return (T) loadFileToTaskList(listType);
        } else if (Objects.equals(listType, "toDo") || Objects.equals(listType, "doing") || Objects.equals(listType, "done")){
            return (T) loadFileToKanbanTaskList(listType);
        } else {
            throw new IllegalArgumentException("Illegal argument. Enter taskList, toDo, doing or done.");
        }

    }

    private TaskList loadFileToTaskList(String listType) {
        TaskList taskList = new TaskList();
        try {
            File file = new File("src/test/java/hwr/oop/toodleedoo/resources/"+listType+".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task = stringToTask(scanner.nextLine());
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println(listType+" could not be read. Try again.");
        }
        System.out.println("Loading "+listType+" has been successful.");
        return taskList;
    }

    private TaskListOrganizer loadFileToKanbanTaskList(String listType) {
        TaskListOrganizer taskList = new KanbanCategory();
        try {
            File file = new File("src/test/java/hwr/oop/toodleedoo/resources/"+listType+".txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task task = stringToTask(scanner.nextLine());
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println(listType+" could not be read. Try again.");
        }
        System.out.println("Loading "+listType+" has been successful.");
        return taskList;
    }


    private Task stringToTask(String concatString) {
        String[] taskValues = concatString.split(";");
        return new Task(taskValues[0], LocalDate.parse(taskValues[1], formatter), LocalDate.parse(taskValues[2], formatter));
    }


}
