package hwr.oop;

public class Display {

    public void showAllTasksIn(TaskList taskList) {
        int length = taskList.getLength();
        for (int index = 0; index < length; index++) {
            Task task = taskList.getTaskAtIndex(index);
            String output = String.format("%d - %s am %s, Deadline: %s", index+1, task.getTaskName(), task.getDate(), task.getDeadline());
            System.out.println(output);
        }
    }


}
