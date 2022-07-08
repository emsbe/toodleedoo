package hwr.oop.toodleedoo;

import java.util.*;

public class GeneralTaskList implements TaskManager, Sorting {
    private List<Task> taskList;

    public GeneralTaskList() {
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
    @Override
    public void sortBy(String filter) throws IllegalArgumentException {
        if (Objects.equals(filter, "deadline") || Objects.equals(filter, "date")) {
            Collections.sort(taskList, new Comparator<Task>() {
                @Override
                public int compare(Task firstTask, Task secondTask) {
                    if(Objects.equals(filter, "deadline")) {
                        return firstTask.getDeadline().compareTo(secondTask.getDeadline());
                    } else if(Objects.equals(filter, "date")) {
                        return firstTask.getDate().compareTo(secondTask.getDate());
                    }
                    return 0;
                }
            });
        } else {
            throw new IllegalArgumentException("Error. Please enter deadline or date as a filter.");
        }
    }

 */
    @Override
    public void sortBy(String filter) throws IllegalArgumentException {
        if (filter == "deadline") {
            Collections.sort(taskList, new Comparator<Task>() {
                @Override
                public int compare(Task firstTask, Task secondTask) {
                    return firstTask.getDeadline().compareTo(secondTask.getDeadline());
                }
            });
        } else if (filter == "date") {
            Collections.sort(taskList, new Comparator<Task>() {
                @Override
                public int compare(Task firstTask, Task secondTask) {
                    return firstTask.getDate().compareTo(secondTask.getDate());
                }
            });
        } else throw new IllegalArgumentException("Error. Please enter deadline or date as a filter.");
    }

    @Override
    public int getIndexOf(Task task) {
        int index = taskList.indexOf(task);
        if (index == -1) {
            throw new AssertionError("The task does not exist in the task list.");
        }
        return index;
    }

    @Override
    public Task changeTask(int index, Task newTask) {
        return taskList.set(index, newTask);
    }

    @Override
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
