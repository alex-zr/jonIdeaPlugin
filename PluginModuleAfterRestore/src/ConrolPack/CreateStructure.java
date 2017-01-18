package ConrolPack;

import Sprints_Tasks.Task;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by ${BIM} on 11.12.2016.
 */

public class CreateStructure {

    public CreateStructure(AnActionEvent anActionEvent, Task task) {
        this.anActionEvent = anActionEvent;
        this.chousenTask = task;
    }

    public void createStructereMethod() {

        final Project project = getAnActionEvent().getData(CommonDataKeys.PROJECT);

        for (int i = 0; i < project.getBaseDir().getChildren().length; i++) {
            VirtualFile virtualFile = project.getBaseDir().getChildren()[i].getCanonicalFile();

            for (int z = 0; z < virtualFile.getChildren().length; z++) {
                VirtualFile virtualFile1 = virtualFile.getChildren()[z].getCanonicalFile();

                if (virtualFile1.getName().equals("main")) {

                    for (int w = 0; w < virtualFile1.getChildren().length; w++) {
                        VirtualFile folderComponent = virtualFile1.getChildren()[w];

                        if (folderComponent.getName().equals("java")) {

                            String taskUrl = getChousenTask().getTaskFile().getFileUrl();

                            VirtualFile finalFolder = structureCreation(folderComponent, taskUrl);
                            createFileMethod(finalFolder, getChousenTask());
                            project.getBaseDir().refresh(true, true);
                        }
                    }
                }
            }
        }
    }


    public VirtualFile structureCreation(VirtualFile virtualFile, String subDirectories) {
        String[] subDir = subDirectories.split("/");

        for (int i = 0; i < subDir.length; i++) {

            String folderName = subDir[i];
            VirtualFile subDirectory = virtualFile.findChild(folderName);
            final VirtualFile finalVirtualFile;
            if (subDirectory == null) {
                finalVirtualFile = virtualFile;
                ApplicationManager.getApplication().runWriteAction(() -> {
                    try {
                        finalVirtualFile.createChildDirectory(null, folderName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                virtualFile = finalVirtualFile.findChild(folderName);
            } else {
                virtualFile = subDirectory;
            }
        }
        return virtualFile;
    }


    void createFileMethod(VirtualFile virtualFile, Task task) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                VirtualFile classFile = virtualFile.findOrCreateChildData(null, task.getName() + ".java");
                Charset utf8 = Charset.forName("UTF-8");
                classFile.setCharset(utf8);
                String taskText = "package " + task.getTaskFile().getFileUrl().replaceFirst("/", ".") + ";\n" + task.getTaskFile().getFileComponent();
                classFile.setBinaryContent(taskText.getBytes(utf8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public CreateStructure() {
    }

    private Task chousenTask;

    private AnActionEvent anActionEvent;

    public Task getChousenTask() {
        return chousenTask;
    }

    public void setChousenTast(Task chousenTask) {
        this.chousenTask = chousenTask;
    }

    public AnActionEvent getAnActionEvent() {
        return anActionEvent;
    }

    public void setAnActionEvent(AnActionEvent anActionEvent) {
        this.anActionEvent = anActionEvent;
    }

}
