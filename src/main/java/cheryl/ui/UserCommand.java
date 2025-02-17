package cheryl.ui;

import cheryl.exception.MissingInputException;
import cheryl.exception.OutOfIndexException;
import cheryl.util.TaskList;

public class UserCommand {

  static String run(String userInput, TaskList taskList) {
    Commands command;
    String[] userCommand = Parser.mainCommand(userInput);
    String userString = Parser.details(userCommand);

    try {
      command = Commands.valueOf(userCommand[0].toUpperCase());
    } catch (IllegalArgumentException e) {
      return "Huh?? Please enter a valid input...";
    }

    switch (command) {
      case HELP:
        return UI.helpString();
      case LIST:
        return taskList.listOut();
    }

    try {
      if (userCommand.length < 2) {
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
}
