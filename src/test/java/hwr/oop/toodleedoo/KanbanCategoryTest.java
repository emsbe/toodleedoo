package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class KanbanCategoryTest {
    TaskListOrganizer toDo;
    TaskListOrganizer doing;
    TaskListOrganizer done;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeEach
    void setUp() {
        toDo = new KanbanCategory();
        doing = new KanbanCategory();
        done = new KanbanCategory();
    }

    @Test
    void kanbanCategory_isEmpty_returnsTrue() {
        assertThat(toDo.isEmpty()).isTrue();
        assertThat(doing.isEmpty()).isTrue();
        assertThat(done.isEmpty()).isTrue();
    }

    @Test
    void kanbanCategory_add_isEmptyIsFalse() {
        toDo.add(new Task("code", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter)));
        doing.add(new Task("call Mum", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter)));
        done.add(new Task("prepare food", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter)));
        assertThat(toDo.isEmpty()).isFalse();
        assertThat(doing.isEmpty()).isFalse();
        assertThat(done.isEmpty()).isFalse();
    }

    @Test
    void kanbanCategory_delete_oneTaskIsAddedThenDeleted_isEmptyIsTrue() {
        Task code = new Task("code", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        Task call = new Task("call Mum", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        Task food = new Task("prepare food", LocalDate.parse("26.06.2022", formatter), LocalDate.parse("01.07.2022", formatter));
        toDo.add(code);
        doing.add(call);
        done.add(food);

        toDo.delete(code);
        doing.delete(call);
        done.delete(food);

        assertThat(toDo.isEmpty()).isTrue();
        assertThat(doing.isEmpty()).isTrue();
        assertThat(done.isEmpty()).isTrue();

    }
}
