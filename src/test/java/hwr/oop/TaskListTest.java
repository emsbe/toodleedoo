package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class TaskListTest {
    TaskList taskList;
    Task task;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task = new Task("vacuum", dueDate, deadline);
        LocalDate dueDate = LocalDate.parse("25.05.2022", formatter);
        LocalDate deadline = LocalDate.parse("27.05.2022", formatter);

    }

    @Test
    void taskList_isEmpty_returnsTrue() {
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskList_add_isEmptyReturnsFalse() {
        taskList.add(task);
        assertThat(taskList.isEmpty()).isFalse();
    }

    @Test
    void taskList_deleteTask_isEmptyReturnsTrueAfterOneTaskIsAddedAndRemoved() {
        taskList.add(task);
        taskList.deleteTask(task);
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskList_deleteTask_isEmptyReturnsFalseAfterTwoTasksAreAddedAndOneRemoved() {
        taskList.add(task);
        Task newTask = new Task("do OOP homework", dueDate, deadline);
        taskList.add(newTask);
        taskList.deleteTask(task);
        assertThat(taskList.isEmpty()).isFalse();
    }

    @Test
    void taskList_markAsDone_isEmptyReturnsTrueAfterOneTaskIsAddedAndRemoved() {
        taskList.add(task);
        taskList.markAsDone(task);
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskList_getIndexOf_returns0AfterOneTaskIsAdded() {
        taskList.add(task);
        int index = taskList.getIndexOf(task);
        assertThat(index).isEqualTo(0);
    }

    @Test
    void taskList_getIndexOf_returns1AfterThreeAddedAndSecondQueried() {
        taskList.add(task);
        Task secondTask = new Task("clean bathroom", dueDate, deadline);
        Task thirdTask = new Task("study", dueDate, deadline);
        taskList.add(secondTask);
        taskList.add(thirdTask);
        int index = taskList.getIndexOf(secondTask);
        assertThat(index).isEqualTo(1);
    }

    @Test
    void taskList_getIndexOf_catchesErrorBecauseTaskIsNotInTaskList() {
        assertThatThrownBy(() -> taskList.getIndexOf(task)).isInstanceOf(AssertionError.class);
    }

    @Test
    void taskList_changeTask_getTaskAtIndex_returnsNewTaskAtGivenIndex() {
        Task fourthTask = new Task("Python", dueDate, deadline);
        taskList.add(fourthTask);
        int index = taskList.getIndexOf(fourthTask);
        Task newTask = new Task("Java", dueDate, deadline);
        taskList.changeTask(taskList.getIndexOf(fourthTask), newTask);
        taskList.getTaskAtIndex(index);
        assertThat(taskList.getTaskAtIndex(index)).isEqualTo(newTask);
    }

    @Test
    void taskList_changeTask_returnsPreviousTaskAtGivenIndex() {
        Task fourthTask = new Task("Python", dueDate, deadline);
        taskList.add(fourthTask);
        int index = taskList.getIndexOf(fourthTask);
        Task newTask = new Task("Java", dueDate, deadline);
        Task oldTask = taskList.changeTask(taskList.getIndexOf(fourthTask), newTask);
        assertThat(oldTask).isEqualTo(fourthTask);
    }

    @Test
    void taskList_getLength_returns1() {
        taskList.add(task);
        assertThat(taskList.getLength()).isEqualTo(1);
    }


}





