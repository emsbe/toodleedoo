package hwr.oop.toodleedoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class DisplayTest {
    TaskManager taskList;
    Task taskVacuum;
    Task call;
    Display display;
    ByteArrayOutputStream outputStream;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDateTransformer transformDate = new LocalDateTransformer();
    LocalDate dueDate = transformDate.createLocalDate("05.06.2022");
    LocalDate deadline = transformDate.createLocalDate("07.06.2022");

    @BeforeEach
    void setUp() {
        taskList = new TaskManager();
        taskVacuum = new Task("vacuum", transformDate.createLocalDate("25.05.2022"), transformDate.createLocalDate("27.05.2022"));
        call = new Task("call Begüm", transformDate.createLocalDate("23.05.2022"), transformDate.createLocalDate("25.05.2022"));
        display = new Display();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    void display_showAll_showAllTasksInTaskList() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", dueDate, deadline));
        String desiredOutput = String.format("1 - vacuum on 25.05.2022, Deadline: 27.05.2022%n"+"2 - call Begüm on 05.06.2022, Deadline: 07.06.2022");
        display.showAllTasksIn(taskList);
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void display_showView_givesStringWithTwoTasksSortedByDeadline() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", LocalDate.parse("23.06.2022", formatter), LocalDate.parse("25.05.2022", formatter)));
        display.showViewOf(taskList, "deadline");
        String desiredOutput = String.format("1 - 25.05.2022: deadline for call Begüm%n" +
                "2 - 27.05.2022: deadline for vacuum");
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void display_showView_givesStringWithTwoTasksSortedByDate() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", LocalDate.parse("23.06.2022", formatter), LocalDate.parse("25.05.2022", formatter)));
        display.showViewOf(taskList, "date");
        String desiredOutput = String.format("1 - 25.05.2022: date for vacuum%n" +
                "2 - 23.06.2022: date for call Begüm");
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void display_showTaskAt_showsOnlyOneTaskAtGivenDate() {
        taskList.add(taskVacuum);
        taskList.add(call);
        display.showTaskAt(taskList,"23.05.2022");
        String desiredOutput = String.format("23.05.2022:%n" + "- call Begüm");
        assertThat(desiredOutput).isEqualTo(outputStream.toString().trim());
    }

    @Test
    void display_showTaskAt_showsMultipleTasksAtGivenDate() {
        taskList.add(call);
        taskList.add(new Task("gym", LocalDate.parse("23.05.2022", formatter), LocalDate.parse("25.05.2022", formatter)));
        display.showTaskAt(taskList, "23.05.2022");
        String desiredOutput = String.format("23.05.2022:%n" + "- call Begüm%n" +
                "- gym");
    }
}
