import service.SwingTaskTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

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

        SwingTaskTable swingTaskTable = new SwingTaskTable(anActionEvent);
        swingTaskTable.createScen();

    }
}
