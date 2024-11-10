public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task1 = taskManager.addTask("Задача 1", "Описание1");
        Task task2 = taskManager.addTask("Задача 2", "Описание2");
        System.out.println(task1);
        System.out.println(task2);

        Epic epic1 = taskManager.addEpic("Эпик1", "Описание эпика 1");
        Subtask subtask1 = taskManager.addSubtask("Подзадача1", "Описание подзадачи1", 2);
        Subtask subtask2 = taskManager.addSubtask("Подзадача2", "Описание подзадачи2", 2);
        System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);

        Epic epic2 = taskManager.addEpic("Эпик2", "Описание эпика 2");
        Subtask subtask3 = taskManager.addSubtask("Подзадача3", "Описание подзадачи3", 5);

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

        task1 = new Task(task1.getId(), "Задача 1", "Описание1", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task1);
        subtask1 = new Subtask(subtask1.getId(),"Подзадача1", "Описание подзадачи1", TaskStatus.IN_PROGRESS, epic1.getId());
        taskManager.updateSubtask(subtask1);
        subtask2 = new Subtask(subtask2.getId(),"Подзадача2", "Описание подзадачи2", TaskStatus.DONE, epic1.getId());
        taskManager.updateSubtask(subtask2);
        subtask3 = new Subtask(subtask3.getId(),"Подзадача3", "Описание подзадачи3", TaskStatus.DONE, epic2.getId());
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

        taskManager.removeSubtaskById(4);

        System.out.println("Все подзадачи после удаления по id:");
        for (Subtask subtask: taskManager.getAllSubtasks()){
            System.out.println(subtask);
        }

        taskManager.removeEpicById(2);

        System.out.println("Все эпики после удаления по id:");
        for (Epic epic: taskManager.getAllEpics()){
            System.out.println(epic);
        }

        System.out.println("Все подзадачи после удаления эпика по id:");
        for (Subtask subtask: taskManager.getAllSubtasks()){
            System.out.println(subtask);
        }
    }
}
