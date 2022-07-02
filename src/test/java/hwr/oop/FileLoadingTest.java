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
    TaskListOrganizer toDo;
    TaskListOrganizer doing;
    TaskListOrganizer done;

    @BeforeEach
    void setUp() throws IOException {
        FileSaving fileSaving = new FileSaving();
        taskList = new TaskList();
        toDo = new ToDo();
        doing = new Doing();
        done = new Done();
        dueDate = LocalDate.parse("25.05.2022", formatter);
        deadline = LocalDate.parse("27.05.2022", formatter);

        taskVacuum = new Task("vacuum", dueDate, deadline);
        Task taskJavaProject = new Task("finish Java project", LocalDate.parse("03.07.2022", formatter), LocalDate.parse("08.07.2022", formatter));
        Task taskGroceries = new Task("grocery shopping", LocalDate.parse("04.07.2022", formatter), LocalDate.parse("05.07.2022", formatter));
        Task taskGym = new Task("go to gym", LocalDate.parse("02.07.2022", formatter), LocalDate.parse("03.07.2022", formatter));
        Task taskCallMum = new Task("call Mum", LocalDate.parse("07.07.2022", formatter), LocalDate.parse("08.07.2022", formatter));

        taskList.add(taskVacuum);
        taskList.add(taskJavaProject);

        toDo.add(taskVacuum);
        toDo.add(taskJavaProject);
        doing.add(taskGroceries);
        doing.add(taskCallMum);
        done.add(taskGym);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        fileSaving.saveToFile(taskList, "taskList");
        fileSaving.saveToFile(toDo, "toDo");
        fileSaving.saveToFile(doing, "doing");
        fileSaving.saveToFile(done, "done");
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
        toDo = fileLoading.loadFile("toDo");
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


}
