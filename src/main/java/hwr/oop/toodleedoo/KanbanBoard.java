package hwr.oop.toodleedoo;

import java.util.NoSuchElementException;
import java.util.Objects;

public class KanbanBoard {
    private TaskList toDo;
    private TaskList doing;
    private TaskList done;

    public KanbanBoard() {
        this.toDo = new KanbanCategory();
        this.doing = new KanbanCategory();
        this.done = new KanbanCategory();
    }

    public void loadToBoard(TaskList taskList, String kanbanCategory) {
        if (Objects.equals(kanbanCategory, "to do")) {
            this.toDo = taskList;
        } else if (Objects.equals(kanbanCategory, "doing")) {
            this.doing = taskList;
        } else if (Objects.equals(kanbanCategory, "done")) {
            this.done = taskList;
        }
    }

    public void addToBoard(String kanbanLabel, Task task) {
        getListToMatch(kanbanLabel).add(task);
    }

    public void deleteFromBoard(String kanbanLabel, Task task) {
        getListToMatch(kanbanLabel).delete(task);
    }

    private TaskList getListToMatch(String label) {
        if (Objects.equals(label, "to do")) {
            return toDo;
        } else if(Objects.equals(label, "doing")) {
            return doing;
        } else if (Objects.equals(label, "done")){
            return done;
        } else throw new IllegalArgumentException("Error. Please enter to do, doing or done.");
    }

    public void move(Task task, String newKanbanLabel) {
        String oldKanbanLabel = getKanbanCategoryOf(task);
        getListToMatch(oldKanbanLabel).delete(task);
        getListToMatch(newKanbanLabel).add(task);
    }

    public TaskList getToDo() {
        return toDo;
    }

    public TaskList getDoing() {
        return doing;
    }

    public TaskList getDone() {
        return done;
    }

    public void show() {
        int maxLength = Math.max(Math.max(toDo.getLength(), doing.getLength()), done.getLength());
        System.out.format("| %-20s | %-20s | %-20s |%n", "To Do", "Doing", "Done");
        System.out.println("----------------------------------------------------------------------");
        for (int index = 0; index < maxLength; index++) {
            outputTasksInColumns(index, maxLength);
        }
        System.out.println("----------------------------------------------------------------------");
    }

    private void outputTasksInColumns(int index, int maxLength) {
        try {
            System.out.format("| %-20s | %-20s | %-20s |%n", toDo.getTaskAtIndex(index).getTaskName(), doing.getTaskAtIndex(index).getTaskName(), done.getTaskAtIndex(index).getTaskName());
        } catch (IndexOutOfBoundsException e) {
            String forToDo = getStringForOutput(toDo, maxLength, index);
            String forDoing = getStringForOutput(doing, maxLength, index);
            String forDone = getStringForOutput(done, maxLength, index);
            System.out.format("| %-20s | %-20s | %-20s |%n", forToDo, forDoing, forDone);
        }
    }

    private String getStringForOutput(TaskList taskList, int maxLength, int index) {
        if (taskList.getLength() < maxLength) {
            return "";
        } else return taskList.getTaskAtIndex(index).getTaskName();
    }

    public String getKanbanCategoryOf(Task task) {
        if (loopThroughCategory(toDo, task)) {
            return "to do";
        } else if (loopThroughCategory(doing, task)) {
            return "doing";
        } else if (loopThroughCategory(done, task)) {
            return "done";
        } else throw new NoSuchElementException("Error. Task cannot be found in kanban board.");
    }

    private boolean loopThroughCategory(TaskList taskList, Task task) {
        for (int i = 0; i < taskList.getLength(); i++) {
            if (taskList.getTaskAtIndex(i) == task) {
                return true;
            }
        }
        return false;
    }

}
