import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private List<Task> history = new ArrayList<>();
    private Map<Integer, Task> tasks = new HashMap<>();

    @Override
    public void add(Task task){
        if (task != null){
            if (!history.contains(task)){
                history.add(task);
                if (history.size()>10){
                    history.remove(0);
                }
            }
        }
    }


    @Override
    public List<Task> getHistory(){
        return new ArrayList<>(history);
    }

    public void setTasks(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }
}
