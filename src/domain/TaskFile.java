package domain;


/**
 * Created by ${BIM} on 13.12.2016.
 */
public class TaskFile {

    public TaskFile(String fileUrl, String fileContent) {
        this.fileUrl = fileUrl; /*d:/rty/dfgh/ghj */
        this.fileContent = fileContent; /*Что угодно*/
    }

    private String fileUrl;
    private String fileContent;

    public TaskFile() {
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
