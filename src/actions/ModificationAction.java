package actions;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.SourcePathsBuilder;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

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