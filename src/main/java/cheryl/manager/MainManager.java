package cheryl.manager;

import cheryl.exception.OutOfIndexException;
import cheryl.inputproccessor.Parser;
import cheryl.ui.MainUI;
import cheryl.ui.TaskUI;

public class MainManager implements Manager {

    private ManagerTypes pointer;
    private final TaskManager taskManager;

    public MainManager() {
        this.taskManager = new TaskManager();
        this.pointer = ManagerTypes.MAINMANAGER;
    }

    //Used in CLI
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

    //Used in JavaFX
    public String run(String userInput) {
        if(!(pointer == ManagerTypes.MAINMANAGER)) {
            String returnValue = runPointer(userInput);
            if(returnValue.equals("quit")) {
                setPointer(ManagerTypes.MAINMANAGER);
                return options();
            }
            return returnValue;
        }
        String command = Parser.mainCommand(userInput);
        try {
            switch (command) {
                case "1":
                    this.setPointer(ManagerTypes.TASKMANAGER);
                    return TaskManager.options();
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

    public void setPointer(ManagerTypes pointer) {
        this.pointer = pointer;
    }

    public String runPointer(String userInput) {
        switch (pointer) {
            case TASKMANAGER:
                return taskManager.run(userInput);
            default:
                setPointer(ManagerTypes.MAINMANAGER);
                return this.run(userInput);
        }
    }

    public String write() {
        StringBuilder sb = new StringBuilder();
        sb.append(taskManager.write());
        return sb.toString();
    }

    public void read(String readString) {
        switch (DataTypes.valueOf(Parser.deserializeCommand(readString).toUpperCase())) {
            case TASK -> {
                this.taskManager.read(Parser.deserializeDetails(readString));
            }
        }
    }

    public void clear() {
        taskManager.clear();
    }
}
