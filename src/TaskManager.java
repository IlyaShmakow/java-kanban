import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private int idCount = 0;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();

    private int generateId() { // создание id
        return idCount++;
    }


    public Task addTask(String title, String description) { // добавление задачи
        Task task = new Task(generateId(), title, description, TaskStatus.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic addEpic(String title, String description) { // добавление эпика
        Epic epic = new Epic(generateId(), title, description, TaskStatus.NEW, new ArrayList<>());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Subtask addSubtask(String title, String description, int epicId) { // добавление подзадачи
        Subtask subtask = new Subtask(generateId(), title, description, TaskStatus.NEW, epicId);
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(epicId);
        epic.addSubtask(subtask.getId());

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
        epics.clear();
    }

    public Epic getEpicById(int id) { //получение эпика по id
        return epics.get(id);
    }

    public Epic removeEpicById(int id) { // удаление эпика по id
        Epic epic = epics.remove(id);
        if(epic != null){
            List<Integer> subtaskIds = epic.getSubtasks();
                for(Integer subtaskId : subtaskIds){
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
    }

    public Subtask getSubtaskById(int id) { // получение всех подзадач по id
        return subtasks.get(id);
    }

    public Subtask removeSubtaskById(int id) { // удаление подзадач по id
        return subtasks.remove(id);
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

    public Task updateTask(Task updatedTask){ // обновление задачи
            tasks.put(updatedTask.getId(), updatedTask);
            return updatedTask;
    }

    public Epic updateEpic(Epic updatedEpic){ // обновление эпика
        epics.put(updatedEpic.getId(), updatedEpic);
        return updatedEpic;
    }

    public Subtask updateSubtask(Subtask updatedSubtask){ // обновление подзадачи
        subtasks.put(updatedSubtask.getId(), updatedSubtask);
        checkEpicStatus(updatedSubtask.getEpicId());
        return updatedSubtask;
    }

    public void checkEpicStatus(int epicId){ // проверка статуса эпика
        Epic epic = epics.get(epicId);
        List<Subtask>checkSubtasksEpic = getSubtasksByEpicId(epicId);
        if (checkSubtasksEpic.isEmpty()){
            epic.setStatus(TaskStatus.NEW);
        } else{
            boolean done = true;
            for (Subtask subtask: checkSubtasksEpic){
                if (subtask.getStatus() != TaskStatus.DONE){
                    done = false;
                }
            }
            if (done) {
                epic.setStatus(TaskStatus.DONE);
            } else{
                epic.setStatus(TaskStatus.IN_PROGRESS);
            }
        }
    }
}

