package shelterAnimal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Volunteer implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phone;
    private List<Task> assignedTasks;
    private List<Schedule> schedules;

    public Volunteer(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.assignedTasks = new ArrayList<>();
        this.schedules = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    public void assignTask(Task task){
        assignedTasks.add(task);
    }
    public void addSchedule(Schedule schedule){
        schedules.add(schedule);
    }
}
