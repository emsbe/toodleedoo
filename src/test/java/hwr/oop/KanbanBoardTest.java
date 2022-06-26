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

    @Test
    void kanbanBoard_show_showsThreeColumnsAccordingToKanbanCategories() {
        toDo.add(taskGym);
        done.add(taskGym);
        kanbanBoard.update(toDo, "toDo");
        kanbanBoard.update(done, "done");
        kanbanBoard.show();
        // TODO: Wie kann man das testen?
    }

    @Test
    void kanbanBoard_update_switchesTaskFromDoingToDone_adhocPolymorphism() {
        doing.delete(taskCall);
        done.add(taskCall);
        kanbanBoard.update(doing, done, "doing", "done");
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(0);
        assertThat(kanbanBoard.getDone().getLength()).isEqualTo(2);
    }


}
