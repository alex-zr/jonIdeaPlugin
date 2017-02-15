package ControlPack;

import Sprints_Tasks.Task;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vcs.checkout.CheckoutFolderToTheSameFolder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.openapi.vfs.impl.VirtualFileManagerImpl;
import com.intellij.openapi.vfs.newvfs.FileAttribute;
import com.intellij.openapi.vfs.newvfs.VirtualFileFilteringListener;

import javax.help.plaf.basic.BasicFavoritesNavigatorUI;
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

         /*   if (virtualFile.getName().equals("src")) {
                FileAttribute fileAttribute = new FileAttribute("resources");
                fileAttribute.writeAttribute(virtualFile);
                System.out.println(virtualFile);
            }*/
            for (int z = 0; z < virtualFile.getChildren().length; z++) {
                VirtualFile virtualFile1 = virtualFile.getChildren()[z].getCanonicalFile();

                if (virtualFile1.getName().equals("main")) {

                    for (int w = 0; w < virtualFile1.getChildren().length; w++) {
                        VirtualFile folderComponent = virtualFile1.getChildren()[w];

                        if (folderComponent.getName().equals("java")) {

                            VirtualFileSystem vfs = folderComponent.getFileSystem();


                            String taskUrl = getChousenTask().getTaskFile().getFileUrl();

                            String taskName = getChousenTask().getName();

                            VirtualFile finalFolder = structureCreation(folderComponent, taskUrl, taskName);
                            createFileMethod(finalFolder, getChousenTask());
                            project.getBaseDir().refresh(true, true);
                        }
                    }
                }
            }
        }
    }

    void createFileMethod(VirtualFile virtualFile, Task task) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                VirtualFile classFile = virtualFile.findOrCreateChildData(null, task.getTaskFile().getFileUrl().split("/")[task.getTaskFile().getFileUrl().split("/").length - 1] + ".java");
                Charset utf8 = Charset.forName("UTF-8");
                classFile.setCharset(utf8);
                String taskText = task.getTaskFile().getFileComponent(); //"package " + task.getTaskFile().getFileUrl().replaceFirst("/", ".") + ";\n" + task.getTaskFile().getFileComponent();
                classFile.setBinaryContent(taskText.getBytes(utf8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public VirtualFile structureCreation(VirtualFile virtualFile, String subDirectories, String fileNAme) {
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
