package actions;

import service.SwingTaskTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.logging.*;

/**
 * Created by ${BIM} on 11.11.2016.
 */
public class TaskDownloader extends AnAction {
    private static Logger log = Logger.getLogger(TaskDownloader.class.getName());

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        SwingTaskTable swingTaskTable = new SwingTaskTable(anActionEvent);
        swingTaskTable.createScen();
    }
}
