package hwr.oop;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task implements Comparable<Task>{
    private String taskName;
    private LocalDate date;
    private LocalDate deadline;

    public Task(String taskName, LocalDate date, LocalDate deadline) {
        this.taskName = taskName;
        this.date = date;
        this.deadline = deadline;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Task otherTask) {
        return this.getDate().compareTo(otherTask.getDate());
    }
}
