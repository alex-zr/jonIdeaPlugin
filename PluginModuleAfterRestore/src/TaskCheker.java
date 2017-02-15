
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by ${BIM} on 04.01.2017.
 */


/* Json объект.
    Отправка: ид задания, список объектов файлов (название класса, текст класса)
*/
public class TaskCheker extends AnAction {
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
