package hwr.oop;

import java.util.*;

public class TaskList implements TaskListOrganizer{
    private final List<Task> taskList;
    private Collection<Task> completedTasks;
    Sorting sorting = new Sorting();

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public void add(Task task) {
        int index = sorting.getIndexToSortIn(taskList, task);
        taskList.add(index, task);
    }

    @Override
    public void delete(Task task) {
        taskList.remove(task);
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

    public void sortBy(String filter) {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task firstTask, Task secondTask) {
                if(Objects.equals(filter, "deadline")) {
                    return firstTask.getDeadline().compareTo(secondTask.getDeadline());
                } else if(Objects.equals(filter, "date")) {
                    return firstTask.getDate().compareTo(secondTask.getDate());
                } else return 0;
                // TODO: Hier vielleicht eine Exception werfen, wenn ein Filter gegeben wird, nach dem nicht gefiltert werden kann?
            }
        });
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

    @Override
    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    @Override
    public int getLength() {
        return taskList.size();
    }


}
