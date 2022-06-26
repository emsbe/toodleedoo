package hwr.oop;

import java.util.Objects;

public class KanbanBoard {
    TaskListOrganizer toDo;
    TaskListOrganizer doing;
    TaskListOrganizer done;

    public KanbanBoard(TaskListOrganizer toDo, TaskListOrganizer doing, TaskListOrganizer done) {
        this.toDo = toDo;
        this.doing = doing;
        this.done = done;
    }

    public void update(TaskListOrganizer updatedList, String kanbanCategory) {
        if (Objects.equals(kanbanCategory, "toDo")) {
            this.toDo = updatedList;
        } else if(Objects.equals(kanbanCategory, "doing")) {
            this.doing = updatedList;
        } else {
            this.done = updatedList;
        }
    }

    public void update(TaskListOrganizer originalList, TaskListOrganizer updatedList, String oldCategory, String newCategory) {
        update(originalList, oldCategory);
        update(updatedList, newCategory);
    }

    public TaskListOrganizer getToDo() {
        return toDo;
    }

    public TaskListOrganizer getDoing() {
        return doing;
    }

    public TaskListOrganizer getDone() {
        return done;
    }

    public void show() {
        int maxLength = Math.max(Math.max(toDo.getLength(), doing.getLength()), done.getLength());
        System.out.format("| %-20s | %-20s | %-20s |\n", "To Do", "Doing", "Done");
        System.out.println("----------------------------------------------------------------------");
        for (int index = 0; index < maxLength; index++) {
            outputTasksInColumns(index, maxLength);
        }
        System.out.println("----------------------------------------------------------------------");
    }

    private void outputTasksInColumns(int index, int maxLength) {
        try {
            System.out.format("| %-20s | %-20s | %-20s |\n", toDo.getTaskAtIndex(index).getTaskName(), doing.getTaskAtIndex(index).getTaskName(), done.getTaskAtIndex(index).getTaskName());
        } catch (IndexOutOfBoundsException e) {
            String forToDo = getStringForOutput(toDo, maxLength, index);
            String forDoing = getStringForOutput(doing, maxLength, index);
            String forDone = getStringForOutput(done, maxLength, index);
            System.out.format("| %-20s | %-20s | %-20s |\n", forToDo, forDoing, forDone);
        }
    }

    private String getStringForOutput(TaskListOrganizer taskList, int maxLength, int index) {
        if (taskList.getLength() < maxLength) {
            return "";
        } else return taskList.getTaskAtIndex(index).getTaskName();
    }

}
