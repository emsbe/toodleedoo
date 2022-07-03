package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ManageInputTest {

    ManageInput manageInput;

    @BeforeEach
    void setUp () {
        manageInput = new ManageInput();

    }

    @Test
    void createTask() {
        Task task = manageInput.createTask();
        assertThat(task).isNotNull();
    }
}
