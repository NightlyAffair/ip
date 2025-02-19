package cheryl.manager;

import cheryl.exception.OutOfIndexException;
import cheryl.inputproccessor.Parser;
import cheryl.ui.MainUI;
import cheryl.ui.TaskUI;

public class MainManager implements Manager {

    private final TaskManager taskManager;

    public MainManager() {
        this.taskManager = new TaskManager();
    }

    public String run() {
        MainUI.printOptions();
        Boolean runMainLoop = true;
        while(runMainLoop) {
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

    public String run(String userInput) {
        String command = Parser.mainCommand(userInput);
        try {
            switch (command) {
                case "1":
                    return taskManager.run();
                default:
                    throw new OutOfIndexException();
            }
        } catch (OutOfIndexException e) {
            return e.getMessage();
        }
    }

    public static String options() {
        StringBuilder sb = new StringBuilder();
        sb.append("Please choose one of the following options:" + "\n");
        sb.append("[1] Tasks");

        return sb.toString();
    }

    public void pushFile() {

    }
}
