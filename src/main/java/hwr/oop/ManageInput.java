package hwr.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManageInput {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Task createTask() {
        InputUse input = new InputUse();
        return new Task(input.enterTaskName(), LocalDate.parse(input.enterDate(), formatter), LocalDate.parse(input.enterDeadline(), formatter));

    }
}
