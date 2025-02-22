package cheryl.contact;

import cheryl.commands.ContactCommands;
import cheryl.commands.TaskCommands;
import cheryl.inputproccessor.Parser;
import cheryl.manager.Manager;
import cheryl.manager.ManagerTypes;

public class ContactManager implements Manager {
    private final ContactList contactList;
    private ManagerTypes pointer;

    public ContactManager() {
        this.contactList = new ContactList();
        this.pointer = ManagerTypes.CONTACTMANAGER;
    }

    public String run(String userInput) {
        if (userInput.equals("quit")) {
            return userInput;
        }

        ContactCommands command;
        try {
            command = ContactCommands.valueOf(Parser.mainCommand(userInput).toUpperCase());
        } catch (IllegalArgumentException e) {
            return options();
        }

        return runCommand(command, userInput);
    }

    public String runCommand(ContactCommands command, String userInput) {
        String details = Parser.details(userInput);

        switch (command) {
            case ADD -> {
                return contactList.add(details);
            }
            case REMOVE -> {
                return contactList.remove(details);
            }
            case EDIT -> {
                return contactList.edit(details);
            }
            case LIST -> {
                return contactList.list();
            }
        }

        return "Invalid command" + "\n" + options();
    }

    public static String options() {
        StringBuilder sb = new StringBuilder();
        String addString = "To add: add /n name /p phone number /e emailaddress@gmail.com /a Singapore Marina Bay Sands";
        sb.append(addString);
        sb.append("\n");
        String removeString = "To remove: remove name";
        sb.append(removeString);
        sb.append("\n");
        String editString = "To edit a contact: edit /n name /p phone number";
        sb.append(editString);
        sb.append("\n");
        String listString = "To list: list";
        sb.append(listString);
        sb.append("\n");
        String quitString = "To quit: quit";
        sb.append(quitString);
        return sb.toString();
    }

    @Override
    public void setPointer(ManagerTypes pointer) {
        this.pointer = pointer;
    }

    @Override
    public String write() {
        return contactList.serialize();
    }

    @Override
    public void read(String readString) {
        contactList.read(readString);
    }

    @Override
    public void clear() {
        contactList.clear();
    }
}
