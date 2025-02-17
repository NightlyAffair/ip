package cheryl.main;

import cheryl.util.FileSystem;
import cheryl.ui.Manager;
import cheryl.ui.UI;
import cheryl.util.FileSystem;

/**
 * Represents the overall chatbot Provides methods to begin running the chatbot.
 *
 * @author Nithvin Leelakrishnan
 * @version 1.0
 */
public class Cheryl {
  private final Manager manager;

  /**
   * Constructs a new Cheryl instance. Initializes the manager with tasks loaded from the file
   * system.
   */
  public Cheryl() {
    this.manager = new Manager(new FileSystem().getTasks());
  }

  /** Runs the main loop until user gives exit command. */
  public void run() {
    UI.printIntro();
    Boolean isMainLoopRunning = true;
    while (isMainLoopRunning) {
      String userInput = UI.scan();
      if (userInput.equals("bye")) {
        isMainLoopRunning = false;
      } else {
        System.out.println(manager.run(userInput));
      }
    }
    manager.pushFile();
    UI.printOutro();
  }

  /** For use with GUI code to return response String **/
    public String run(String userInput) {
        assert userInput != null;
        UI.printIntro();
        manager.pushFile();
        UI.printOutro();
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
