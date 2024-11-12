import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private int idCount = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    private int generateId() { // создание id
        return idCount++;
    }


    public Task addTask(Task task) { // добавление задачи
        if (task == null) {
            return null;
        }
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic addEpic(Epic epic) { // добавление эпика
        if (epic == null) {
            return null;
        }
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask addSubtask(Subtask subtask) { // добавление подзадачи
        if (subtask == null) {
            return null;
        }
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        epic.addSubtask(subtask.getId());
        checkEpicStatus(epicId);

        return subtask;
    }

    public List<Task> getAllTasks() { // получение всех задач
        return new ArrayList<>(tasks.values());
    }

    public void clearAllTasks() { // удаление всех задач
        tasks.clear();
    }

    public Task getTaskById(int id) { // получение задачи по id
        return tasks.get(id);
    }

    public Task removeTaskById(int id) { // удаление задачи по id
        return tasks.remove(id);
    }

    public List<Epic> getAllEpics() {  // получение всех эпиков
        return new ArrayList<>(epics.values());
    }

    public void clearAllEpics() { // удаление всех эпиков
        for(Epic epic : epics.values()){
            List<Integer> subtaskIds = epic.getSubtasks();
                for (Integer subtaskId : subtaskIds){
                    subtasks.remove(subtaskId);
                }
        }
        epics.clear();
    }

    public Epic getEpicById(int id) { //получение эпика по id
        return epics.get(id);
    }

    public Epic removeEpicById(int id) { // удаление эпика по id
        Epic epic = epics.remove(id);
        if (epic != null) {
            List<Integer> subtaskIds = epic.getSubtasks();
            for (Integer subtaskId : subtaskIds) {
                subtasks.remove(subtaskId);
            }
        }
        return epic;
    }

    public List<Subtask> getAllSubtasks() { // получение всех подзадач
        return new ArrayList<>(subtasks.values());
    }

    public void clearAllSubtasks() { // удаление всех подзадач
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
            checkEpicStatus(epic.getId());
        }
    }

    public Subtask getSubtaskById(int id) { // получение всех подзадач по id
        return subtasks.get(id);
    }

    public Subtask removeSubtaskById(int id) { // удаление подзадач по id
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            int epicId = subtask.getEpicId();
            Epic epic = epics.get(epicId);
            if (epic != null) {
                epic.removeSubtask(id);
                checkEpicStatus(epicId);
            } else {
                System.out.println("Эпик не найден");
            }
        } else {
            System.out.println("Подзадача не найдена");
        }
        return subtask;
    }

    public List<Subtask> getSubtasksByEpicId(int epicId) { // получение всех подзадач эпика
        Epic epic = epics.get(epicId);
        List<Subtask> result = new ArrayList<>();
        if (epic != null) {
            for (Integer subtaskId : epic.getSubtasks()) {
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask != null) {
                    result.add(subtask);
                }
            }
        }
        return result;
    }

    public Task updateTask(Task updatedTask) { // обновление задачи
        int taskId = updatedTask.getId();
        if (tasks.containsKey(taskId)) {
            tasks.put(updatedTask.getId(), updatedTask);
            return updatedTask;
        } else {
            System.out.println("Задача не найдена");
            return null;
        }
    }

    public Epic updateEpic(Epic updatedEpic) { // обновление эпика
        Epic epic = epics.get(updatedEpic.getId());
        if (epic != null) {
            epic.setTitle(updatedEpic.getTitle());
            epic.setDescription(updatedEpic.getDescription());
            return updatedEpic;
        } else {
            System.out.println("Эпик не найден");
            return null;
        }
    }

    public Subtask updateSubtask(Subtask updatedSubtask) { // обновление подзадачи
        Subtask subtask = subtasks.get(updatedSubtask.getId());
        if (subtask == null) {
            System.out.println("Подзадача не найдена");
            return null;
        }
        if (subtask.getEpicId() != updatedSubtask.getEpicId()) {
            System.out.println("Разные эпики");
            return null;
        }
        subtasks.put(updatedSubtask.getId(), updatedSubtask);
        checkEpicStatus(updatedSubtask.getEpicId());
        return updatedSubtask;
    }

    private void checkEpicStatus(int epicId) { // проверка статуса эпика
        Epic epic = epics.get(epicId);
        List<Subtask> checkSubtasksEpic = getSubtasksByEpicId(epicId);
        if (checkSubtasksEpic.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            boolean done = true;
            boolean statusNew = true;
            for (Subtask subtask : checkSubtasksEpic) {
                if (subtask.getStatus() != TaskStatus.DONE) {
                    done = false;
                }
                if (subtask.getStatus() != TaskStatus.NEW) {
                    statusNew = false;
                }
            }
            if (done) {
                epic.setStatus(TaskStatus.DONE);
            } else if (statusNew) {
                epic.setStatus(TaskStatus.NEW);
            } else {
                epic.setStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }
}

