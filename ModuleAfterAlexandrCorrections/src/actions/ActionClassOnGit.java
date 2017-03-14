package actions;

import domain.Sprint;
import exception.ClassNameNotFoundException;
import exception.PackageNotFoundException;
import service.SwingTaskTable;
import service.RemoteService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.util.List;
import java.util.logging.*;

/**
 * Created by ${BIM} on 11.11.2016.
 */
public class ActionClassOnGit extends AnAction {
    private static Logger log = Logger.getLogger(ActionClassOnGit.class.getName());

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        SwingTaskTable swingTaskTable = new SwingTaskTable(anActionEvent);
        swingTaskTable.createScen();
    }
}
