package hwr.oop.toodleedoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

public class InputTest {
    Input input;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    void input_enterTaskName_returnsTaskName() {
        inputStream = new ByteArrayInputStream("vacuum\n".getBytes());
        input = new Input(inputStream, printStream);
        input.enterTaskName();
        String outputText = outputStream.toString();
        String key = "output: ";
        String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
        assertThat(output).isEqualTo("vacuum");
    }

    @Test
    void input_enterDate_returnsDate() {
        inputStream = new ByteArrayInputStream("23.05.2022\n".getBytes());
        input = new Input(inputStream, printStream);
        input.enterDate();
        String outputText = outputStream.toString();
        String key = "output: ";
        String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
        assertThat(output).isEqualTo("23.05.2022");
    }

    @Test
    void input_enterDeadline_returnsDeadline() {
        inputStream = new ByteArrayInputStream("24.05.2022\n".getBytes());
        input = new Input(inputStream, printStream);
        input.enterDeadline();
        String outputText = outputStream.toString();
        String key = "output: ";
        String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
        assertThat(output).isEqualTo("24.05.2022");
    }



}
