package hwr.oop.toodleedoo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FileSaving {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void saveToFile(TaskList taskList, String listType) throws IOException {
        if (Objects.equals(listType, "taskList") || Objects.equals(listType, "toDo") || Objects.equals(listType, "doing") || Objects.equals(listType, "done")) {
            try {
                File file = new File("src/test/java/hwr/oop/toodleedoo/resources/" + listType + ".txt");
                PrintWriter pw = new PrintWriter(file);
                for (int index = 0; index < taskList.getLength(); index++) {
                    if (index < taskList.getLength()-1) {
                        pw.write(taskToString(taskList.getTaskAtIndex(index)) + String.format("%n"));
                    } else pw.write(taskToString(taskList.getTaskAtIndex(index)));
                }
                pw.flush();
                pw.close();
            } catch (IOException e) {
                System.out.println("Your task list "+listType+" could not be saved. Try again.");
            }
            System.out.println(listType+" has been successfully saved.");
        } else {
            System.out.println("Invalid list type.");
        }


        /*
        try (ObjectOutputStream out = new ObjectOutputStream((new FileOutputStream("src/test/java/hwr/oop/resources/"+listType+".txt")))) {
            for (int index = 0; index < taskList.getLength(); index++) {
                out.writeObject(taskToString(taskList.getTaskAtIndex(index))+String.format("%n"));
            }
            System.out.println("Save successful.");
        } catch (IOException e) {
            System.out.println("File to write the Task list in couldn't be found. Try again.");
        }

         */
    }

    private String taskToString(Task task) {
        return String.format("%s;%s;%s", task.getTaskName(), task.getDate().toString(), task.getDeadline().toString());
    }
}
