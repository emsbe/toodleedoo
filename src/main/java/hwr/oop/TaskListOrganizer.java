package hwr.oop;

public interface TaskListOrganizer {
    boolean isEmpty();

    void add(Task task);

    void sortBy(String filter);

    void delete(Task task);

}
