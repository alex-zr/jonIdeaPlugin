package ControlPack;

import java.io.*;
import java.net.*;

import Exceptions.BadCredentialsException;
import Exceptions.ClassNameNotFoundException;
import Exceptions.PackageNotFoundException;
import Sprints_Tasks.Sprint;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

//import ua.com.jon.Activator;
/*
import ua.com.jon.domain.GraduateResult;
import ua.com.jon.exceptions.BadCredentialsException;
import ua.com.jon.exceptions.ClassNameNotFoundException;
import ua.com.jon.exceptions.PackageNotFoundException;
import ua.com.jon.preference.PrefUtil;
*/


/*import Sprints_Tasks.Sprint;
import Sprints_Tasks.Task;
import Sprints_Tasks.TaskFile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;*/

/**
 * Created by User on 29.11.2016.
 */
public class RemoteService {
/*
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

    private List<Task> tasks = new ArrayList<>();

    private Task task1_1 = new Task("sprint1", "Task1Name", "Вывести на экран надпись «Жить хорошо, а хорошо жить еще лучше»", new TaskFile("sprint1/Task1Name", "public class Task1Name{\n  public static void main(String[] args){\n System.out.println( \" Hello1 \" );}\n}"));

    private Task task1_1_1 = new Task("sprint1", "Task1_2Name", "Вывести на экран надпись «Жить хорошо, а хорошо жить еще лучше»", new TaskFile("sprint1/Task1Name", "public class Task1_2Name{\n public static void main(String[] args){\n System.out.println(\" Hello1_2 \");}\n}"));

    private Task task1_2 = new Task("sprint1", "Task2Name", "Используя цикл вывести на экран сто раз надпись:«Я никогда не буду работать за копейки. Амиго»", new TaskFile("sprint1/Task2Name", "public class Task2Name{\n public static void main(String[] args){\n System.out.println(\" Hello2 \");}\n}"));

    private Task task2_1 = new Task("sprint2", "Task1Name", "Ввести с клавиатуры число n.\nВывести на экран надпись «Я буду зарабатывать $n в час».\nПример:\nЯ буду зарабатывать $50 в час", new TaskFile("sprint2/Task1Name", "\n public class Task1Name{\n public static void main(String[] args){\n System.out.println(\" Hello3 \");}\n}"));

    private Task task2_2 = new Task("sprint2", "Task2Name", "Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.\n", new TaskFile("sprint2/Task2Name", "public class Task2Name{\n" + "public static void main(String[] args){\n System.out.println(\" Hello4 \");}\n}"));

    private Task task2_3 = new Task("sprint2", "Task3Name", "Программа вводит два числа с клавиатуры и выводит их сумму на экран.", new TaskFile("sprint2/Task3Name", "public class Task3Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello5 \");}\n}"));

    private Task task2_4 = new Task("sprint2", "Task4Name", "Подумай, что должен возвращать метод toString в классах Cat и Dog?\n В методе main создай по два объекта каждого класса и выведи их на экран.\nОбъекты класса Duck уже созданы и выводятся на экран.", new TaskFile("sprint2/Task4Name", "public class Task4Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello6 \");}\n}"));


    public ArrayList<Sprint> getUserSprints(String login, String pass) {
       *//*модулируем ситуация в которой нам пришел список Sprints_Tasks.Task*//*

*//*        getTasks().add(task1_1);
        getTasks().add(task1_2);
        getTasks().add(task2_1);
        getTasks().add(task2_2);
        getTasks().add(task2_3);
        getTasks().add(task2_4);
        getTasks().add(task1_1_1);*//*
      *//*====================================================================*//*

        *//*Боевая часть метода*//*

        Sprint sprint1 = new Sprint("Sprint_1");
        sprint1.getTasks().add(new Task(sprint1.getName(), "Task1_1Name", "Вывести на экран надпись «Жить хорошо, а хорошо жить еще лучше»", new TaskFile(sprint1.getName() + "/Task1_1Name", "public class Task1_1Name{\n  public static void main(String[] args){\n System.out.println( \" Hello1 \" );}\n}")));
        sprint1.getTasks().add(new Task(sprint1.getName(), "Task1_1_2Name", "Вывести на экран надпись «Жить хорошо, а хорошо жить еще лучше»", new TaskFile(sprint1.getName() + "/Task1_1_2Name", "public class Task1_1_2Name{\n public static void main(String[] args){\n System.out.println(\" Hello1_2 \");}\n}")));
        sprint1.getTasks().add(new Task(sprint1.getName(), "Task1_2Name", "Используя цикл вывести на экран сто раз надпись:«Я никогда не буду работать за копейки. Амиго»", new TaskFile(sprint1.getName() + "/Task1_2Name", "public class Task1_2Name{\n public static void main(String[] args){\n System.out.println(\" Hello2 \");}\n}")));


        Sprint sprint2 = new Sprint("Sprint_2");

        sprint2.getTasks().add(new Task(sprint2.getName(), "Task2_1Name", "Ввести с клавиатуры число n.\n Вывести на экран надпись «Я буду зарабатывать $n в час».\n Пример:\n Я буду зарабатывать $50 в час", new TaskFile(sprint2.getName() + "/Task2_1Name", "\n public class Task2_1Name{\n public static void main(String[] args){\n System.out.println(\" Hello3 \");}\n}")));

        sprint2.getTasks().add(new Task(sprint2.getName(), "Task2_2Name", "Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.\n", new TaskFile(sprint2.getName() + "/Task2_2Name", "public class Task2_2Name{\n" + "public static void main(String[] args){\n System.out.println(\" Hello4 \");}\n}")));

        sprint2.getTasks().add(new Task(sprint2.getName(), "Task2_3Name", "Программа вводит два числа с клавиатуры и выводит их сумму на экран.", new TaskFile(sprint2.getName() + "/Task2_3Name", "public class Task2_3Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello5 \");}\n}")));

        sprint2.getTasks().add(new Task(sprint2.getName(), "Task2_4Name", "Подумай, что должен возвращать метод toString в классах Cat и Dog?\n В методе main создай по два объекта каждого класса и выведи их на экран.\n Объекты класса Duck уже созданы и выводятся на экран.", new TaskFile(sprint2.getName() + "/Task2_4Name", "public class Task2_4Name{\n public static void main(String[] args){\nSystem.out.println(\" Hello6 \");}\n}")));

        *//*боевая часть*//*
        ArrayList<Sprint> sprintList = new ArrayList<>();
        sprintList.add(sprint1);
        sprintList.add(sprint2);

        *//*Sprint sprint1 = new Sprint();
        sprint1.setTasks(getTasks());
        sprint1.setName("Уровень 1");

        Sprint sprint2 = new Sprint();
        sprint2.setTasks(getTasks());
        sprint2.setName("Уровень 2");

        sprintList.add(sprint1);
        sprintList.add(sprint2);*//*
        return sprintList;
    }*/


    //	private String login;
//	private String password;
    private final String baseUrl = "http://in.jon.com.ua/";
    //	private final String baseUrl = "http://localhost:8081";
    private final String USER_AGENT = "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36";
    private String cookies;
    private JSONDer jsonDer = new JSONDer();
    //   private ConsoleService consoleService;
    private List<Sprint> sprints;

    public RemoteService(/*String login, String password, ConsoleService consoleService*/) {
//		this.login = login;
//		this.password = password;
        ///  this.consoleService = consoleService;
        this.sprints = new ArrayList<Sprint>();
    }

    public List<Sprint> getUserSprints() throws ClassNameNotFoundException, PackageNotFoundException {

        sprints = jsonDer.getUserSprints(getSprintsAsJSON());
        return sprints;
    }

 /*   public Task getTask(String packageName, String className) throws ClassNameNotFoundException {
        for (Sprint sprint : sprints) {
            for (Task task : sprint.getTasks()) {
                if (task.getClassName() != null && task.getClassName().equals(className)
                        && task.getPackageName() != null && task.getPackageName().equals(packageName)) {
                    return task;
                }
            }
        }
        throw new ClassNameNotFoundException("Внутренняя ошибка, не найден проверяемый класс");
    }*/

   /* public GraduateResult graduateTask(String taskCode) throws ClassNameNotFoundException, PackageNotFoundException {
        if (sprints.isEmpty()) {
            throw new ClassNameNotFoundException("Нет загруженных задач, попробуйте \"Обновить\" задания");
        }
      *//*  if (PreferenceConst.DEFAULT_LOGIN.equals(PrefUtil.getLoginOrSaveDefault())) {
            consoleService.write("Внимание, решение не сохранено. Введите свои логин и пароль!");
        }*//*
        String className = jsonDer.extractClassNames(taskCode)[0];
        String packageName = jsonDer.extractPackageName(taskCode);
        Task task = getTask(packageName, className);
        // task.setCode(taskCode);
        // Long taskId = task.getId();
        // Long templateId = task.getTemplateId();

        StringBuilder result = new StringBuilder();

        try {
            result.append(URLEncoder.encode("taskCode", "UTF-8"));
            result.append('=');
            result.append(URLEncoder.encode(taskCode, "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("id", "UTF-8"));
            result.append('=');
            result.append(URLEncoder.encode(String.valueOf(taskId), "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("templateId", "UTF-8"));
            result.append('=');
            result.append(URLEncoder.encode(String.valueOf(templateId), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            //   consoleService.write("Внутренняя ошибка, не найдена кодировка " +  e1.getMessage());
        }

        String urlParameters = result.toString();

        String gradAddr = baseUrl + "/user/graduate";
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(gradAddr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Cookie", cookies);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setReadTimeout(10000);
            conn.addRequestProperty("User-Agent", USER_AGENT);
            conn.setDoOutput(true);
            DataOutputStream wr1 = new DataOutputStream(conn.getOutputStream());

            wr1.writeBytes(urlParameters);
            wr1.flush();
            conn.connect();

            if (conn.getHeaderField("Set-Cookie") != null) {
                cookies = conn.getHeaderField("Set-Cookie");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            checkCredentials(sb);
            conn.disconnect();

        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
            //   consoleService.write("Отсутствует связь с сервером " + baseUrl);
        } catch (ConnectException ce) {
            ce.printStackTrace();
            // consoleService.write("Невозможно установить соединение с сервером " + baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
            //consoleService.write("Внутренняя ошибка " + e.getMessage());
        }

        return jsonDer.getGraduateResult(sb.toString());
    }*/


    /* ====================== Этот метод делает запрос ======================*/
    public String getSprintsAsJSON() {
        String urlParameters = "j_username=Salvador_Sakho&j_password=international1";

        String jonAddr = baseUrl + "/user/groups";
        StringBuilder sb = new StringBuilder();


        URL url = null;
        try {
            url = new URL(jonAddr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setReadTimeout(5000);
            conn.setUseCaches(false);

            if (conn.getResponseCode() == 302) {
                conn = auth(urlParameters, jonAddr, conn);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }


            checkCredentials(sb);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void checkCredentials(StringBuilder sb) throws Exception {
        if (sb.indexOf("class=\"error\"") >= 0) {
            throw new BadCredentialsException("Логин или пароль пользователя указан неверно, изменить логин или пароль можно в меню \"Настройки\"");
        }
    }

    private HttpURLConnection auth(String urlParameters, String jonAddr,
                                   HttpURLConnection conn) throws Exception {
        cookies = conn.getHeaderField("Set-Cookie");
        conn.disconnect();

        conn = (HttpURLConnection) new URL(baseUrl + "/login")
                .openConnection();
        conn.addRequestProperty("Cookie", cookies);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setReadTimeout(5000);
        conn.addRequestProperty("User-Agent", USER_AGENT);
        conn.setDoOutput(true);
        DataOutputStream wr1 = new DataOutputStream(
                conn.getOutputStream());
        wr1.writeBytes(urlParameters);
        wr1.flush();

        cookies = conn.getHeaderField("Set-Cookie");

        conn.disconnect();

        conn = (HttpURLConnection) new URL(jonAddr).openConnection();
        conn.addRequestProperty("Cookie", cookies);
        conn.addRequestProperty("User-Agent", USER_AGENT);
        conn.setDoOutput(true);
        return conn;
    }

}
