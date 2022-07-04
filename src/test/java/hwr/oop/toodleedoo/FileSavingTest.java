package hwr.oop.toodleedoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.*;

public class FileSavingTest {
    FileSaving fileSaving = new FileSaving();
    Task taskVacuum;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;
    TaskList taskList;
    KanbanBoard kanbanBoard;
    ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        taskList = new TaskManager();
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

        kanbanBoard.addToBoard("to do", taskVacuum);
        kanbanBoard.addToBoard("to do", taskJavaProject);
        kanbanBoard.addToBoard("doing", taskGroceries);
        kanbanBoard.addToBoard("doing", taskCallMum);
        kanbanBoard.addToBoard("done", taskGym);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

    }

    @Test
    void fileSaving_saveToFile_saveTaskListToFile_printsSaveSuccessful() throws IOException {
        fileSaving.saveToFile(taskList, "taskList");
        String desiredOutput = "taskList has been successfully saved.";
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void fileSaving_saveToFile_saveTaskListToFile_printsSaveUnsuccessful() throws IOException {
        fileSaving.saveToFile(kanbanBoard.getDoing(), "doiing");
        String desiredOutput = "doing has been successfully saved.";
        assertThat(desiredOutput).isNotEqualTo(outputStream.toString().trim());
    }

}
