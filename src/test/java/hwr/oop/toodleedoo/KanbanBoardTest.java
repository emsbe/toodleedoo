package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class KanbanBoardTest {
    private KanbanBoard kanbanBoard;
    private Task taskCode;
    private Task taskCall;
    private Task taskFood;
    private Task taskGym;
    private FileSaving fileSaving;
    private FileLoading fileLoading;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        taskCode = new Task("code", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        taskCall = new Task("call Mum", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        taskFood = new Task("prepare food", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        taskGym = new Task("go to gym", LocalDate.parse("27.06.2022", formatter), LocalDate.parse("27.06.2022", formatter));

        kanbanBoard = new KanbanBoard();
        fileSaving  = new FileSaving();
        fileLoading = new FileLoading();

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void kanbanBoard_addToBoard_AddATaskToToDo_LengthIs1() {
        kanbanBoard.addToBoard("to do", taskGym);
        assertThat(kanbanBoard.getToDo().getLength()).isEqualTo(1);
    }

    @Test
    void kanbanBoard_addToBoard_AddATaskToDoing_LengthIs1() {
        kanbanBoard.addToBoard("doing", taskGym);
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(1);
    }

    @Test
    void kanbanBoard_addToBoard_AddATaskToDone_LengthIs1() {
        kanbanBoard.addToBoard("done", taskGym);
        assertThat(kanbanBoard.getDone().getLength()).isEqualTo(1);
    }

    @Test
    void kanbanBoard_show_showsThreeColumnsAccordingToKanbanCategories() {
        kanbanBoard.addToBoard("to do", taskCode);
        kanbanBoard.addToBoard("to do", taskGym);
        kanbanBoard.addToBoard("doing", taskCall);
        kanbanBoard.addToBoard("done", taskFood);
        kanbanBoard.addToBoard("done", taskGym);
        String desiredOutput = String.format("| To Do                | Doing                | Done                 |%n" +
                "----------------------------------------------------------------------%n" +
                "| code                 | call Mum             | prepare food         |%n" +
                "| go to gym            |                      | go to gym            |%n" +
                "----------------------------------------------------------------------");
        kanbanBoard.show();
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void kanbanBoard_move_switchesTaskFromDoingToDone() {
        kanbanBoard.addToBoard("doing", taskFood);
        kanbanBoard.move(taskFood, "done");
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(0);
        assertThat(kanbanBoard.getDone().getLength()).isEqualTo(1);
    }

    @Test
    void kanbanBoard_getKanbanCategoryOf_ReturnsCorrectKanbanLabel() {
        kanbanBoard.addToBoard("to do", taskCode);
        String label = kanbanBoard.getKanbanCategoryOf(taskCode);
        assertThat(label).isEqualTo("to do");
    }

    @Test
    void kanbanBoard_loadToBoard_() throws IOException {
        TaskListOrganizer toDo = new KanbanCategory();
        toDo.add(taskFood);
        fileSaving.saveToFile(toDo, "toDo");
        kanbanBoard.loadToBoard(fileLoading.loadFile("toDo"), "to do");
        assertThat(kanbanBoard.getToDo().getLength()).isEqualTo(1);
    } // TODO: mehr Tests

}
