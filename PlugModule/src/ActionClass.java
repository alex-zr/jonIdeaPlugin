import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by ${BIM} on 11.11.2016.
 */


/*Вопросы
* При создании покате в СРЦ и помещении класса в пакет теряются пути к классам в xml
* (ПЕренести кнопку r RUN)
* Вывести структуру модуля
* JavaRush осмотреть плагин
*
*   ДЛЯ ПЛАГИНА НАчало ПРОГИ JavaRushTaskChooser
*      Взять код структуры проекта из консоли
*      Если папка с таким именем существует то оставляем как есть
*      Если папка с таким именем не существует существует то:
*                   1) Создать Файлы
*                   2) Записать в них инфо из консоли
*                   3) Создать папку с нужным именем
*                   4) Поместить файлы в папку
*      Кнопка создающая проект
*
* */
public class ActionClass extends AnAction {
    private static Logger log = Logger.getLogger(ActionClass.class);

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        String userName = System.getProperty("user.name");
        File fileForDate = new File("C:\\Users\\" + userName + "\\Desktop\\fileForDate");
        FileWriter fileWriter = null;

        if (!fileForDate.exists()) {
            fileForDate.mkdir();
        }
        try {
            System.out.println(" ================================ Parsing plug structure ================================");
            final Project project = anActionEvent.getData(CommonDataKeys.PROJECT);
            // final Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR); // сохранить как заготовку
            // System.out.println(editor.getDocument().getText());// Получаем текст с окна ввода кода человека

            System.out.println("name:= " + project.getProjectFile().getName());
            for (int i = 0; i < project.getBaseDir().getChildren().length; i++) {
                VirtualFile virtualFile = project.getBaseDir().getChildren()[i].getCanonicalFile();
                System.out.println("File from:= " + virtualFile.getParent() + " file name:= " + virtualFile.getName());

                for (int z = 0; z < virtualFile.getChildren().length; z++) {
                    VirtualFile virtualFile1 = virtualFile.getChildren()[z].getCanonicalFile();
                    System.out.println("File from:= " + virtualFile1.getParent() + " file name:= " + virtualFile1.getName());


                    for (int y = 0; y < virtualFile1.getChildren().length; y++) {
                        VirtualFile virtualFile2 = virtualFile1.getChildren()[y].getCanonicalFile();
                        System.out.println("File from:= " + virtualFile2.getParent() + " file name:= " + virtualFile2.getName());

                        for (int f = 0; f < virtualFile2.getChildren().length; f++) {

                            File fil2 = new File(virtualFile2.getChildren()[f].getCanonicalPath());

                            System.out.println(fil2.getParent());
                            File classFile = new File(fil2.getParent(), "ClassFile.java");

                            classFile.createNewFile();
                            String line1 = "public class ClassFile{\n public static void main(String[] args){\n for(int i=0;i<100;i++){\n System.out.println(\" Hello \");}\n}\n}";
                            String classText = null;
                            BufferedReader fin2 = new BufferedReader(new StringReader(line1));

                            fileWriter = new FileWriter(classFile);

                            while ((classText = fin2.readLine()) != null) {
                                // System.out.println(line1);
                                fileWriter.write(classText);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
