package hwr.oop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FilterTest {

    TaskList taskList;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    Filter filter;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Task studying = new Task("study", LocalDate.parse("07.06.2022", formatter), LocalDate.parse("08.06.2022", formatter));
        Task homework = new Task("do OOP homework", LocalDate.parse("09.06.2022", formatter), LocalDate.parse("10.06.2022", formatter));
        Task cleaning = new Task("clean kitchen", LocalDate.parse("11.06.2022", formatter), LocalDate.parse("12.06.2022", formatter));
        taskList.add(studying);
        taskList.add(homework);
        taskList.add(cleaning);
    }
    /*
    @Test
    void filter() {
        filter.sortBy("deadline");

    }
    */
}
