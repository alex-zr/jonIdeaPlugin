package ConrolPack;

import Sprints_Tasks.Task;
import Sprints_Tasks.TaskFile;

/**
 * Created by User on 29.11.2016.
 */
public class TableInterfaceInfo {

    private String taskName;
    private String taskInfo;
    private TaskFile taskFile;
    private Task task;

    public TableInterfaceInfo() {
    }

    public TableInterfaceInfo(Task task, String taskName, String taskInfo, TaskFile taskFile) {
        this.task = task;
        this.taskName = taskName;
        this.taskInfo = taskInfo;
        this.taskFile = taskFile;
    }


    public void setTask(String task) {
        this.taskName = task;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskFile getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(TaskFile taskFile) {
        this.taskFile = taskFile;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
