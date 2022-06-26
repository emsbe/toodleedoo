package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class KanbanBoardTest {
    private KanbanBoard kanbanBoard;
    private TaskListOrganizer toDo;
    private TaskListOrganizer doing;
    private TaskListOrganizer done;
    Task taskCode;
    Task taskCall;
    Task taskFood;
    Task taskGym;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeEach
    void setUp() {
        toDo = new ToDo();
        doing = new Doing();
        done = new Done();

        taskCode = new Task("code", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        taskCall = new Task("call Mum", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        taskFood = new Task("prepare food", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        taskGym = new Task("go to gym", LocalDate.parse("27.06.2022", formatter), LocalDate.parse("27.06.2022", formatter));


        toDo.add(taskCode);
        doing.add(taskCall);
        done.add(taskFood);

        kanbanBoard = new KanbanBoard(toDo, doing, done);
    }

    @Test
    void kanbanBoard_update_getToDo_addAnotherToDoAndUpdateKanbanBoard() {
        toDo.add(taskGym);
        kanbanBoard.update(toDo, "toDo");
        assertThat(kanbanBoard.getToDo().getLength()).isEqualTo(2);
    }

    @Test
    void kanbanBoard_update_getDoing_addAnotherDoingAndUpdateKanbanBoard() {
        doing.add(taskGym);
        kanbanBoard.update(doing, "doing");
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(2);
    }

    @Test
    void kanbanBoard_update_getDone_addAnotherDoneAndUpdateKanbanBoard() {
        done.add(taskGym);
        kanbanBoard.update(done, "done");
        assertThat(kanbanBoard.getDone().getLength()).isEqualTo(2);
    }






    /*
    Interface: KanbanCategory
    Methods in KanbanCategory: add() - adds task to category, sortByLabel()
    Three classes that implement KanbanCategory
    Class KanbanBoard: displays Kanban Categories in three columns


    This is very repetitive. In the end, there are multiple array lists that contain the same tasks.
    Doing it in separate classes requires us to also outsource the sorting algorithm. Because the tasks in each
    category array list has to be sorted by date as well. Or doesn't it have to be sorted?
    The problem with having it all in different classes: If a task is deleted or changed in the task list, it also has to be
    deleted or changed in the kanban-category-list.
    It seems to make more sense to introduce the kanban-label into the class Task and use the sortBy-methode in taskList
    to sort by kanban-category-label.
    The class KanbanBoard is used to display the board.


    WICHTIG: Interface TaskOrganizer in TaskList umbenennen, damit das der höchste Datentyp ist. Dann klappt das mit
    Sorting auch für die Kanban-Kategorien!!!

    ODER:
    Task bekommt zusätzliches Attribut namens kanbanCategory (vielleicht auch noch direkt das Label mitmachen).
    Die Tasks sind ja dann in Task List bereits sortiert nach Datum. Wenn man jetzt Listen für jedes Kanban-Label haben möchte,
    muss man nur einmal durch die TaskList durchgehen und je nach Label der Liste hinzufügen. Aber was macht man dann,
    wenn ein neuer Task hinzugefügt wird? Ahhhhhhhhhhh.....
    Observer-Pattern? Haben aber keine richtige 1:n-Abhängigkeit...

     */

}
