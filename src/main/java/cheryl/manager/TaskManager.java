package cheryl.manager;

import cheryl.exception.MissingInputException;
import cheryl.exception.OutOfIndexException;
import cheryl.inputproccessor.Parser;
import cheryl.task.Task;
import cheryl.commands.TaskCommands;
import cheryl.ui.TaskUI;
import cheryl.util.FileSystem;
import cheryl.task.TaskList;
import java.util.ArrayList;

public class TaskManager implements Manager {
  private final TaskList taskList;

  TaskManager() {
    this.taskList = new TaskList();
  }

  public TaskManager(ArrayList<Task> taskList) {
    this.taskList = new TaskList(taskList);
  }

  public String run() {
    TaskCommands command;
    TaskUI.printIntroString();
    String userCommand;
    while (!Parser.mainCommand(userCommand = Parser.scan()).equals("0")) {
      try {
        command = TaskCommands.valueOf((Parser.mainCommand(userCommand)).toUpperCase());
        System.out.println(runCommand(command, userCommand));
      } catch (IllegalArgumentException e) {
        System.out.println( "Huh?? Please enter a valid input...");
      }
    }
    return "0";
  }

  public String runCommand(TaskCommands command, String userCommand) {
    switch (command) {
      case HELP:
        return TaskUI.helpString();
      case LIST:
        return taskList.listOut();
    }

    String userString = Parser.details(userCommand);

    try {
      if (userString.isEmpty()) {
        throw new MissingInputException();
      }
      switch (command) {
        case TODO:
          return taskList.addTodo(userString);
        case DEADLINE:
          return taskList.addDeadline(userString);
        case EVENT:
          return taskList.addEvent(userString);
        case SEARCH:
          return taskList.searchTask(userString);
      }

      // For command referring to index in taskList
      if (Integer.parseInt(userString) < 1 || Integer.parseInt(userString) > taskList.size()) {
        throw new OutOfIndexException();
      }

      switch (command) {
        case MARK:
          return taskList.markList(userString);
        case UNMARK:
          return taskList.unmarkList(userString);
        case DELETE:
          return taskList.delete(userString);
      }
    } catch (MissingInputException | OutOfIndexException e) {
      return e.getMessage();
    } catch (NumberFormatException e) {
      return "Please enter a number to indicate the list item to be modified.";
    }
    return "";
  }


  public void pushFile() {
    new FileSystem(taskList.get()).pushFile();
  }
}
