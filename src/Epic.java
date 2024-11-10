import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {

    private final List<Integer> subtasksId;

    public Epic(int id, String title, String description, TaskStatus status, List<Integer> subtasks) {
        super(id, title, description, status);
        this.subtasksId= new ArrayList<>(subtasks);
    }


    public void addSubtask(int subtaskId) {
        subtasksId.add(subtaskId);
    }

    public List<Integer> getSubtasks() {
        return new ArrayList<>(subtasksId);
    }

    public void setStatus(TaskStatus status){
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtasksId, epic.subtasksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasksId);
    }
}