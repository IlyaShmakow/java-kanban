import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EpicTest {
    @Test
    void testTasksEqualById() {
        Epic epic1 = new Epic(1, "Название 1", "Описание 1", TaskStatus.NEW);
        Epic epic2 = new Epic(1, "Название 2", "Описание 2", TaskStatus.NEW);

        assertEquals(epic1, epic2, "Эпики равны если равны их id");
    }

    @Test
    void getTestTasksNotEqualById() {
        Epic epic1 = new Epic(1, "Название 3", "Описание 3", TaskStatus.NEW);
        Epic epic2 = new Epic(2, "Название 3", "Описание 3", TaskStatus.NEW);

        assertNotEquals(epic1, epic2, "Эпики не равны если не равны их id");
    }
}