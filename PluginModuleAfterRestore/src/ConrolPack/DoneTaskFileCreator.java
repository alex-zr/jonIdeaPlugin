package ConrolPack;

import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by ${BIM} on 10.01.2017.
 */
public class DoneTaskFileCreator {


    public void createDineTaskFile(VirtualFile virtualFile) {
        for (int i = 0; i < virtualFile.getChildren().length; i++) {

            if (virtualFile.getChildren()[i].getName().equals("src")) {
                VirtualFile srcFolder = virtualFile.getChildren()[i];

                for (int z = 0; z < srcFolder.getChildren().length; z++) {
                    System.out.println(srcFolder.getChildren()[z].getName());
                }
            }

        }


    }

    public DoneTaskFileCreator() {
    }

}
