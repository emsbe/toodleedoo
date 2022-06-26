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

    public TaskListOrganizer getToDo() {
        return toDo;
    }

    public TaskListOrganizer getDoing() {
        return doing;
    }

    public TaskListOrganizer getDone() {
        return done;
    }
}
