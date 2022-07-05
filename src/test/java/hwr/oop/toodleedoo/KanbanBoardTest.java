package hwr.oop.toodleedoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

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
    private final LocalDateTransformer transformDate = new LocalDateTransformer();

    @BeforeEach
    void setUp() {
        taskCode = new Task("code", transformDate.createLocalDate("26.06.2022"), transformDate.createLocalDate("01.07.2022"));
        taskCall = new Task("call Mum", transformDate.createLocalDate("26.06.2022"), transformDate.createLocalDate("01.07.2022"));
        taskFood = new Task("prepare food", transformDate.createLocalDate("26.06.2022"), transformDate.createLocalDate("01.07.2022"));
        taskGym = new Task("go to gym", transformDate.createLocalDate("27.06.2022"), transformDate.createLocalDate("27.06.2022"));

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
    void kanbanBoard_getKanbanCategoryOf_ReturnsDoingLabel() {
        kanbanBoard.addToBoard("doing", taskCode);
        String label = kanbanBoard.getKanbanCategoryOf(taskCode);
        assertThat(label).isEqualTo("doing");
    }
    @Test
    void kanbanBoard_getKanbanCategoryOf_ReturnsDoneLabel() {
        kanbanBoard.addToBoard("done", taskCode);
        String label = kanbanBoard.getKanbanCategoryOf(taskCode);
        assertThat(label).isEqualTo("done");
    }

    @Test
    void kanbanBoard_getKanbanCategoryOf_ThrowsIllegalArgumentException() {
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> {
           kanbanBoard.getKanbanCategoryOf(new Task("test", transformDate.createLocalDate("03.07."), transformDate.createLocalDate("03.07.")));
        });
    }

    @Test
    void kanbanBoard_loadToBoard_() throws IOException {
        TaskList toDo = new KanbanCategory();
        toDo.add(taskFood);
        fileSaving.saveToFile(toDo, "toDo");
        kanbanBoard.loadToBoard(fileLoading.loadFile("toDo"), "to do");
        assertThat(kanbanBoard.getToDo().getLength()).isEqualTo(1);
    }

    @Test
    void kanbanBoard_loadToBoard_moveToDoToDoing() throws IOException {
        TaskList doing = new KanbanCategory();
        doing.add(taskCode);
        fileSaving.saveToFile(doing, "doing");
        kanbanBoard.loadToBoard(fileLoading.loadFile("doing"), "doing");
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(1);
    }

   @Test
    void kanbanBoard_loadToBoard_moveToDoToDone() throws IOException {
        TaskList done = new KanbanCategory();
        done.add(taskFood);
        fileSaving.saveToFile(done, "done");
        kanbanBoard.loadToBoard(fileLoading.loadFile("done"), "done");
        assertThat(kanbanBoard.getDone().getLength()).isEqualTo(1);
    }
    // TODO: mehr Tests

    @Test
    void kanbanBoard_addToBoard_throwsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            kanbanBoard.addToBoard("lalala", taskCall);
        });
    }

/////////////////////////////////////////////////////////////////
/*
    @Test
    void kanbanBoard_deleteFromBoard() throws IOException {
        TaskList done = new KanbanCategory();
        done.add(taskGym);
        fileSaving.saveToFile(done, "done");
        kanbanBoard.loadToBoard(fileLoading.loadFile("done"), "done");
        kanbanBoard.deleteFromBoard("done", taskGym);
        assertThat(kanbanBoard.getDone().getLength()).isEqualTo(0);
    }



    @Test
    void kanbanBoard_deleteFromBoardDoing() throws IOException {
        TaskList doing = new KanbanCategory();
        doing.add(taskGym);
        fileSaving.saveToFile(doing, "doing");
        kanbanBoard.loadToBoard(fileLoading.loadFile("doing"), "doing");
        kanbanBoard.deleteFromBoard("doing", taskGym);
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(0);
    }
*/

    @Test
    void kanbanBoard_deleteFromBoardDoingWithWrongLabel() throws IOException {
        TaskList doing = new KanbanCategory();
        doing.add(taskGym);
        fileSaving.saveToFile(doing, "doing");
        kanbanBoard.loadToBoard(fileLoading.loadFile("doing"), "doing");
        kanbanBoard.deleteFromBoard("done", taskGym);
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(1);
    } // wenn man das falsche Label eingibt, wird es nicht gelöscht

    @Test
    void kanbanBoard_deleteFromBoardDoingWithWrongTask() throws IOException {
        TaskList doing = new KanbanCategory();
        doing.add(taskGym);
        fileSaving.saveToFile(doing, "doing");
        kanbanBoard.loadToBoard(fileLoading.loadFile("doing"), "doing");
        kanbanBoard.deleteFromBoard("done", taskCode);
        assertThat(kanbanBoard.getDoing().getLength()).isEqualTo(1);
    } // wenn man die falsche Task eingibt, wird es nicht gelöscht
}
