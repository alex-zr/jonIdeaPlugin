package actions;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.roots.*;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by ${BIM} on 04.01.2017.
 */


/* Json объект.
    Отправка: ид задания, список объектов файлов (название класса, текст класса)
*/
public class TaskCheker extends AnAction {
    private static final String MODULE_SOURCE_ROOT_KEY = "java.module.default.source.root";

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        String path = anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE).getPath();

        File taskFile = new File(path);
        try {

            List<String> solvedTask = Files.readAllLines(Paths.get(path), Charset.defaultCharset());

            System.out.println(solvedTask.get(0));
/*
            JSONObject jo = new JSONObject();
            jo.append(taskFile.getName(), taskFile);*/
            // System.out.println(solwedTask);
            // System.out.println(jo);

            /*String actionText = anActionEvent.getActionManager().getAction("ReformatCode").getTemplatePresentation().getText();
            System.out.println(actionText);*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
