import ConrolPack.SwingTaskTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.apache.log4j.Logger;

/**
 * Created by ${BIM} on 11.11.2016.
 */

/*Вопросы
* При создании покате в СРЦ и помещении класса в пакет теряются пути к классам в xml
* (ПЕренести кнопку r RUN)  +
* Вывести структуру модуля  +
* JavaRush осмотреть плагин +
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
*
*
* Куда писать код присланій вчера в скайпе???
*
* */
public class ActionClassOnGit extends AnAction {
    private static Logger log = Logger.getLogger(ActionClassOnGit.class);

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        // final Editor editor = anActionEvent.getData(CommonDataKeys.EDITOR); // сохранить как заготовку
        // System.out.println(editor.getDocument().getText());// Получаем текст с окна ввода кода человека
        SwingTaskTable swingTaskTable = new SwingTaskTable(anActionEvent);
        swingTaskTable.createScen();
    }
}
