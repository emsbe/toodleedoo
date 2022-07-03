package hwr.oop;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Sorting {

    public int getIndexToSortIn(List<Task> taskList, Task task) {
        int length = taskList.size();
        for (int index = 0; index < length; index++) {
            if (isTaskEarlierOrEqualToTaskAtIndex(taskList, task, index)) {
                return index;
            }
        }
        //insert at end of array list (or into empty array list)
        return length;
    }

    private boolean isTaskEarlierOrEqualToTaskAtIndex(List<Task> taskList, Task task, int index) {
        return task.compareTo(taskList.get(index)) <= 0;
    }

    public List<Task> sortBy(List<Task> taskList, String filter) {
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
        return taskList;
    } // TODO: Das muss man auch noch testen, ist derzeit noch in TaskList und damit in TaskListTest getestet....

}
