package cheryl.contact;

import cheryl.commands.ContactCommands;
import cheryl.commands.TaskCommands;
import cheryl.inputproccessor.Parser;
import cheryl.manager.Manager;
import cheryl.manager.ManagerTypes;

public class ContactManager implements Manager {
    private final ContactList contactList;
    private ManagerTypes pointer;

    ContactManager() {
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
        switch (command) {

        }
    }

    public static String options() {
        return "To add: /n name /p phone number /e emailaddress@gmail.com /a Singapore Marina Bay Sands";
    }

    @Override
    public void setPointer(ManagerTypes pointer) {

    }

    @Override
    public String run() {
        return "";
    }

    @Override
    public String write() {
        return "";
    }

    @Override
    public void read(String readString) {

    }

    @Override
    public void clear() {

    }
}
