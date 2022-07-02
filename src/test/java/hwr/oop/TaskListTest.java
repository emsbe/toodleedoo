package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class TaskListTest {
    TaskList taskList;
    Task taskVacuum;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        dueDate = LocalDate.parse("25.05.2022", formatter);
        deadline = LocalDate.parse("27.05.2022", formatter);
        taskVacuum = new Task("vacuum", dueDate, deadline);

    }

    @Test
    void taskList_isEmpty_returnsTrue() {
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskList_add_isEmptyReturnsFalse() {
        taskList.add(taskVacuum);
        assertThat(taskList.isEmpty()).isFalse();
    }

    @Test
    void taskList_deleteTask_isEmptyReturnsTrueAfterOneTaskIsAddedAndRemoved() {
        taskList.add(taskVacuum);
        taskList.delete(taskVacuum);
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskList_deleteTask_isEmptyReturnsFalseAfterTwoTasksAreAddedAndOneRemoved() {
        taskList.add(taskVacuum);
        Task newTask = new Task("do OOP homework", dueDate, deadline);
        taskList.add(newTask);
        taskList.delete(taskVacuum);
        assertThat(taskList.isEmpty()).isFalse();
    }

    @Test
    void taskList_markAsDone_isEmptyReturnsTrueAfterOneTaskIsAddedAndRemoved() {
        taskList.add(taskVacuum);
        taskList.markAsDone(taskVacuum);
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskList_getIndexOf_returns0AfterOneTaskIsAdded() {
        taskList.add(taskVacuum);
        int index = taskList.getIndexOf(taskVacuum);
        assertThat(index).isEqualTo(0);
    }

    @Test
    void taskList_getIndexOf_returns1AfterThreeAddedAndSecondQueried() {
        taskList.add(taskVacuum);
        Task taskClean = new Task("clean bathroom", dueDate, deadline);
        Task taskStudy = new Task("study", dueDate, deadline);
        taskList.add(taskClean);
        taskList.add(taskStudy);
        int index = taskList.getIndexOf(taskClean);
        assertThat(index).isEqualTo(1);
    }

    @Test
    void taskList_getIndexOf_catchesErrorBecauseTaskIsNotInTaskList() {
        assertThatThrownBy(() -> taskList.getIndexOf(taskVacuum)).isInstanceOf(AssertionError.class);
    }

    @Test
    void taskList_changeTask_getTaskAtIndex_returnsNewTaskAtGivenIndex() {
        Task taskPython = new Task("Python", dueDate, deadline);
        taskList.add(taskPython);
        int index = taskList.getIndexOf(taskPython);
        Task taskJava = new Task("Java", dueDate, deadline);
        taskList.changeTask(taskList.getIndexOf(taskPython), taskJava);
        taskList.getTaskAtIndex(index);
        assertThat(taskList.getTaskAtIndex(index)).isEqualTo(taskJava);
    }

    @Test
    void taskList_changeTask_returnsPreviousTaskAtGivenIndex() {
        Task taskPython = new Task("Python", dueDate, deadline);
        taskList.add(taskPython);
        int index = taskList.getIndexOf(taskPython);
        Task newTask = new Task("Java", dueDate, deadline);
        Task oldTask = taskList.changeTask(taskList.getIndexOf(taskPython), newTask);
        assertThat(oldTask).isEqualTo(taskPython);
    }

    @Test
    void taskList_getLength_OneTaskAdded_returns1() {
        taskList.add(taskVacuum);
        assertThat(taskList.getLength()).isEqualTo(1);
    }

    @Test
    void taskList_add_TaskAddedSecondWithEarlierDateIsAtIndex0() {
        Task taskToTest = new Task("call Mum", LocalDate.parse("05.06.2022", formatter), LocalDate.parse("08.06.2022", formatter));
        taskList.add(new Task("study", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("06.06.2022", formatter)));
        taskList.add(taskToTest);
        assertThat(taskList.getTaskAtIndex(0)).isEqualTo(taskToTest);
    }

    @Test
    void taskList_add_TaskAddedToEmptyList_NoErrorAddingToAnEmptyArrayList() {
        taskList.add(taskVacuum);
        assertThat(taskList.getTaskAtIndex(0)).isEqualTo(taskVacuum);
    }

    @Test
    void taskList_add_AddedTwoTasksThenThirdTask_NoErrorAddingToEndOfArrayList() {
        Task taskToTest = new Task("call Mum", LocalDate.parse("12.06.2022", formatter), LocalDate.parse("13.06.2022", formatter));
        taskList.add(new Task("study", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("06.06.2022", formatter)));
        taskList.add(new Task("meet Friends", LocalDate.parse("06.06.2022", formatter), LocalDate.parse("06.06.2022", formatter)));
        taskList.add(taskToTest);
        assertThat(taskList.getTaskAtIndex(2)).isEqualTo(taskToTest);
    }

    @Test
    void taskList_sortBy_sortsCurrentStateOfTaskListByDeadlineIntoNewTaskList_returnsTaskWithEarliestDeadlineAtIndex0() {
        Task taskToTest = new Task("call Mum", LocalDate.parse("12.06.2022", formatter), LocalDate.parse("13.06.2022", formatter));
        taskList.add(new Task("study", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("15.06.2022", formatter)));
        taskList.add(new Task("meet Friends", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("14.06.2022", formatter)));
        taskList.add(taskToTest);
        TaskList filteredTaskList = taskList;
        filteredTaskList.sortBy("deadline");
        assertThat(filteredTaskList.getTaskAtIndex(0)).isEqualTo(taskToTest);
    }

    @Test
    void taskList_sortBy_returnsTaskWithLatestDeadlineAtIndex2() {
        Task taskToTest = new Task("study", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("15.06.2022", formatter));
        taskList.add(new Task("call Mum", LocalDate.parse("12.06.2022", formatter), LocalDate.parse("13.06.2022", formatter)));
        taskList.add(new Task("meet Friends", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("14.06.2022", formatter)));
        taskList.add(taskToTest);
        TaskList filteredTaskList = taskList;
        filteredTaskList.sortBy("deadline");
        assertThat(filteredTaskList.getTaskAtIndex(2)).isEqualTo(taskToTest);
    }




}





