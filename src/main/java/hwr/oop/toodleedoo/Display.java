package hwr.oop.toodleedoo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Display {
    private LocalDateTransformer transformDate = new LocalDateTransformer();

    public void showAllTasksIn(TaskManager taskList) {
        int length = taskList.getLength();
        for (int index = 0; index < length; index++) {
            Task task = taskList.getTaskAtIndex(index);
            String output = String.format("%d - %s on %s, Deadline: %s", index+1, task.getTaskName(), transformDate.getFormatDate(task.getDate()), transformDate.getFormatDate(task.getDeadline()));
            System.out.println(output);
        }
    }

    public void showViewOf(TaskManager taskList, String view) {
        TaskManager filteredTaskList = taskList;
        filteredTaskList.sortBy(view);
        for (int index = 0; index < filteredTaskList.getLength(); index++) {
            Task task = taskList.getTaskAtIndex(index);
            String givenDate = "";
            if (Objects.equals(view, "deadline")) {
                givenDate = transformDate.getFormatDate(task.getDeadline());
            } else if (Objects.equals(view, "date")) {
                givenDate = transformDate.getFormatDate(task.getDate());
            }
            System.out.println(String.format("%d - %s: %s for %s", index+1, givenDate, view, task.getTaskName()));
        }
    }

    public void showTaskAt(TaskManager taskList, String date) {
        LocalDate formatDate = transformDate.createLocalDate(date);
        System.out.println(date + ":");
        for (int index = 0; index < taskList.getLength(); index++) {
            Task taskAtIndex = taskList.getTaskAtIndex(index);
            if (taskAtIndex.getDate().compareTo(formatDate) == 0) {
                System.out.println("- "+ taskAtIndex.getTaskName());
            }
        }
    }
}
