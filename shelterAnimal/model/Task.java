package shelterAnimal.model;

import java.time.LocalDate;

public class Task {
    private String taskId;
    private String description;
    private LocalDate dueDate;
    private boolean completed;
    private String volunteerId;

    public Task(String taskId, String description, LocalDate dueDate, String volunteerId) {
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.volunteerId = volunteerId;
        this.completed = false;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void complete(){
        this.completed = true;
    }
}
