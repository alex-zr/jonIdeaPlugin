package domain;

/**
 * Created by ${BIM} on 13.12.2016.
 */
public class TaskFile {

    public TaskFile(String classname, String fileUrl, String fileContent) {
        this.fileUrl = fileUrl;
        this.fileContent = fileContent;
        this.classname = classname;
    }

    private String classname;
    private String fileUrl;
    private String fileContent;

    public TaskFile() {
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileComponent() {
        return fileContent;
    }

    public void setFileComponent(String fileContent) {
        this.fileContent = fileContent;
    }
}
