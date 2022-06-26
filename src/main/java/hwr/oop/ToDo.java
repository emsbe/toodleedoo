package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class ToDo implements TaskListOrganizer {
    private List<Task> toDo;
    private Sorting sorting = new Sorting();

    public ToDo() {
        this.toDo = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return toDo.isEmpty();
    }

    @Override
    public void add(Task task) {
        toDo.add(sorting.getIndexToSortIn(toDo, task), task);
    }

    @Override
    public void delete(Task task) {
        toDo.remove(task);
    }

    @Override
    public int getLength() {
        return toDo.size();
    }

    @Override
    public Task getTaskAtIndex(int index) {
        return toDo.get(index);
    }
}
