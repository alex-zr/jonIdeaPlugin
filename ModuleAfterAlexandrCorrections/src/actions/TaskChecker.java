package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import exception.ClassNameNotFoundException;
import exception.PackageNotFoundException;
import service.RemoteService;

/**
 * Created by ${BIM} on 04.01.2017.
 */
public class TaskChecker extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        RemoteService remoteService = new RemoteService();
        try {
            remoteService.getUserSprints();
            System.out.println(remoteService.graduateTask(anActionEvent));
        } catch (ClassNameNotFoundException e) {
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            e.printStackTrace();
        }

    }
}
