package cheryl.contact;

import cheryl.commands.ContactCommands;
import cheryl.inputproccessor.Parser;
import cheryl.manager.Manager;
import cheryl.manager.ManagerTypes;

/**
 * Manages contact-related functionalities within the chatbot.
 * Provides methods for adding, removing, editing, and listing contacts.
 *
 * Author: Nithvin Leelakrishnan
 * Version: 1.0
 */
public class ContactManager implements Manager {
    private final ContactList contactList;
    private ManagerTypes pointer;

    /**
     * Constructs a new ContactManager instance. Initializes the contact list and
     * sets the pointer to CONTACTMANAGER.
     */
    public ContactManager() {
        this.contactList = new ContactList();
        this.pointer = ManagerTypes.CONTACTMANAGER;
    }

    /**
     * Processes user input commands related to contact management.
     *
     * @param userInput The user input command string.
     * @return A string indicating the result of the operation or the command options if invalid.
     */
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

    /**
     * Executes the specified contact command with the provided user input.
     *
     * @param command The contact command to execute.
     * @param userInput The user input command string.
     * @return A string indicating the result of the executed command.
     */
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

    /**
     * Displays the available options for the user to manage contacts.
     *
     * @return A formatted string listing the available contact commands and usage examples.
     */
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

    /**
     * Sets the current pointer to the specified ManagerTypes value.
     *
     * @param pointer The new manager type to set.
     */
    @Override
    public void setPointer(ManagerTypes pointer) {
        this.pointer = pointer;
    }

    /**
     * Serializes the current state of the contact list for storage.
     *
     * @return A string representation of the serialized contact list.
     */
    @Override
    public String write() {
        return contactList.serialize();
    }

    /**
     * Deserializes and processes the input string to update the contact list.
     *
     * @param readString The string to deserialize and process.
     */
    @Override
    public void read(String readString) {
        contactList.read(readString);
    }

    /**
     * Clears all contacts from the contact list.
     */
    @Override
    public void clear() {
        contactList.clear();
    }
}
