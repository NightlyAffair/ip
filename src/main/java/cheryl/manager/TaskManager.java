package cheryl.manager;

import cheryl.exception.FileCorruptedException;
import cheryl.exception.MissingInputException;
import cheryl.exception.OutOfIndexException;
import cheryl.inputproccessor.Parser;
import cheryl.inputproccessor.TimeProcessor;
import cheryl.task.*;
import cheryl.commands.TaskCommands;
import cheryl.ui.TaskUI;
import cheryl.util.FileSystem;
import javafx.scene.web.HTMLEditorSkin;

import java.util.ArrayList;

public class TaskManager implements Manager {
  private final TaskList taskList;
  private ManagerTypes pointer;

  TaskManager() {
    this.taskList = new TaskList();
    this.pointer = ManagerTypes.TASKMANAGER;
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

  public String run(String userInput) {
    if(userInput.equals("quit")) {
      return userInput;
    }
    TaskCommands command = TaskCommands.valueOf(Parser.mainCommand(userInput).toUpperCase());

    return runCommand(command, userInput);
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

  public void setPointer(ManagerTypes pointer) {
    this.pointer = pointer;
  }

  public static String options() {
    return TaskUI.helpString();
  }

  public String write() {
    return taskList.serialize();
  }

  public void read(String readString) {
    String[] details = readString.split("\\|\\|");

    try {
      switch (TaskType.valueOf(details[0])) {
        case TASK -> {
          taskList.addTask(new Task(details[1], details[2]));
        }
        case TODO -> {
          taskList.addTask(new Todo(details[1], details[2]));
        }
        case DEADLINE -> {
          taskList.addTask(new Deadline(
                  new Task(details[1], details[2]), new TimeProcessor(details[3]).getDateTime()));
        }
        case EVENT -> {
          taskList.addTask(new Event(
                  details[1],
                  details[2],
                  new TimeProcessor(details[3]).getDateTime(),
                  new TimeProcessor(details[4]).getDateTime()));
        }
        default -> throw new FileCorruptedException();
      }
    } catch (FileCorruptedException e) {
      System.err.println(e.getMessage());
    }
  }

  public void clear() {
    this.taskList.clear();
  }
}
