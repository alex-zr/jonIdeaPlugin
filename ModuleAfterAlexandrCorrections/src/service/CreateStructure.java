package service;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import domain.Task;

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

            if (virtualFile.getName().equals("src")) {

                if (virtualFile.getChildren().length == 0) {
                    ApplicationManager.getApplication().runWriteAction(() -> {
                        VirtualFile mainFolder;
                        try {
                            mainFolder = virtualFile.createChildDirectory(null, "main");
                            project.getBaseDir().refresh(true, true);
                            VirtualFile javaFolder = mainFolder.createChildDirectory(null, "java");

                            Module module = ModuleUtilCore.findModuleForFile(javaFolder, project);
                            ModifiableRootModel model = ModuleRootManager.getInstance(module).getModifiableModel();

                            ContentEntry contentEntry = model.addContentEntry(project.getBaseDir());
                            contentEntry.addSourceFolder(javaFolder, false);
                            model.commit();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

            for (int z = 0; z < virtualFile.getChildren().length; z++) {
                VirtualFile virtualFile1 = virtualFile.getChildren()[z].getCanonicalFile();

                for (int w = 0; w < virtualFile1.getChildren().length; w++) {
                    VirtualFile folderComponent = virtualFile1.getChildren()[w];

                    if (folderComponent.getName().equals("java")) {

                        String taskUrl = getChousenTask().getTaskFile().get(0).getFileUrl();
                        VirtualFile finalFolder = null;

                        finalFolder = structureCreation(folderComponent, taskUrl);

                        for (int f = 0; f < getChousenTask().getTaskFile().size(); f++) {
                            createFileMethod(finalFolder, getChousenTask());
                        }
                        project.getBaseDir().refresh(true, true);
                    }
                }
            }
        }
    }

    public VirtualFile structureCreation(VirtualFile virtualFile, String subDirectories) {
        String[] subDir = subDirectories.split("/");

        if (subDir.length > 1) {
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
        } else {
            final VirtualFile finalVirtualFile;
            finalVirtualFile = virtualFile;
            ApplicationManager.getApplication().runWriteAction(() -> {
                try {
                    finalVirtualFile.createChildDirectory(null, subDirectories);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            virtualFile = finalVirtualFile.findChild(subDirectories);
        }

        return virtualFile;
    }

    void createFileMethod(VirtualFile virtualFile, Task task) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                VirtualFile classFile = null;
                String taskText = null;
                for (int i = 0; i < task.getTaskFile().size(); i++) {
                    classFile = virtualFile.findOrCreateChildData(null, task.getTaskFile().get(i).getClassname() + ".java");
                    taskText = task.getTaskFile().get(i).getFileComponent();
                }

                Charset utf8 = Charset.forName("UTF-8");
                classFile.setCharset(utf8);
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
