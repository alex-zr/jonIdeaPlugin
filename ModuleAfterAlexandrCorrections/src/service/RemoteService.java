package service;

import java.io.*;
import java.net.*;

import domain.GraduateResult;
import domain.Task;
import domain.TaskFile;
import exception.BadCredentialsException;
import exception.ClassNameNotFoundException;
import exception.PackageNotFoundException;
import domain.Sprint;

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


/*import domain.Sprint;
import domain.Task;
import domain.TaskFile;

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

    // private String login;
    // private String password;
    private final String baseUrl = "http://in.jon.com.ua/";
    private final String USER_AGENT = "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36";
    private String cookies;
    private JSONDer jsonDer = new JSONDer();
    // private ConsoleService consoleService;
    private List<Sprint> sprints;

    public RemoteService(/*String login, String password, ConsoleService consoleService*/) {
//		this.login = login;
//		this.password = password;
//      this.consoleService = consoleService;
        this.sprints = new ArrayList<>();
    }

    public List<Sprint> getUserSprints() throws ClassNameNotFoundException, PackageNotFoundException {
        sprints = jsonDer.getUserSprints(getSprintsAsJSON());
        return sprints;
    }

    public Task getTask(String packageName, String className) throws ClassNameNotFoundException {
        for (Sprint sprint : sprints) {
            for (Task task : sprint.getTasks()) {


                for (int i = 0; i < task.getTaskFile().size(); i++) {

                    if (task.getTaskFile().get(i).getClassname() != null
                            && task.getTaskFile().get(i).getClassname().equals(className)
                            && task.getPackageName() != null
                            && task.getPackageName().equals(packageName)) {
                        return task;
                    }
                }

            }
        }
        throw new ClassNameNotFoundException("Внутренняя ошибка, не найден проверяемый класс, или пакет");
    }

    public GraduateResult graduateTask(String taskCode) throws ClassNameNotFoundException, PackageNotFoundException {
        if (sprints.isEmpty()) {
            throw new ClassNameNotFoundException("Нет загруженных задач, попробуйте \"Обновить\" задания");
        }

      /*  if (PreferenceConst.DEFAULT_LOGIN.equals(PrefUtil.getLoginOrSaveDefault())) {
            consoleService.write("Внимание, решение не сохранено. Введите свои логин и пароль!");
        }*/
        String className = jsonDer.extractClassNames(taskCode)[0];
        String packageName = jsonDer.extractPackageName(taskCode).replace(";", "");

        Task task = getTask(packageName, className);

        TaskFile taskFile = new TaskFile();
        taskFile.setFileContent(taskCode);
        task.getTaskFile().add(taskFile);
        Long taskId = task.getId();
        Long templateId = task.getTemplateId();
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
    }


    /* ====================== Этот метод делает запрос ======================*/
    public String getSprintsAsJSON() {
        String urlParameters = "j_username=Salvador_Sakho&j_password=international1";
        String jonAddr = baseUrl + "/user/groups";
        StringBuilder sb = new StringBuilder();
        URL url;
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

    private HttpURLConnection auth(String urlParameters, String jonAddr, HttpURLConnection conn) throws Exception {
        cookies = conn.getHeaderField("Set-Cookie");
        conn.disconnect();

        conn = (HttpURLConnection) new URL(baseUrl + "/login").openConnection();
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
