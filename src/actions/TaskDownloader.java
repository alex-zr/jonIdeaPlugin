package actions;

import service.SwingTaskTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by ${BIM} on 11.11.2016.
 */
public class TaskDownloader extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        SwingTaskTable swingTaskTable = new SwingTaskTable(anActionEvent);
        swingTaskTable.createScen();
    }
}
