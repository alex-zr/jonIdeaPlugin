package domain;

import java.util.List;

/**
 * Created by User on 29.11.2016.
 */
public class Task {

    private Long id;
    private String sprintName;
    private String name;
    private String taskInfo;
    private List<TaskFile> taskFile;
    private String packageName;/*name*/
    private Long templateId;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public List<TaskFile> getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(List<TaskFile> taskFile) {
        this.taskFile = taskFile;
    }
    
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}
