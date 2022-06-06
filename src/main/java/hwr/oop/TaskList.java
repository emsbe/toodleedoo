package hwr.oop;

import java.util.*;

public class TaskList {
    private final List<Task> taskList;
    private Collection<Task> completedTasks;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void add(Task task) {
        int index = getIndexToSortIn(task);
        taskList.add(index, task);
    }

    private int getIndexToSortIn(Task task) {
        int length = taskList.size();
        for (int index = 0; index < length; index++) {
            if (isTaskEarlierOrEqualToTaskAtIndex(task, index)) {
                return index;
            }
        }
        //insert at end of array list (or into empty array list)
        return length;
    }

    private boolean isTaskEarlierOrEqualToTaskAtIndex(Task task, int index) {
        return task.compareTo(taskList.get(index)) <= 0;
    }

    public void sortBy(String filter) {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                if(Objects.equals(filter, "deadline")) {
                    return task1.getDeadline().compareTo(task2.getDeadline());
                } else if(Objects.equals(filter, "date")) {
                    return task1.getDate().compareTo(task2.getDate());
                }
                return 0;
            }
        });
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    public void markAsDone(Task task) {
        taskList.remove(task);
        this.completedTasks = new ArrayList<>();
        completedTasks.add(task); // TODO: testen
    }

    public int getIndexOf(Task task) {
        int index = taskList.indexOf(task);
        if (index == -1) {
            throw new AssertionError("The task does not exist in the task list.");
        }
        return index;
    }

    public Task changeTask(int index, Task newTask) {
        return taskList.set(index, newTask);
    }

    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    public int getLength() {
        return taskList.size();
    }


}
