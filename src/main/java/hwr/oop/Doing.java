package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Doing implements TaskListOrganizer {
    private List<Task> doing;
    private Sorting sorting = new Sorting();

    public Doing() {
        this.doing = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return doing.isEmpty();
    }

    @Override
    public void add(Task task) {
        doing.add(sorting.getIndexToSortIn(doing, task), task);
    }

    @Override
    public void delete(Task task) {
        doing.remove(task);
    }

    @Override
    public int getLength() {
        return doing.size();
    }
}
