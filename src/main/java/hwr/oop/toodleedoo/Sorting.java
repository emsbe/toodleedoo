package hwr.oop.toodleedoo;

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
}
