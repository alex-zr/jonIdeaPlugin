package ConrolPack;

import Sprints_Tasks.Sprint;
import Sprints_Tasks.Task;
import Sprints_Tasks.TaskFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29.11.2016.
 */
public class RemoteService {

    public RemoteService() {
    }

    public RemoteService(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks = new ArrayList<Task>();
    private Task task1_1 = new Task("sprint1", "Task1Name", "Вывести на экран надпись «Жить хорошо, а хорошо жить еще лучше»", new TaskFile("sprint1/Task1Name", "public class Task1Name{\n  public static void main(String[] args){\n System.out.println( \" Hello1 \" );}\n}"));
    private Task task1_1_1 = new Task("sprint1", "Task1_2Name", "Вывести на экран надпись «Жить хорошо, а хорошо жить еще лучше»", new TaskFile("sprint1/Task1Name", "public class Task1_2Name{\n public static void main(String[] args){\n System.out.println(\" Hello1_2 \");}\n}"));

    private Task task1_2 = new Task("sprint1", "Task2Name", "Используя цикл вывести на экран сто раз надпись:«Я никогда не буду работать за копейки. Амиго»", new TaskFile("sprint1/Task2Name", "public class Task2Name{\n public static void main(String[] args){\n System.out.println(\" Hello2 \");}\n}"));
    private Task task2_1 = new Task("sprint2", "Task1Name", "Ввести с клавиатуры число n.\nВывести на экран надпись «Я буду зарабатывать $n в час».\nПример:\nЯ буду зарабатывать $50 в час", new TaskFile("sprint2/Task1Name", "\n public class Task1Name{\n public static void main(String[] args){\n System.out.println(\" Hello3 \");}\n}"));
    private Task task2_2 = new Task("sprint2", "Task2Name", "Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.\n", new TaskFile("sprint2/Task2Name", "public class Task2Name{\n" + "public static void main(String[] args){\n System.out.println(\" Hello4 \");}\n}"));
    private Task task2_3 = new Task("sprint2", "Task3Name", "Программа вводит два числа с клавиатуры и выводит их сумму на экран.", new TaskFile("sprint2/Task3Name", "public class Task3Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello5 \");}\n}"));
    private Task task2_4 = new Task("sprint2", "Task4Name", "Подумай, что должен возвращать метод toString в классах Cat и Dog?\n В методе main создай по два объекта каждого класса и выведи их на экран.\nОбъекты класса Duck уже созданы и выводятся на экран.", new TaskFile("sprint2/Task4Name", "public class Task4Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello6 \");}\n}"));


    public List<Sprint> getUserSprints(String login, String pass) {
       /*модулируем ситуация в которой нам пришел список Sprints_Tasks.Task*/

        getTasks().add(task1_1);
        getTasks().add(task1_2);
        getTasks().add(task2_1);
        getTasks().add(task2_2);
        getTasks().add(task2_3);
        getTasks().add(task2_4);
        getTasks().add(task1_1_1);
      /*====================================================================*/

        /*Боевая часть метода*/

        List<Sprint> sprintList = new ArrayList<Sprint>();
        Sprint sprint = new Sprint();
        sprint.setTasks(getTasks());

        sprint.setName("Название спринта");

        sprintList.add(sprint);
        return sprintList;
    }

}
