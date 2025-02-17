package hwr.oop.toodleedoo;

public interface TaskList {
    boolean isEmpty();

    void add(Task task);

    void delete(Task task);

    int getLength();

    Task getTaskAtIndex(int index);

}
