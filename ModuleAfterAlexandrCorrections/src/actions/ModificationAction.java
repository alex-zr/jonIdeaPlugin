package actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 15.02.17
 */
public class ModificationAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull final AnActionEvent event) {

        setSourceFolder(event);

    }

    private void setSourceFolder(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) return;
        Navigatable element = event.getData(CommonDataKeys.NAVIGATABLE);
        if (element instanceof PsiDirectory) {
            VirtualFile file = ((PsiDirectory) element).getVirtualFile();
            Module module = ModuleUtilCore.findModuleForFile(file, project);
            if (module == null) return;

            ModifiableRootModel model = ModuleRootManager.getInstance(module).getModifiableModel();
            VirtualFile baseDir = project.getBaseDir();

            VirtualFile contentRoot = null;
            try {
                contentRoot = baseDir.createChildDirectory(null, "java");
            } catch (IOException e) {
                e.printStackTrace();
            }

            ContentEntry contentEntry = model.addContentEntry(baseDir);
            boolean isTestRoot = false;
            contentEntry.addSourceFolder(contentRoot, isTestRoot);
            model.commit();

        }
    }
}