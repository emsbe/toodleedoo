package hwr.oop.toodleedoo;

public class ManageInput {
    LocalDateTransformer transformDate = new LocalDateTransformer();

    public Task createTask() {
        InputUse input = new InputUse();
        return new Task(input.enterTaskName(), transformDate.createLocalDate(input.enterDate()), transformDate.createLocalDate(input.enterDeadline()));
    }
}
