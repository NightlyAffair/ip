package cheryl.manager;

import cheryl.contact.ContactManager;
import cheryl.exception.OutOfIndexException;
import cheryl.inputproccessor.Parser;
import cheryl.ui.MainUI;
import cheryl.ui.TaskUI;

public class MainManager implements Manager {

    private ManagerTypes pointer;
    private final TaskManager taskManager;
    private final ContactManager contactManager;

    public MainManager() {
        this.taskManager = new TaskManager();
        this.pointer = ManagerTypes.MAINMANAGER;
        this.contactManager = new ContactManager();
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

    public static String options() {
        StringBuilder sb = new StringBuilder();
        sb.append("Please choose one of the following options:" + "\n");
        sb.append("[1] Task");
        sb.append("\n");
        sb.append("[2] Contact");

        return sb.toString();
    }

    public void setPointer(ManagerTypes pointer) {
        this.pointer = pointer;
    }

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

    public String write() {
        StringBuilder sb = new StringBuilder();
        sb.append(taskManager.write());
        sb.append(contactManager.write());
        return sb.toString();
    }

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

    public void clear() {
        taskManager.clear();
    }

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
