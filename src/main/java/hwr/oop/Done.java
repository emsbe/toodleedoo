package hwr.oop;

import java.util.ArrayList;
import java.util.List;

public class Done implements TaskListOrganizer {
    private List<Task> done;
    private Sorting sorting = new Sorting();

    public Done() {
        this.done = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return done.isEmpty();
    }

    @Override
    public void add(Task task) {
        done.add(sorting.getIndexToSortIn(done, task), task);
    }

    @Override
    public void delete(Task task) {
        done.remove(task);
    }

    @Override
    public int getLength() {
        return done.size();
    }

    @Override
    public Task getTaskAtIndex(int index) {
        return done.get(index);
    }
}
