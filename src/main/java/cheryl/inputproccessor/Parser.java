package cheryl.inputproccessor;

import java.util.Scanner;

public class Parser {
  private static final Scanner SCANNER = new Scanner(System.in);

  public static String scan() {
    return SCANNER.nextLine();
  }

  // Parses out details from user input and returns the details as a String
  public static String details(String userInput) {
    String[] userCommand = userInput.split(" ");
    if (userCommand.length == 1) {
      return userCommand[0];
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 1; i < userCommand.length; i++) {
      stringBuilder.append(userCommand[i]);

      if (i != userCommand.length - 1) {
        stringBuilder.append(' ');
      }
    }

    return stringBuilder.toString();
  }

  public static String mainCommand(String userInput) {
    return userInput.split(" ")[0];
  }

  public static String deserializeCommand(String userInput) {
    return userInput.split("\\|\\|\\|")[0];
  }

  public static String deserializeDetails(String userInput) {
    return userInput.split("\\|\\|\\|")[1];
  }
}
