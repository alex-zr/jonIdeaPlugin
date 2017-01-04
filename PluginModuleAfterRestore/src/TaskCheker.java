import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;

/**
 * Created by ${BIM} on 04.01.2017.
 */

/*Для проверки ДЗ*/
public class TaskCheker extends com.intellij.openapi.actionSystem.AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {


        final Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR);
        System.out.println(editor.getDocument().getText());
    }
}
