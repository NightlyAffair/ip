package cheryl.manager;

import cheryl.contact.ContactManager;
import cheryl.exception.OutOfIndexException;
import cheryl.inputproccessor.Parser;
import cheryl.ui.MainUI;

/**
 * Manages the overall functionality of the chatbot, coordinating between task and contact
 * management. Provides methods for running the application in both CLI and JavaFX environments.
 *
 * @author Nithvin Leelakrishnan
 * @version 1.0
 */
public class MainManager implements Manager {

  private ManagerTypes pointer;
  private final TaskManager taskManager;
  private final ContactManager contactManager;

  /**
   * Constructs a new MainManager instance. Initializes the pointer to MAINMANAGER and instantiates
   * TaskManager and ContactManager.
   */
  public MainManager() {
    this.pointer = ManagerTypes.MAINMANAGER;
    this.taskManager = new TaskManager();
    this.contactManager = new ContactManager();
  }

  /**
   * Runs the main loop for the command-line interface. Displays options to the user and processes
   * user input, delegating to the appropriate manager based on the command.
   *
   * @return A string indicating the result of the operation, or "bye" when exiting the loop.
   */
  public String run() {
    MainUI.printOptions();
    Boolean runMainLoop = true;
    while (runMainLoop) {
      String userInput = Parser.scan();
      String command = Parser.mainCommand(userInput);
      try {
        switch (command) {
          case "1":
            return taskManager.run();
          case "0":
            runMainLoop = false;
            break;
          default:
            throw new OutOfIndexException();
        }
      } catch (OutOfIndexException e) {
        System.out.println(e.getMessage());
      }
    }
    return "bye";
  }

  /**
   * Processes user input in the JavaFX interface, determining the current manager to execute.
   *
   * @param userInput The user input command string.
   * @return A string indicating the result of the operation.
   */
  public String run(String userInput) {
    if (!(pointer == ManagerTypes.MAINMANAGER)) {
      String returnValue = runPointer(userInput);
      if (returnValue.equals("quit")) {
        setPointer(ManagerTypes.MAINMANAGER);
        return options();
      }
      return returnValue;
    }
    String command = convert(Parser.mainCommand(userInput).toUpperCase());

    try {
      DataTypes.valueOf(command);
      switch (command) {
        case "TASK":
          this.setPointer(ManagerTypes.TASKMANAGER);
          return TaskManager.options();
        case "CONTACT":
          this.setPointer(ManagerTypes.CONTACTMANAGER);
          return ContactManager.options();
        default:
          throw new OutOfIndexException();
      }
    } catch (OutOfIndexException | IllegalArgumentException e) {
      return options();
    }
  }

  /**
   * Displays the available options for the user to choose from within the main manager.
   *
   * @return A formatted string listing the available commands.
   */
  public static String options() {
    StringBuilder sb = new StringBuilder();
    sb.append("Please choose one of the following options:" + "\n");
    sb.append("[1] Task");
    sb.append("\n");
    sb.append("[2] Contact");

    return sb.toString();
  }

  /**
   * Sets the current pointer to the specified ManagerTypes value.
   *
   * @param pointer The new manager type to set.
   */
  public void setPointer(ManagerTypes pointer) {
    this.pointer = pointer;
  }

  /**
   * Executes the run method of the currently pointed manager (TaskManager or ContactManager).
   *
   * @param userInput The user input command string.
   * @return A string indicating the result of the operation.
   */
  public String runPointer(String userInput) {
    switch (pointer) {
      case TASKMANAGER:
        return taskManager.run(userInput);
      case CONTACTMANAGER:
        return contactManager.run(userInput);
      default:
        setPointer(ManagerTypes.MAINMANAGER);
        return this.run(userInput);
    }
  }

  /**
   * Collects and returns the string representations of the current state of both taskManager and
   * contactManager.
   *
   * @return A string containing the serialized data of tasks and contacts.
   */
  public String write() {
    StringBuilder sb = new StringBuilder();
    sb.append(taskManager.write());
    sb.append(contactManager.write());
    return sb.toString();
  }

  /**
   * Deserializes and processes the input string to update the appropriate manager's state.
   *
   * @param readString The string to deserialize and process.
   */
  public void read(String readString) {
    switch (DataTypes.valueOf(Parser.deserializeCommand(readString).toUpperCase())) {
      case TASK -> {
        this.taskManager.read(Parser.deserializeDetails(readString));
      }
      case CONTACT -> {
        this.contactManager.read(Parser.deserializeDetails(readString));
      }
    }
  }

  /** Clears all tasks from the taskManager. */
  public void clear() {
    taskManager.clear();
  }

  /**
   * Converts a numeric command (as a string) to its corresponding DataTypes value.
   *
   * @param command The command string to convert.
   * @return A string representation of the corresponding DataTypes value or the original command if
   *     no match is found.
   */
  public String convert(String command) {
    if (command.equals("1")) {
      return DataTypes.TASK.toString();
    }
    if (command.equals("2")) {
      return DataTypes.CONTACT.toString();
    }
    return command;
  }
}
