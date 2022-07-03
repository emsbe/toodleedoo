package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class FileLoadingTest {
    FileLoading fileLoading = new FileLoading();
    TaskList taskList;
    ByteArrayOutputStream outputStream;

    Task taskVacuum;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;
    KanbanBoard kanbanBoard;

    @BeforeEach
    void setUp() throws IOException {
        FileSaving fileSaving = new FileSaving();
        taskList = new TaskList();
        kanbanBoard = new KanbanBoard();
        dueDate = LocalDate.parse("25.05.2022", formatter);
        deadline = LocalDate.parse("27.05.2022", formatter);

        taskVacuum = new Task("vacuum", dueDate, deadline);
        Task taskJavaProject = new Task("finish Java project", LocalDate.parse("03.07.2022", formatter), LocalDate.parse("08.07.2022", formatter));
        Task taskGroceries = new Task("grocery shopping", LocalDate.parse("04.07.2022", formatter), LocalDate.parse("05.07.2022", formatter));
        Task taskGym = new Task("go to gym", LocalDate.parse("02.07.2022", formatter), LocalDate.parse("03.07.2022", formatter));
        Task taskCallMum = new Task("call Mum", LocalDate.parse("07.07.2022", formatter), LocalDate.parse("08.07.2022", formatter));

        taskList.add(taskVacuum);
        taskList.add(taskJavaProject);

        kanbanBoard.addToBoard("toDo", taskVacuum);
        kanbanBoard.addToBoard("toDo", taskJavaProject);
        kanbanBoard.addToBoard("doing", taskGroceries);
        kanbanBoard.addToBoard("doing", taskCallMum);
        kanbanBoard.addToBoard("done", taskGym);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        fileSaving.saveToFile(taskList, "taskList");
        fileSaving.saveToFile(kanbanBoard.getToDo(), "toDo");
        fileSaving.saveToFile(kanbanBoard.getDoing(), "doing");
        fileSaving.saveToFile(kanbanBoard.getDone(), "done");
        outputStream.reset();
    }

    @Test
    void fileLoading_loadFile_taskListIsOfTypeTaskList() {
        taskList = fileLoading.loadFile("taskList");
        String desiredOutput = "Loading taskList has been successful.";
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
        assertThat(taskList).isInstanceOf(TaskList.class);
    }

    @Test
    void fileLoading_loadFile_toDoIsOfTypeTaskListOrganizer_isNotOfTypeTaskList() {
        TaskListOrganizer toDo = fileLoading.loadFile("toDo");
        assertThat(toDo).isInstanceOf(TaskListOrganizer.class).isNotInstanceOf(TaskList.class);
    }

    @Test
    void fileLoading_loadFile_OutputSaysLoadingIsSuccessful() {
        taskList = fileLoading.loadFile("taskList");
        String desiredOutput = "Loading taskList has been successful.";
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void fileLoading_loadFile_ThrowsIllegalArgumentException_TaskList() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            taskList = fileLoading.loadFile("taskListt");
        });
    }

    @Test
    void fileLoading_loadFile_ThrowsIllegalArgumentException_KanbanTaskList() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            taskList = fileLoading.loadFile("doooing");
        });
    }
// TODO: Alles aus files löschen nach Tests.

}
