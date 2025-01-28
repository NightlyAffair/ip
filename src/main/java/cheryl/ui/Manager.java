package cheryl.ui;

import java.util.ArrayList;

import cheryl.util.FileSystem;
import cheryl.util.TaskList;
import cheryl.task.Task;

public class Manager {
    private final TaskList taskList;

    Manager() {
        this.taskList = new TaskList();
    }

    public Manager(ArrayList<Task> taskList) {
        this.taskList = new TaskList(taskList);
    }

    public String run(String userInput) {
        return UserCommand.run(userInput, taskList);


    }

    public void pushFile() {
        new FileSystem(taskList.get()).pushFile();
    }
}
