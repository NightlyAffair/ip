package cheryl.util;

import cheryl.task.Task;

import java.util.ArrayList;

public class SearchSystem {
    public static String find(String searchPhrase, ArrayList<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            if (task.has(searchPhrase)) {
                sb.append(task.toString());
                sb.append("\n");
            }
        }
        return findString(sb.toString());
    }

    public static String findString(String taskString) {
        return "Found the following tasks with that phrase:\n" + taskString;
    }
}
