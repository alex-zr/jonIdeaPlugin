package actions;

import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;
import service.SwingTaskTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by ${BIM} on 11.11.2016.
 */
public class ActionClassOnGit extends AnAction {
    private static Logger log = Logger.getLogger(ActionClassOnGit.class.getName());

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
/*
        Project project = anActionEvent.getProject();


        VirtualFile virtualFile = project.getBaseDir();

        for (int i = 0; i < virtualFile.getChildren().length; i++) {
            //   System.out.println(virtualFile.getChildren()[i].getName());

            if (virtualFile.getChildren()[i].getName().equals("src")) {

                System.out.println(virtualFile.getChildren()[i].getName());
            }
        }*/

        setSourceFolder(anActionEvent, "java");
        showSourceFolders(anActionEvent);

        SwingTaskTable swingTaskTable = new SwingTaskTable(anActionEvent);
        swingTaskTable.createScen();

    }

    private void showSourceFolders(AnActionEvent anActionEvent) {

        Project project = anActionEvent.getProject();
        String projectName = project.getName();
        StringBuilder sourceRootsList = new StringBuilder();
        VirtualFile[] vFiles = ProjectRootManager.getInstance(project).getContentSourceRoots();
        for (VirtualFile file : vFiles) {
            sourceRootsList.append(file.getUrl()).append("\n");
        }

        Messages.showInfoMessage("Source roots for the " + projectName + " plugin:\n" + sourceRootsList, "Project Properties");

    }

    private void setSourceFolder(final AnActionEvent event, final String srcName) {
        Project project = event.getProject();
        if (project == null) return;
        Navigatable element = event.getData(CommonDataKeys.NAVIGATABLE);
        if (element instanceof PsiDirectory) {
            VirtualFile file = ((PsiDirectory) element).getVirtualFile();
            Module module = ModuleUtilCore.findModuleForFile(file, project);
            if (module == null) {
                Messages.showErrorDialog("Module not found " + file.getPath(), "Source folder settings");
            }

            ModifiableRootModel model = ModuleRootManager.getInstance(module).getModifiableModel();
            VirtualFile baseDir = project.getBaseDir();

            if (FileUtil.exists(baseDir.getPath() + File.separator + srcName)) {
                return;
            }
            
            VirtualFile contentRoot = null;
            try {
                contentRoot = baseDir.createChildDirectory(null, srcName);
            } catch (IOException e) {
                Messages.showErrorDialog("Can't create folder " + file.getPath(), "Source folder settings");
                e.printStackTrace();
                return;
            }

            if (contentRoot == null) {
                Messages.showErrorDialog("Module not found " + file.getPath(), "Source folder settings");
            }
            ContentEntry contentEntry = model.addContentEntry(baseDir);
            boolean isTestRoot = false;
            contentEntry.addSourceFolder(contentRoot, isTestRoot);
            model.commit();

        }
    }
}
