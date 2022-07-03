package hwr.oop.toodleedoo;

import hwr.oop.toodleedoo.Display;
import hwr.oop.toodleedoo.Task;
import hwr.oop.toodleedoo.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class DisplayTest {
    TaskList taskList;
    Task taskVacuum;
    Display display;
    ByteArrayOutputStream outputStream;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate = LocalDate.parse("05.06.2022", formatter);
    LocalDate deadline = LocalDate.parse("07.06.2022", formatter);

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        LocalDate dueDate = LocalDate.parse("25.05.2022", formatter);
        LocalDate deadline = LocalDate.parse("27.05.2022", formatter);
        taskVacuum = new Task("vacuum", dueDate, deadline);
        display = new Display();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    void display_showAll_showAllTasksInTaskList() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", dueDate, deadline));
        String desiredOutput = String.format("1 - vacuum am 2022-05-25, Deadline: 2022-05-27%n"+"2 - call Begüm am 2022-06-05, Deadline: 2022-06-07");
        display.showAllTasksIn(taskList);
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void display_showView() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", LocalDate.parse("23.06.2022", formatter), LocalDate.parse("25.05.2022", formatter)));
        display.showViewOf(taskList, "deadline");
        String desiredOutput = String.format("1 - 2022-05-25: deadline for call Begüm%n" +
                "2 - 2022-05-27: deadline for vacuum");
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void display_showTaskAt() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", LocalDate.parse("23.06.2022", formatter), LocalDate.parse("25.05.2022", formatter)));
        display.showTaskAt(taskList,"23.06.2022");
        String desiredOutput = "To Do on 23.06.2022: call Begüm";
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }
}
