package hwr.oop.toodleedoo;

import java.util.List;

public interface TaskManager extends TaskList {
    void sortBy(String filter) throws IllegalArgumentException;

    int getIndexOf(Task task);

    Task changeTask(int index, Task task);

    List<Task> getTaskWithName(String taskName);
}
