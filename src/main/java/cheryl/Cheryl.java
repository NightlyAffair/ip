package cheryl;

import cheryl.inputproccessor.Parser;
import cheryl.manager.MainManager;
import cheryl.ui.MainUI;
import cheryl.util.FileSystem;

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

  /** For use with GUI code to return response String **/
    public String run(String userInput) {
        manager.clear();
        assert userInput != null;
        FileSystem.read(manager);
        String returnString = manager.run(userInput);
        FileSystem.write(manager.write());
        return returnString;
    }

    /**
     * Entry point of the cheryl.Cheryl chatbot application.
     * Creates a new cheryl.Cheryl instance and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Cheryl().run(Parser.scan());
    }
}
