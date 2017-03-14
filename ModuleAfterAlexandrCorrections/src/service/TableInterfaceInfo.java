package service;

import domain.TaskFile;

/**
 * Created by User on 29.11.2016.
 */
public class TableInterfaceInfo {

    private String taskName;
    private String taskInfo;
    private TaskFile taskFile;

    public TableInterfaceInfo() {
    }

    public TableInterfaceInfo(String taskName, String taskInfo, TaskFile taskFile) {
        this.taskName = taskName;
        this.taskInfo = taskInfo;
        this.taskFile = taskFile;
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
}
