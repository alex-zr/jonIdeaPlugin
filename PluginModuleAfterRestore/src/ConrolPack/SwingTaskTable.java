package ConrolPack;

import Sprints_Tasks.Sprint;
import Sprints_Tasks.Task;
import Sprints_Tasks.TaskFile;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.table.JBTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by ${BIM} on 06.12.2016.
 */
public class SwingTaskTable {

    public SwingTaskTable() {
    }

    public SwingTaskTable(AnActionEvent anActionEvent) {
        this.anActionEvent = anActionEvent;
    }

    private ObservableList<TableInterfaceInfo> interfaceInfoData;
    private AnActionEvent anActionEvent;
    private TaskFile taskFile;
    private List<Task> taskList;
    private List<Sprint> sprints;
    private Task task;

    public void createScen() {
        JFrame jFrame = new JFrame();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return row == 1;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        TableColumn tablecolumn1 = new TableColumn();
        TableColumn tablecolumn2 = new TableColumn();
        TableColumn tablecolumn3 = new TableColumn();
        JTable jTable = new JBTable(model);


        RemoteService remoteService = new RemoteService();

        model.addColumn(tablecolumn1, new String[]{"Задание"});
        model.addColumn(tablecolumn2, new String[]{"Описание"});
        model.addColumn(tablecolumn3, new String[]{"Оценка"});
        /*http://javatalks.ru/topics/43915?page=1#224416 для высоты ячейки*/
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setEnabled(false);

        jTable.setSize(jFrame.getWidth(), jFrame.getHeight());

        jFrame.getContentPane().add(jTable);
        jFrame.setSize(800, 600);

        setInterfaceInfoData(FXCollections.observableArrayList());
        setSprints(remoteService.getUserSprints("User", "User"));

        for (int i = 0; i < getSprints().size(); i++) {
            setTaskList(getSprints().get(i).getTasks());

            for (int z = 0; z < getTaskList().size(); z++) {
                getInterfaceInfoData().add(new TableInterfaceInfo(getTask(), getTaskList().get(z).getName(), getTaskList().get(z).getTaskInfo(), getTaskList().get(z).getTaskFile()));

                model.addRow(new Object[]{getInterfaceInfoData().get(z).getTaskName(), getInterfaceInfoData().get(z).getTaskInfo()});
            }
        }

        for (int r = 1; r < jTable.getRowCount(); r++) {
            jTable.setRowHeight(r, 45);
        }

        for (int w = 0; w < jTable.getColumnCount(); w++) {
            jTable.getColumnModel().getColumn(w).setCellRenderer(centerRenderer);
        }

        jTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    jFrame.setVisible(false);
                    setTask(getTaskList().get(row - 1));
                    CreateStructure createStructure = new CreateStructure();
                    createStructure.createStructereMethod(getAnActionEvent(), getTask());
                }
            }
        });

        jFrame.setVisible(true);
    }

    public ObservableList<TableInterfaceInfo> getInterfaceInfoData() {
        return interfaceInfoData;
    }

    public void setInterfaceInfoData(ObservableList<TableInterfaceInfo> interfaceInfoData) {
        this.interfaceInfoData = interfaceInfoData;
    }

    public AnActionEvent getAnActionEvent() {
        return anActionEvent;
    }

    public void setAnActionEvent(AnActionEvent anActionEvent) {
        this.anActionEvent = anActionEvent;
    }

    public TaskFile getTaskFile() {
        return taskFile;
    }

    public void setTaskFile(TaskFile taskFile) {
        this.taskFile = taskFile;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}