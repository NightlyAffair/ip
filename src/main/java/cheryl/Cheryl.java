package cheryl;

import cheryl.inputproccessor.Parser;
import cheryl.manager.MainManager;
import cheryl.ui.MainUI;

/**
 * Represents the overall chatbot Provides methods to begin running the chatbot.
 *
 * @author Nithvin Leelakrishnan
 * @version 1.0
 */
public class Cheryl {
  private final MainManager manager;

  /**
   * Constructs a new Cheryl instance. Initializes the manager with tasks loaded from the file
   * system.
   */
  public Cheryl() {
    this.manager = new MainManager();
  }

  /** Runs the main loop until user gives exit command. */
  public void run() {
    MainUI.printIntro();
    Boolean isMainLoopRunning = true;
    while (isMainLoopRunning) {
      String exitCommand = manager.run();
      if (exitCommand.equals("bye")) {
        isMainLoopRunning = false;
      }
    }
    this.manager.pushFile();
    MainUI.printOutro();
  }

  /** For use with GUI code to return response String **/
    public String run(String userInput) {
        assert userInput != null;
        MainUI.printOutro();
        return manager.run(userInput);
    }

    /**
     * Entry point of the cheryl.Cheryl chatbot application.
     * Creates a new cheryl.Cheryl instance and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Cheryl().run();
    }
}
