import manager.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
private InMemoryTaskManager taskManager;

    @BeforeEach
    public void setUp(){
        taskManager = new InMemoryTaskManager();
    }

    @Test
    void testAddAndGetTask(){
        Task task = new Task("Название 1", "Описание 1", TaskStatus.NEW);
        Task addedTask = taskManager.addTask(task);
        assertNotNull(addedTask);
        assertEquals(task.getId(), addedTask.getId());

        Task taskById = taskManager.getTaskById(addedTask.getId());
        assertNotNull(taskById);
        assertEquals(addedTask.getId(), taskById.getId());
    }

    @Test
    public void testAddAndGetEpic() {
        Epic epic = new Epic("Название эпика 1", "Описание эпика 1");
        Epic addedEpic = taskManager.addEpic(epic);

        assertNotNull(addedEpic);
        assertEquals(epic.getId(), addedEpic.getId());

        Epic epicById = taskManager.getEpicById(addedEpic.getId());
        assertNotNull(epicById);
        assertEquals(addedEpic.getId(), epicById.getId());
    }

    @Test
    public void testAddAndGetSubtask() {
        Epic epic = new Epic("Название эпика 2", "Описание эпика 2");
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask("Название сабтаска 1", "Описание сабтаска 1", TaskStatus.NEW, epic.getId());
        Subtask addedSubtask = taskManager.addSubtask(subtask);

        assertNotNull(addedSubtask);
        assertEquals(subtask.getId(), addedSubtask.getId());

        Subtask subtaskById = taskManager.getSubtaskById(addedSubtask.getId());
        assertNotNull(subtaskById);
        assertEquals(addedSubtask.getId(), subtaskById.getId());
    }

        @Test
    void tasksNotConflict(){
        Task task1 = new Task(1,"Название 1", "Описание 1", TaskStatus.NEW);
        taskManager.addTask(task1);

        Task task2 = new Task("Название 2", "Описание 2", TaskStatus.NEW);
        Task addedTask = taskManager.addTask(task2);

        Task task1ById = taskManager.getTaskById(task1.getId());
        Task task2ById = taskManager.getTaskById(addedTask.getId());

        assertNotNull(task1ById);
        assertNotNull(task2ById);

        assertNotEquals(task1ById.getId(), task2ById.getId());
    }

    @Test
    void taskNotChange(){
        Task task1 = new Task("Название 1", "Описание 1", TaskStatus.NEW);
        Task addedTask = taskManager.addTask(task1);
        Task taskById = taskManager.getTaskById(addedTask.getId());

        assertNotNull(taskById);
        assertEquals(task1.getId(), taskById.getId());
        assertEquals(task1.getTitle(), taskById.getTitle());
        assertEquals(task1.getDescription(), taskById.getDescription());
        assertEquals(task1.getStatus(), taskById.getStatus());
    }

    @Test
    void testAddEpicAsSubtask() {
        Epic epic = new Epic(1, "Название эпика 1", "Описание эпика 1", TaskStatus.NEW);
        taskManager.addEpic(epic);

        Subtask subtask = new Subtask(epic.getId(), "Название сабтаска 1", "Описание подзадачи 1", TaskStatus.NEW, epic.getId());
        Subtask result = taskManager.addSubtask(subtask);

        assertNull(result, "Эпик не должен быть добавлен как сабтаск");
    }

    @Test
    void testSubtaskCantBeOwnEpic(){
        Subtask subtask = new Subtask(1, "Название сабтаска 1", "Описание подзадачи 1", TaskStatus.NEW, 1);
        Subtask result = taskManager.addSubtask(subtask);
        assertNull(result, "Подзадача не может быть своим эпиком");
    }
}

