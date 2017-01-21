package Sprints_Tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29.11.2016.
 */
public class Sprint {

    private String name;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Sprint() {
    }

    public Sprint(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Sprints_Tasks.Sprint [name=" + name + ", tasks=" + tasks + "]";
    }

}
