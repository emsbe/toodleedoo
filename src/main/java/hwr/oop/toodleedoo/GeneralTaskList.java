package hwr.oop.toodleedoo;

import java.util.*;

public class TaskManager implements TaskList, Sorting {
    private List<Task> taskList;
    private Collection<Task> completedTasks;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public void add(Task task) {
        int index = getIndexToSortIn(taskList, task);
        taskList.add(index, task);
    }

    @Override
    public void delete(Task task) {
        taskList.remove(task);
    }

    @Override
    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    @Override
    public int getLength() {
        return taskList.size();
    }
/*
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

 */

    public void sortBy(String filter) throws IllegalArgumentException {
        if (Objects.equals(filter, "deadline") || Objects.equals(filter, "date")) {
            Collections.sort(taskList, new Comparator<Task>() {
                @Override
                public int compare(Task firstTask, Task secondTask) {
                    if(Objects.equals(filter, "deadline")) {
                        return firstTask.getDeadline().compareTo(secondTask.getDeadline());
                    } else if(Objects.equals(filter, "date")) {
                        return firstTask.getDate().compareTo(secondTask.getDate());
                    } else return 0;
                }
            });
        } else {
            throw new IllegalArgumentException("Error. Please enter deadline or date as a filter.");
        }
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

    public List<Task> getTaskWithName(String taskName) {
        List<Task> requestedTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (Objects.equals(task.getTaskName(), taskName)) {
                requestedTasks.add(task);
            }
        }
        return requestedTasks;
    }


}
