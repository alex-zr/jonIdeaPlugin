package service;

import domain.GraduateResult;
import domain.Sprint;
import domain.Task;
import domain.TaskFile;
import exception.ClassNameNotFoundException;
import exception.PackageNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ${BIM} on 24.01.2017.
 */
public class JSONDer {

    private String UNKNOWN = "Unknown";

    private JSONParser parser = new JSONParser();

    public List<Sprint> getUserSprints(String jsonString) throws ClassNameNotFoundException, PackageNotFoundException {
        List<Sprint> sprintsList = new ArrayList<>();
        try {
            Object obj = parser.parse(jsonString);
            JSONArray array = (JSONArray) obj;
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext()) {
                Sprint sprint = new Sprint();
                JSONObject jsonObj = iterator.next();
                String sprintName = (String) jsonObj.get("name"); //Имя спринта (Пример: ProffWeek6)
                JSONArray tasks = (JSONArray) jsonObj.get("tasks");

                List<Task> taskList = new ArrayList<>();
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = new Task();
                    JSONObject jsonTask = (JSONObject) tasks.get(i);

                  /*  System.out.println(jsonTask.keySet() + " " + "jsonTask");*/


                    String taskName = (String) jsonTask.get("name"); //(Название задания на русском - для таблици)
                    String taskinfoText = (String) jsonTask.get("text"); //(Текст задания)
                    Long id = (Long) jsonTask.get("id"); // не Пустой
                    Long templateId = (Long) jsonTask.get("taskTemplateId"); // не Пустой
                    String classPath = (String) jsonTask.get("classPath"); // Пути
                    String className;

                    if (taskName.equals("4. Расширение справочника")) {
                        classPath = "sprint1/session1/Task1";
                    }

                    //String etem1 = (String) jsonTask.get("code"); // Пустой
                    //String etem2 = String.valueOf(jsonTask.get("groupId")); // не Пустой
                    //String etem3 = (String) jsonTask.get("className"); // Пустой
                    //String etem4 = (String) jsonTask.get("type"); // не Пустой
                    //String etem5 = (String) jsonTask.get("userName"); // не Пустой
                    //String etem6 = String.valueOf(jsonTask.get("taskTemplateId")); // не Пустой
                    //String etem7 = (String) jsonTask.get("result"); // Пустой
                    //String etem8 = (String) jsonTask.get("material"); // Пустой
                    //String etem9 = String.valueOf(jsonTask.get("rate")); // 0.0
                    //String etem10 = (String) jsonTask.get("classPath"); // не Пустой
                    //String etem11 = (String) jsonTask.get("name"); // не Пустой (Название задания)
                    //String etem12 = String.valueOf(jsonTask.get("id")); // не Пустой
                    //String etem13 = (String) jsonTask.get("text"); // не Пустой (Текст задания)
                    //String etem14 = (String) jsonTask.get("status"); // не Пустой

                    if (!taskName.isEmpty()) {
                        task.setName(taskName);
                    } else {
                        System.out.println("Нету имени задания (которое для поьзователя)");
                    }

                    if (!taskinfoText.isEmpty()) {
                        task.setTaskInfo(taskinfoText);
                    } else {
                        System.out.println("Нету текста задания");
                    }

                    if (id != null) {
                        task.setId(id);
                    } else {
                        System.out.println("Нету ID задания");
                    }

                    if (templateId != null) {
                        task.setTemplateId(templateId);
                    } else {
                        System.out.println("Нету ID шаблона задания");
                    }

                    TaskFile taskFile = new TaskFile();
                    List<TaskFile> taskFileList = new ArrayList<>();
                    taskFileList.add(taskFile);
                    task.setTaskFile(taskFileList);

                    for (int y = 0; y < task.getTaskFile().size(); y++) {
                        className = "TaskClassName_" + y;
                        task.getTaskFile().get(y).setClassname(className);
                        task.getTaskFile().get(y).setFileUrl(classPath);
                        task.getTaskFile().get(y).setFileComponent(" package " + task.getTaskFile().get(y).getFileUrl().replace("/", ".") + ";\n/*" + task.getTaskInfo() + "\n*/" + ";\n\n public class " + className + " {\n /*напишите тут решение*/ \n}");
                    }

                    String packageName = UNKNOWN;
                    try {
                        // packageName = extractPackageName(taskinfoText);
                        packageName = task.getTaskFile().get(0).getFileUrl().split("/")[task.getTaskFile().get(0).getFileUrl().split("/").length - 1];
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    task.setPackageName(packageName);
                    // task.setClassName(taskName);

                    taskList.add(task);
                }
                sprint.setName(sprintName);
                sprint.setTasks(taskList);
                sprintsList.add(sprint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sprintsList;
    }

    public String extractPackageName(String classCode) throws PackageNotFoundException {
        if (classCode == null || classCode.isEmpty() || classCode.equals('\n')) {
            return UNKNOWN;
        }

        String[] tokens = getTokens(classCode);
        int classIdx;
        for (int i = 0; i < tokens.length; i++) {
            if ("package".equals(tokens[i])) {

                for (int j = i + 1;j<tokens.length;j++) {
                    classIdx = j;
                    if (tokens[classIdx + 1].contains(";")) {
                        while (tokens[classIdx + 1] == null || tokens[classIdx + 1].isEmpty()) {
                            classIdx++;
                        }
                        return tokens[classIdx + 1];
                    }
                }
            }
        }
        throw new PackageNotFoundException("Package declaration error");
    }

    public String[] extractClassNames(String classCode) throws ClassNameNotFoundException {
        List<String> classNames = new ArrayList<>();
        if (classCode == null || classCode.isEmpty() || classCode.equals('\n')) {
            return new String[0];
        }
        String[] tokens = getTokens(classCode);
        int classIdx;
        for (int i = 0; i < tokens.length; i++) {
            if ("class".equals(tokens[i])) {
                classIdx = i;
                while (tokens[classIdx + 1] == null || tokens[classIdx + 1].isEmpty()) {
                    classIdx++;
                }
                classNames.add(tokens[classIdx + 1]);
            }
        }
        if (classNames.isEmpty()) {
            throw new ClassNameNotFoundException("Class not found in the task");
        }
        return classNames.toArray(new String[classNames.size()]);
    }

    private String[] getTokens(String classCode) {
        String[] tokens = classCode.split("[^a-zA-Z_0-9а-яА-Я;]");

        if (tokens.length == 0) {
            return new String[0];
        }

        return tokens;
    }

    public GraduateResult getGraduateResult(String result) {
        try {
            JSONObject jsonObj = (JSONObject) parser.parse(result);
            String mark = (String) jsonObj.get("mark");
            String details = (String) jsonObj.get("details");
            return new GraduateResult(mark, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GraduateResult("", "Task validation error");
    }
}
