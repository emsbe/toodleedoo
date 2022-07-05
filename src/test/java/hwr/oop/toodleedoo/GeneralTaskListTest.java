package hwr.oop.toodleedoo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class GeneralTaskListTest {
    TaskManager taskList;
    Task taskVacuum;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;
    LocalDateTransformer transformDate = new LocalDateTransformer();

    @BeforeEach
    void setUp() {
        taskList = new GeneralTaskList();
        dueDate = transformDate.createLocalDate("25.05.2022");
        deadline = transformDate.createLocalDate("27.05.2022");
        taskVacuum = new Task("vacuum", dueDate, deadline);

    }

    @Test
    void taskManager_isEmpty_returnsTrue() {
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskManager_add_isEmptyReturnsFalse() {
        taskList.add(taskVacuum);
        assertThat(taskList.isEmpty()).isFalse();
    }

    @Test
    void taskManager_deleteTask_isEmptyReturnsTrueAfterOneTaskIsAddedAndRemoved() {
        taskList.add(taskVacuum);
        taskList.delete(taskVacuum);
        assertThat(taskList.isEmpty()).isTrue();
    }

    @Test
    void taskManager_deleteTask_isEmptyReturnsFalseAfterTwoTasksAreAddedAndOneRemoved() {
        taskList.add(taskVacuum);
        Task newTask = new Task("do OOP homework", dueDate, deadline);
        taskList.add(newTask);
        taskList.delete(taskVacuum);
        assertThat(taskList.isEmpty()).isFalse();
    }

    @Test
    void taskManager_getIndexOf_returns0AfterOneTaskIsAdded() {
        taskList.add(taskVacuum);
        int index = taskList.getIndexOf(taskVacuum);
        assertThat(index).isEqualTo(0);
    }

    @Test
    void taskManager_getIndexOf_returns1AfterThreeAddedAndSecondQueried() {
        taskList.add(taskVacuum);
        Task taskClean = new Task("clean bathroom", dueDate, deadline);
        Task taskStudy = new Task("study", dueDate, deadline);
        taskList.add(taskClean);
        taskList.add(taskStudy);
        int index = taskList.getIndexOf(taskClean);
        assertThat(index).isEqualTo(1);
    }

    @Test
    void taskManager_getIndexOf_catchesErrorBecauseTaskIsNotInTaskList() {
        assertThatThrownBy(() -> taskList.getIndexOf(taskVacuum)).isInstanceOf(AssertionError.class);
    }

    @Test
    void taskManager_changeTask_getTaskAtIndex_returnsNewTaskAtGivenIndex() {
        Task taskPython = new Task("Python", dueDate, deadline);
        taskList.add(taskPython);
        int index = taskList.getIndexOf(taskPython);
        Task taskJava = new Task("Java", dueDate, deadline);
        taskList.changeTask(taskList.getIndexOf(taskPython), taskJava);
        taskList.getTaskAtIndex(index);
        assertThat(taskList.getTaskAtIndex(index)).isEqualTo(taskJava);
    }

    @Test
    void taskManager_changeTask_returnsPreviousTaskAtGivenIndex() {
        Task taskPython = new Task("Python", dueDate, deadline);
        taskList.add(taskPython);
        int index = taskList.getIndexOf(taskPython);
        Task newTask = new Task("Java", dueDate, deadline);
        Task oldTask = taskList.changeTask(taskList.getIndexOf(taskPython), newTask);
        assertThat(oldTask).isEqualTo(taskPython);
    }

    @Test
    void taskManager_getLength_OneTaskAdded_returns1() {
        taskList.add(taskVacuum);
        assertThat(taskList.getLength()).isEqualTo(1);
    }

    @Test
    void taskManager_add_TaskAddedSecondWithEarlierDateIsAtIndex0() {
        Task taskToTest = new Task("call Mum", transformDate.createLocalDate("05.06.2022"), transformDate.createLocalDate("08.06.2022"));
        taskList.add(new Task("study", transformDate.createLocalDate("07.06.2022"), transformDate.createLocalDate("06.06.2022")));
        taskList.add(taskToTest);
        assertThat(taskList.getTaskAtIndex(0)).isEqualTo(taskToTest);
    }

    @Test
    void taskManager_add_TaskAddedToEmptyList_NoErrorAddingToAnEmptyArrayList() {
        taskList.add(taskVacuum);
        assertThat(taskList.getTaskAtIndex(0)).isEqualTo(taskVacuum);
    }

    @Test
    void taskManager_add_AddedTwoTasksThenThirdTask_NoErrorAddingToEndOfArrayList() {
        Task taskToTest = new Task("call Mum", LocalDate.parse("12.06.2022", formatter), LocalDate.parse("13.06.2022", formatter));
        taskList.add(new Task("study", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("06.06.2022", formatter)));
        taskList.add(new Task("meet Friends", LocalDate.parse("06.06.2022", formatter), LocalDate.parse("06.06.2022", formatter)));
        taskList.add(taskToTest);
        assertThat(taskList.getTaskAtIndex(2)).isEqualTo(taskToTest);
    }

    @Test
    void taskManager_sortBy_sortsCurrentStateOfTaskListByDeadlineIntoNewTaskList_returnsTaskWithEarliestDeadlineAtIndex0() {
        Task taskToTest = new Task("call Mum", transformDate.createLocalDate("12.06.2022"), transformDate.createLocalDate("13.06.2022"));
        taskList.add(new Task("study", transformDate.createLocalDate("07.06.2022"), transformDate.createLocalDate("15.06.2022")));
        taskList.add(new Task("meet Friends", transformDate.createLocalDate("07.06.2022"), transformDate.createLocalDate("14.06.2022")));
        taskList.add(taskToTest);
        TaskManager filteredTaskList = taskList;
        filteredTaskList.sortBy("deadline");
        assertThat(filteredTaskList.getTaskAtIndex(0)).isEqualTo(taskToTest);
    }

    @Test
    void taskManager_sortBy_returnsTaskWithLatestDeadlineAtIndex2() {
        Task taskToTest = new Task("study", transformDate.createLocalDate("07.06.2022"), transformDate.createLocalDate("15.06.2022"));
        taskList.add(new Task("call Mum", transformDate.createLocalDate("12.06.2022"), transformDate.createLocalDate("13.06.2022")));
        taskList.add(new Task("meet Friends", transformDate.createLocalDate("07.06.2022"), transformDate.createLocalDate("14.06.2022")));
        taskList.add(taskToTest);
        TaskManager filteredTaskList = taskList;
        filteredTaskList.sortBy("deadline");
        assertThat(filteredTaskList.getTaskAtIndex(2)).isEqualTo(taskToTest);
    }

    @Test
    void taskManager_sortBy_throwIllegalArgumentException() {
        taskList.add(new Task("meet Friends", transformDate.createLocalDate("07.06.2022"), transformDate.createLocalDate("14.06.2022")));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            taskList.sortBy("label");
        });
    }

    @Test
    void taskManager_getTaskWithName_returnsListWithOneTask() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Mum", transformDate.createLocalDate("12.06.2022"), transformDate.createLocalDate("13.06.2022")));
        assertThat(taskList.getTaskWithName("vacuum").size()).isEqualTo(1);
    }

    @Test
    void taskManager_getTaskWithName_returnsListWithTwoTasks() {
        taskList.add(taskVacuum);
        taskList.add(new Task("vacuum", transformDate.createLocalDate("12.06.2022"), transformDate.createLocalDate("13.06.2022")));
        assertThat(taskList.getTaskWithName("vacuum").size()).isEqualTo(2);
    }

    @Test
    void taskManager_getTaskWithName_returnsEmptyList() {
        taskList.add(taskVacuum);
        assertThat(taskList.getTaskWithName("call Mum").size()).isEqualTo(0);
    }




}





