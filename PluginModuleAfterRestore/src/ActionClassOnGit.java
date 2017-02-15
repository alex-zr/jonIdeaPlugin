import ControlPack.SwingTaskTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.externalSystem.model.project.ProjectData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.openapi.vfs.newvfs.VirtualFileFilteringListener;

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
