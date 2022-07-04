package hwr.oop.toodleedoo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Display {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void showAllTasksIn(TaskManager taskList) {
        int length = taskList.getLength();
        for (int index = 0; index < length; index++) {
            Task task = taskList.getTaskAtIndex(index);
            String output = String.format("%d - %s am %s, Deadline: %s", index+1, task.getTaskName(), task.getDate(), task.getDeadline());
            System.out.println(output);
        }
    }

    public void showViewOf(TaskManager taskList, String view) {
        TaskManager filteredTaskList = taskList;
        filteredTaskList.sortBy(view);
        for (int index = 0; index < filteredTaskList.getLength(); index++) {
            Task task = taskList.getTaskAtIndex(index);
            System.out.println(String.format("%d - %s: %s for %s", index+1, task.getDeadline(), view, task.getTaskName()));
        }
    }

    public void showTaskAt(TaskManager taskList, String date) {
        LocalDate formatDate = LocalDate.parse(date, formatter);
        for (int index = 0; index < taskList.getLength(); index++) {
            Task taskAtIndex = taskList.getTaskAtIndex(index);
            if (taskAtIndex.getDate().compareTo(formatDate) == 0) {
                System.out.println(String.format("To Do on %s: %s", date, taskAtIndex.getTaskName()));
            }
        }
    }
}
