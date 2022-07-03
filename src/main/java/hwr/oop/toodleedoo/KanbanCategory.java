package hwr.oop.toodleedoo;

import java.util.ArrayList;
import java.util.List;

public class KanbanCategory implements TaskListOrganizer {
    private List<Task> kanbanCategory;
    private Sorting sorting = new Sorting();

    public KanbanCategory() {
        this.kanbanCategory = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return kanbanCategory.isEmpty();
    }

    @Override
    public void add(Task task) {
        kanbanCategory.add(sorting.getIndexToSortIn(kanbanCategory, task), task);
    }

    @Override
    public void delete(Task task) {
        kanbanCategory.remove(task);
    }

    @Override
    public int getLength() {
        return kanbanCategory.size();
    }

    @Override
    public Task getTaskAtIndex(int index) {
        return kanbanCategory.get(index);
    }
}
