package service;

import exception.ClassNameNotFoundException;
import exception.PackageNotFoundException;
import domain.GraduateResult;
import domain.Sprint;
import domain.Task;

import domain.TaskFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        List<Sprint> sprints = new ArrayList<>();
        try {
            Object obj = parser.parse(jsonString);
            JSONArray array = (JSONArray) obj;
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext()) {
                Sprint sprint = new Sprint();
                JSONObject jsonObj = iterator.next();
                String sprintName = (String) jsonObj.get("name");
                JSONArray tasks = (JSONArray) jsonObj.get("tasks");

                List<Task> taskList = new ArrayList<>();
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = new Task();
                    JSONObject jsonTask = (JSONObject) tasks.get(i);

                    //System.out.println(jsonTask.keySet() + " " + "jsonTask");

                    String result = (String) jsonTask.get("result");
                    String taskName = (String) jsonTask.get("name");
                    String taskCode = (String) jsonTask.get("text");
                    String classPath = (String) jsonTask.get("classPath");
                    //System.out.println(taskName + " taskName " + classPath + " classPass");


                    if (taskName.equals("3. Справочник ноутбуков")) {
                        classPath = "sprint1/session1/Task1";
                    }
                    String taskinfoText = (String) jsonTask.get("text");
                    if (taskCode.isEmpty()) {
                        taskCode = (String) jsonTask.get("material");
                    }

                    Long id = (Long) jsonTask.get("id");
                    //Long templateId = (Long) jsonTask.get("taskTemplateId");
                    String className = UNKNOWN;
                    String[] classNames = null;
                    try {
                        classNames = extractClassNames(taskCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (classNames != null && classNames.length != 0) {
                        className = classNames[0];
                    }
                    String packageName = UNKNOWN;
                    try {
                        packageName = extractPackageName(taskCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    task.setName(taskName);
                    task.setTaskInfo(taskinfoText);
                    task.setClassName(taskName);
                    task.setPackageName(taskName);
                    task.setId(id);

                    TaskFile taskFile = new TaskFile();
                    task.setTaskFile(taskFile);
                    task.getTaskFile().setFileUrl(classPath);
                    task.getTaskFile().setFileComponent(" package " + task.getTaskFile().getFileUrl().replace("/", ".") + "\n/*" + task.getTaskInfo() + "\n*/" + ";\n\n public class " + task.getTaskFile().getFileUrl().split("/")[task.getTaskFile().getFileUrl().split("/").length - 1] + " {\n /*напишите тут решение*/ \n}");
                    taskList.add(task);
                }
                sprint.setName(sprintName);
                sprint.setTasks(taskList);
                sprints.add(sprint);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sprints;
    }

    public String extractPackageName(String classCode) throws PackageNotFoundException {
        if (classCode == null || classCode.isEmpty() || classCode.equals('\n')) {
            return UNKNOWN;
        }

        String[] tokens = getTokens(classCode);
        int classIdx;
        for (int i = 0; i < tokens.length; i++) {
            if ("package".equals(tokens[i])) {
                classIdx = i;
                while (tokens[classIdx + 1] == null || tokens[classIdx + 1].isEmpty()) {
                    classIdx++;
                }
                return tokens[classIdx + 1];
            }
        }
        throw new PackageNotFoundException("Package declaration error");
    }

    public String[] extractClassNames(String classCode) throws ClassNameNotFoundException {
        List<String> classNames = new ArrayList<String>();
        if (classCode == null || classCode.isEmpty() || classCode.equals('\n')) {
            return new String[0];
        }
        String[] tokens = getTokens(classCode);
        int classIdx = 0;
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
        String[] tokens = classCode.split("[^a-zA-Z_0-9а-яА-Я]");

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new GraduateResult("", "Task validation error");
    }

}
