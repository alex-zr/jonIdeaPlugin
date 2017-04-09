package service;

import com.intellij.openapi.actionSystem.AnActionEvent;
import domain.Sprint;
import domain.Task;
import exception.ClassNameNotFoundException;
import exception.PackageNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ${BIM} on 06.12.2016.
 */

public class SwingTaskTable {

    public SwingTaskTable(AnActionEvent anActionEvent) {
        this.anActionEvent = anActionEvent;
    }

    private AnActionEvent anActionEvent;
    private Task task;
    private Map<Long, Task> taskMap;
    private JButton jButton;

    public void createScen() {
        JFrame jFrame = new JFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        jFrame.setSize((int) (width - 150), (int) (height - 100));

        RemoteService remoteService = new RemoteService();
        JPanel panel = new JPanel();
        panel.setAutoscrolls(true);
        panel.setSize(jFrame.getSize());
        setTaskMap(new HashMap());
        try {
            for (Sprint sprint : remoteService.getUserSprints()) {

                for (int i = 0; i < sprint.getTasks().size(); i++) {

                    setTask(sprint.getTasks().get(i));

                    getTaskMap().put(getTask().getId(), getTask());
                    setjButton(new JButton());
                    Dimension buttonPrefSize = new Dimension();
                    buttonPrefSize.setSize(200, 150);
                    getjButton().setPreferredSize(buttonPrefSize);
                    getjButton().setSize(200, 150);

                    getjButton().setText(getTask().getName());
                    panel.add(getjButton());

                    getjButton().addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent me) {

                            if (me.getClickCount() == 2) {
                                JButton jButton = (JButton) me.getSource();

                                Iterator<Map.Entry<Long, Task>> taskIterator = getTaskMap().entrySet().iterator();

                                while (taskIterator.hasNext()) {
                                    setTask(taskIterator.next().getValue());

                                    if (getTask().getName().equals(jButton.getText())) {
                                        jFrame.setVisible(false);
                                        CreateStructure createStructure = new CreateStructure(getAnActionEvent(), getTask());
                                        createStructure.createStructereMethod();
                                    }
                                }
                            }
                        }
                    });
                }
            }
        } catch (ClassNameNotFoundException e) {
            e.printStackTrace();
        } catch (PackageNotFoundException e) {
            e.printStackTrace();
        }
        jFrame.add(panel);
        jFrame.setVisible(true);
    }

    public SwingTaskTable() {
    }

    public AnActionEvent getAnActionEvent() {
        return anActionEvent;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }

    public Map<Long, Task> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<Long, Task> taskMap) {
        this.taskMap = taskMap;
    }
}