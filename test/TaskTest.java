import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTasksEqualById() {
        Task task1 = new Task(1, "Название 1", "Описание 1", TaskStatus.NEW);
        Task task2 = new Task(1, "Название 2", "Описание 2", TaskStatus.NEW);

        assertEquals(task1, task2, "Таски равны если равны их id");
    }

    @Test
    void testTasksNotEqualById() {
        Task task1 = new Task(1, "Название 3", "Описание 3", TaskStatus.NEW);
        Task task2 = new Task(2, "Название 3", "Описание 3", TaskStatus.NEW);

        assertNotEquals(task1, task2, "Таски не равны если не равны их id");
    }
}