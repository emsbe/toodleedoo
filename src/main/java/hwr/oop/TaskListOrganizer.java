package hwr.oop;

public interface TaskListOrganizer {
    boolean isEmpty();

    void add(Task task);

    void delete(Task task);

    int getLength();

    Task getTaskAtIndex(int index);

}
