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

    public void showViewOf(TaskList taskList, String view) {
        TaskList filteredTaskList = taskList;
        filteredTaskList.sortBy(view);
        for (int index = 0; index < filteredTaskList.getLength(); index++) {
            Task task = taskList.getTaskAtIndex(index);
            System.out.println(String.format("%d - %s: Deadline für %s", index+1, task.getDeadline(), task.getTaskName()));
        }
    }

}
