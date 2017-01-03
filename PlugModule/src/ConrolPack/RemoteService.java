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
    private Task task1_1 = new Task("sprint1", "task1_1Name", "Вывести на экран надпись «Жить хорошо, а хорошо жить еще лучше»", new TaskFile("d/rty1/dfgh1/ghj1", "public class Task1Name{\n public static void main(String[] args){\n System.out.println(\" Hello1 \");}\n}"));
    private Task task1_2 = new Task("sprint1", "task1_2Name", "Используя цикл вывести на экран сто раз надпись:«Я никогда не буду работать за копейки. Амиго»", new TaskFile("d/rty1/dfgh1/ghj2", "public class Task2Name{\n public static void main(String[] args){\n System.out.println(\" Hello2 \");}\n}"));
    private Task task2_1 = new Task("sprint2", "task2_1Name", "Ввести с клавиатуры число n.\nВывести на экран надпись «Я буду зарабатывать $n в час».\nПример:\nЯ буду зарабатывать $50 в час", new TaskFile("d/rty1/dfgh2/ghj1", ", public class Task1Name{\n public static void main(String[] args){\n System.out.println(\" Hello3 \");}\n}"));
    private Task task2_2 = new Task("sprint2", "task2_2Name", "Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.\n", new TaskFile("d/rty1/dfgh2/ghj2", "\"public class Task2Name{\n" + "public static void main(String[] args){\n System.out.println(\" Hello4 \");}\n}"));
    private Task task2_3 = new Task("sprint2", "task2_3Name", "Программа вводит два числа с клавиатуры и выводит их сумму на экран.", new TaskFile("d/rty1/dfgh2/ghj3", "public class Task4Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello6 \");}\n}"));
    private Task task2_4 = new Task("sprint2", "task2_4Name", "Подумай, что должен возвращать метод toString в классах Cat и Dog?\n В методе main создай по два объекта каждого класса и выведи их на экран.\nОбъекты класса Duck уже созданы и выводятся на экран.", new TaskFile("d/rty1/dfgh2/ghj4", "public class Task4Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello6 \");}\n}"));


    public List<Sprint> getUserSprints(String login, String pass) {
       /*модулируем ситуация в которой нам пришел список Sprints_Tasks.Task*/

        getTasks().add(task1_1);
        getTasks().add(task1_2);
        getTasks().add(task2_1);
        getTasks().add(task2_2);
        getTasks().add(task2_3);
        getTasks().add(task2_4);
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
