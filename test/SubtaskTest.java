import org.junit.jupiter.api.Test;
import task.Subtask;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    void testTasksEqualById() {
        Subtask sabtask1 = new Subtask(1, "Название 1", "Описание 1", TaskStatus.NEW,1);
        Subtask sabtask2 = new Subtask(1, "Название 2", "Описание 2", TaskStatus.NEW, 2);

        assertEquals(sabtask1, sabtask2, "Сабтаски равны если равны их id");
    }

    @Test
    void testTasksNotEqualById() {
        Subtask sabtask1 = new Subtask(1, "Название 1", "Описание 1", TaskStatus.NEW,1);
        Subtask sabtask2 = new Subtask(2, "Название 1", "Описание 1", TaskStatus.NEW, 1);


        assertNotEquals(sabtask1, sabtask2, "Сабтаски не равны если не равны их id");
    }
}