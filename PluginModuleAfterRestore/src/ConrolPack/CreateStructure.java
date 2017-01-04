package ConrolPack;

import Sprints_Tasks.Task;
import com.intellij.history.utils.RunnableAdapter;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.*;
import java.util.Properties;


/**
 * Created by ${BIM} on 11.12.2016.
 */
public class CreateStructure {

    private Task chousenTast;

    public void createStructereMethod(AnActionEvent anActionEvent, Task task) {

        setChousenTast(task);

        final Project project = anActionEvent.getData(CommonDataKeys.PROJECT);

        for (int i = 0; i < project.getBaseDir().getChildren().length; i++) {
            VirtualFile virtualFile = project.getBaseDir().getChildren()[i].getCanonicalFile();

            for (int z = 0; z < virtualFile.getChildren().length; z++) {
                VirtualFile virtualFile1 = virtualFile.getChildren()[z].getCanonicalFile();

                if (virtualFile1.getName().equals("main")) {

                    for (int w = 0; w < virtualFile1.getChildren().length; w++) {
                        VirtualFile folderComponent = virtualFile1.getChildren()[w];

                        if (folderComponent.getName().equals("java")) {

                            String taskUrl = getChousenTast().getTaskFile().getFileUrl();

                            new File(folderComponent.getCanonicalPath(), taskUrl).mkdirs();

                            createFileMethod(folderComponent.getCanonicalPath() + "/" + getChousenTast().getTaskFile().getFileUrl(), getChousenTast());
                        }
                    }
                }
            }
        }
    }


/*    public void recurssion(int iteration, VirtualFile vf, int pathSize, Task task, String lastFolder) {
        for (VirtualFile file1 : vf.getChildren()) {

            if (file1.getName().equals(lastFolder)) {
                createFileMethod(file1.getCanonicalPath(), task);
                break;
            }
            recurssion(iteration, file1, pathSize, task, lastFolder);
        }
        return;
    }*/

    public void createFileMethod(String path, Task task) {

        FileWriter fileWriter = null;
        String filename = task.getName() + ".java";
        File classFile = new File(path, filename);

        try {
            classFile.createNewFile();
            fileWriter = new FileWriter(classFile);

            String taskText = task.getTaskFile().getFileComponent();
            String packageText = task.getTaskFile().getFileUrl().replace("/", ".");
            BufferedReader fin2 = new BufferedReader(new StringReader("package " + packageText + ";\n " + taskText));

            while ((taskText = fin2.readLine()) != null) {
                fileWriter.write(taskText);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Task getChousenTast() {
        return chousenTast;
    }

    public void setChousenTast(Task chousenTast) {
        this.chousenTast = chousenTast;
    }
}
