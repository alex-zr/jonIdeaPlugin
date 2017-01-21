package ConrolPack;

import Sprints_Tasks.Sprint;
import Sprints_Tasks.Task;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.table.JBTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by ${BIM} on 06.12.2016.
 */
public class SwingTaskTable {

    public SwingTaskTable(AnActionEvent anActionEvent) {
        this.anActionEvent = anActionEvent;
    }

    private ObservableList<TableInterfaceInfo> interfaceInfoData;
    private AnActionEvent anActionEvent;
    private ArrayList<Task> taskList;
    private ArrayList<Sprint> sprints;
    private Task task;
    private TableInterfaceInfo tableInterfaceInfo;
    private JTable jTable;

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
        TableColumn tablecolumn4 = new TableColumn();

        setjTable(new JBTable(model));
        getjTable().setRowHeight(80);
        getjTable().setSize(jFrame.getWidth(), jFrame.getHeight());
        RemoteService remoteService = new RemoteService();
        MultiLineCellRenderer multiLineCellRenderer = new MultiLineCellRenderer();


        model.addColumn(tablecolumn1, new String[]{"Задание"});
        model.addColumn(tablecolumn2, new String[]{"Описание"});
        model.addColumn(tablecolumn3, new String[]{"Оценка"});
        model.addColumn(tablecolumn4, new Task[]{});
        /*http://javatalks.ru/topics/43915?page=1#224416 для высоты ячейки*/
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        getjTable().setSize(jFrame.getWidth(), jFrame.getHeight());


        jFrame.getContentPane().add(getjTable());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        jFrame.setSize((int) (width - 150), (int) (height - 100));

        setInterfaceInfoData(FXCollections.observableArrayList());

        setSprints(remoteService.getUserSprints("User", "User"));


        /*Нужно переписать...!!!! getInterfaceInfoData() отрабатывает не корректно*/
        for (Sprint sprint : getSprints()) {
            for (Task task1 : sprint.getTasks()) {
                setTableInterfaceInfo(new TableInterfaceInfo(task1.getName(), task1.getTaskInfo(), task1.getTaskFile()));
                model.addRow(new Object[]{getTableInterfaceInfo().getTaskName(), task1.getTaskInfo(), "", task1});

                for (int w = 0; w < getjTable().getColumnCount(); w++) {
                    getjTable().getColumnModel().getColumn(w).setCellRenderer((TableCellRenderer) multiLineCellRenderer.getTableCellRendererComponent(getjTable(), getTableInterfaceInfo().getTaskInfo(), false, false, w, 2));

                    if (w == 3) {
                        getjTable().getColumnModel().getColumn(w).setMaxWidth(0);
                        getjTable().getColumnModel().getColumn(w).setMinWidth(0);
                        getjTable().getColumnModel().getColumn(w).setWidth(0);
                    }
                }
            }
        }
/*
        for (int i = 0; i < getSprints().size(); i++) {
            setTaskList(getSprints().get(i).getTasks());

         //   setTaskList(getSprints().get(i).getTasks());

            for (int z = 0; z < getTaskList().size(); z++) {
                getInterfaceInfoData().add(new TableInterfaceInfo(getTaskList().get(z).getName(), getTaskList().get(z).getTaskInfo(), getTaskList().get(z).getTaskFile()));
                model.addRow(new Object[]{getInterfaceInfoData().get(z).getTaskName(), getInterfaceInfoData().get(z).getTaskInfo()});
            }
        //    getInterfaceInfoData().removeAll();
        }
*/


        getjTable().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JTable table = (JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    jFrame.setVisible(false);
                    if (row == 0) {
                        row = 1;
                    }
                    //               setTask(getTaskList().get(row - 1));

                    setTask((Task) getjTable().getModel().getValueAt(row, 3));

                    CreateStructure createStructure = new CreateStructure(getAnActionEvent(), getTask());
                    createStructure.createStructereMethod();
                }
            }
        });
        jFrame.setVisible(true);
    }

    class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
        public MultiLineCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setFont(table.getFont());
            if (hasFocus) {
                setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
                if (table.isCellEditable(row, column)) {
                    setForeground(UIManager.getColor("Table.focusCellForeground"));
                }
            } else {
                setBorder(new EmptyBorder(1, 2, 1, 2));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    public SwingTaskTable() {
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

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(ArrayList<Sprint> sprints) {
        this.sprints = sprints;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TableInterfaceInfo getTableInterfaceInfo() {
        return tableInterfaceInfo;
    }

    public void setTableInterfaceInfo(TableInterfaceInfo tableInterfaceInfo) {
        this.tableInterfaceInfo = tableInterfaceInfo;
    }

    public JTable getjTable() {
        return jTable;
    }

    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }
}