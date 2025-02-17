package hwr.oop.toodleedoo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FileSaving {

    public void saveToFile(TaskList taskList, String listType) throws FileNotFoundException {
        if (Objects.equals(listType, "taskList") || Objects.equals(listType, "toDo") || Objects.equals(listType, "doing") || Objects.equals(listType, "done")) {
            File file = new File("src/test/java/hwr/oop/toodleedoo/resources/" + listType + ".txt");
            PrintWriter pw = new PrintWriter(file);
            for (int index = 0; index < taskList.getLength(); index++) {
                if (index < taskList.getLength()-1) {
                    pw.write(taskToString(taskList.getTaskAtIndex(index)) + String.format("%n"));
                } else pw.write(taskToString(taskList.getTaskAtIndex(index)));
            }
            pw.flush();
            pw.close();
            System.out.println(listType+" has been successfully saved.");
        } else {
            System.out.println("Invalid list type.");
        }
    }

    private String taskToString(Task task) {
        return String.format("%s;%s;%s", task.getTaskName(), task.getDate().toString(), task.getDeadline().toString());
    }
}
