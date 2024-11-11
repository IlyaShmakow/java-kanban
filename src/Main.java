import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task ("Задача 1", "Описание1", TaskStatus.NEW);
        Task task2 = new Task ("Задача 2", "Описание2", TaskStatus.NEW);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        System.out.println(task1);
        System.out.println(task2);

        Epic epic1 = new Epic ("Эпик1", "Описание эпика 1", TaskStatus.NEW, new ArrayList<>());
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask ("Подзадача1", "Описание подзадачи1", TaskStatus.NEW, epic1.getId());
        taskManager.addSubtask(subtask1, epic1.getId());
        Subtask subtask2 = new Subtask("Подзадача2", "Описание подзадачи2", TaskStatus.NEW, epic1.getId());
        taskManager.addSubtask(subtask2, epic1.getId());
        System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);

        Epic epic2 = new Epic("Эпик2", "Описание эпика 2", TaskStatus.NEW, new ArrayList<>());
        taskManager.addEpic(epic2);
        Subtask subtask3 = new Subtask("Подзадача3", "Описание подзадачи3", TaskStatus.NEW, epic2.getId());
        taskManager.addSubtask(subtask3, epic2.getId());

        System.out.println("Все задачи:");
        for (Task task: taskManager.getAllTasks()){
            System.out.println(task);
        }

        System.out.println("Все эпики");
        for (Epic epic: taskManager.getAllEpics()){
            System.out.println(epic);
        }

        System.out.println("Все подзадачи:");
        for (Subtask subtask: taskManager.getAllSubtasks()){
            System.out.println(subtask);
        }

        task1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task1);
        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);
        subtask2.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask2);
        subtask3.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask3);


        System.out.println("Все задачи после обновления:");
        for (Task task: taskManager.getAllTasks()){
            System.out.println(task);
        }

        System.out.println("Все эпики после обновления:");
        for (Epic epic: taskManager.getAllEpics()){
            System.out.println(epic);
        }

        System.out.println("Все подзадачи после обновления:");
        for (Subtask subtask: taskManager.getAllSubtasks()){
            System.out.println(subtask);
        }

        taskManager.clearAllTasks();

        System.out.println("Все задачи после удаления:");
        for (Task task: taskManager.getAllTasks()){
            System.out.println(task);
        }

        taskManager.removeSubtaskById(subtask2.getId());

        System.out.println("Все подзадачи после удаления по id:");
        for (Subtask subtask: taskManager.getAllSubtasks()){
            System.out.println(subtask);
        }

        taskManager.removeEpicById(epic1.getId());

        System.out.println("Все эпики после удаления по id:");
        for (Epic epic: taskManager.getAllEpics()){
            System.out.println(epic);
        }

        System.out.println("Все подзадачи после удаления эпика по id:");
        for (Subtask subtask: taskManager.getAllSubtasks()){
            System.out.println(subtask);
        }

        taskManager.clearAllEpics();
        System.out.println("Эпики после удаления:");
        for (Epic epic: taskManager.getAllEpics()){
            System.out.println(epic);
        }
        System.out.println("Все подзадачи после удаления эпиков:");
        for (Subtask subtask: taskManager.getAllSubtasks()){
            System.out.println(subtask);
        }

    }
}
