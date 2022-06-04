package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import static org.assertj.core.api.Assertions.*;

public class DisplayTest {
    TaskList taskList;
    Task task;
    Display display;
    OutputStream outputStream;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task = new Task("washing", "04.06.22", "05.06.22");
        display = new Display();
        outputStream = new ByteArrayOutputStream();
    }
    @Test
    void display_showAll_showAllTasksInTaskList() {
        taskList.add(task);
        taskList.add(new Task("call Begüm", "04.06.22", "05.06.22"));
        display.showAllTasksIn(taskList);
        //String output = showAllTasksIn(outputStream);
        //assertThat(output).isEqualTo
        // TODO: Wie kann man das testen?

    }
}
