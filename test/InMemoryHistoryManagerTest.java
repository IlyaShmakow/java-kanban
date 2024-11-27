import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private InMemoryHistoryManager historyManager;
    private Task task;

    @BeforeEach
    void setUp(){
        historyManager = new InMemoryHistoryManager();
        task = new Task (1,"Название задачи", "Описание задачи", TaskStatus.NEW);
    }

    @Test
    void addTaskToHistory(){
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertNotNull(history,"Не должна быть пустой");
        assertEquals(1, history.size(),"Должна быть одна задача");

        Task savedTask = history.get(0);
        assertNotNull(savedTask);
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getDescription(), savedTask.getDescription());
        assertEquals(task.getStatus(), savedTask.getStatus());
    }



}