package Sprints_Tasks;

/**
 * Created by User on 29.11.2016.
 */
public class Task {

    private String sprintName;
    private String name;
    private String taskInfo;
    private TaskFile taskFile;

    public Task() {
    }

    public Task(String sprintName, String name, String taskInfo, TaskFile taskFile) {
        this.sprintName = sprintName;
        this.name = name;
        this.taskInfo = taskInfo;
        this.taskFile = taskFile;
    }

    public String getFullName() {
        return sprintName + " " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public TaskFile getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(TaskFile taskFile) {
        this.taskFile = taskFile;
    }


    @Override
    public String toString() {
        return "Sprints_Tasks.Task [sprint =" + sprintName + ", name=" + name + ", taskFile=" + taskFile + "]";
    }
}
