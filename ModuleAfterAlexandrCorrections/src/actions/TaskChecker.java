package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import exception.ClassNameNotFoundException;
import exception.PackageNotFoundException;
import service.RemoteService;
import com.intellij.psi.search.FilenameIndex; // чтоб найти файл
import com.intellij.openapi.fileEditor.OpenFileDescriptor;// чтоб считать файл

/**
 * Created by ${BIM} on 04.01.2017.
 */
public class TaskChecker extends AnAction {
    private static final String MODULE_SOURCE_ROOT_KEY = "java.module.default.source.root";
    private static final String DEFAULT_MODULE_SOURCE_ROOT_PATH = "java";

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

      /*  String path = anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE).getPath();
        VirtualFile[] virtualFiles = anActionEvent.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
*/

        RemoteService remoteService = new RemoteService();
        try {
            remoteService.getUserSprints();
            System.out.println(remoteService.graduateTask(anActionEvent.getData(CommonDataKeys.EDITOR).getDocument().getText(),anActionEvent));
        } catch (ClassNameNotFoundException e) {
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            e.printStackTrace();
        }
        /*if (anActionEvent.getData(CommonDataKeys.EDITOR).getDocument() == null) {

            for (VirtualFile vf : virtualFiles) {
                try {
                    File file = new File(vf.getPath());
                    FileReader fileReader = new FileReader(file);
                    BufferedReader br = new BufferedReader(fileReader);
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        System.out.println(sCurrentLine);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //  System.out.println(LocalFileSystem.getInstance().findFileByIoFile(file).getParent().getParent().getName());
                //   LocalFileSystem.getInstance().findFileByIoFile(file).getParent().getParent().getName();
            }
        } else if (anActionEvent.getData(CommonDataKeys.EDITOR).getDocument() != null && anActionEvent.getData(CommonDataKeys.EDITOR).getDocument().getText().length() > 0) {
            System.out.println(anActionEvent.getData(CommonDataKeys.EDITOR).getDocument().getText());
        }*/


    }
}
