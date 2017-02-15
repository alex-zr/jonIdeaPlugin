package domain;

/**
 * Created by User on 29.11.2016.
 */
public class Task {

    private Long id;
    private String sprintName;
    private String name;
    private String taskInfo;
    private TaskFile taskFile;
    private String className; /*name*/
    private String PackageName;/*name*/

    public Task() {
    }

    public Task(Long id, String sprintName, String name, String taskInfo, TaskFile taskFile, String className, String packageName) {
        this.id = id;
        this.sprintName = sprintName;
        this.name = name;
        this.taskInfo = taskInfo;
        this.taskFile = taskFile;
        this.className = className;
        PackageName = packageName;
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

    public TaskFile getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(TaskFile taskFile) {
        this.taskFile = taskFile;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }
}
