package cheryl.ui;

import cheryl.task.Task;
import cheryl.util.FileSystem;
import cheryl.util.TaskList;
import java.util.ArrayList;

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
