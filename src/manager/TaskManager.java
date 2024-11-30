package manager;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.List;

public interface TaskManager {

    Task addTask(Task task);

    Epic addEpic(Epic epic);

    Subtask addSubtask(Subtask subtask);

    List<Task> getAllTasks();

    void clearAllTasks();

    Task getTaskById(int id);

    Task removeTaskById(int id);

    List<Epic> getAllEpics();

    void clearAllEpics();

    Epic getEpicById(int id);

    Epic removeEpicById(int id);

    List<Subtask> getAllSubtasks();

    void clearAllSubtasks();

    Subtask getSubtaskById(int id);

    Subtask removeSubtaskById(int id);

    List<Subtask> getSubtasksByEpicId(int epicId);

    Task updateTask(Task updatedTask);

    Epic updateEpic(Epic updatedEpic);

    Subtask updateSubtask(Subtask updatedSubtask);

    List<Task> getHistory();

}
