package cheryl.contact;

import cheryl.manager.DataTypes;
import cheryl.util.Serialized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a list of contacts, providing methods to manage contacts, including adding, removing,
 * editing, and listing them. Implements the Serialized interface for data persistence.
 *
 * Author: Nithvin Leelakrishnan
 * Version: 1.0
 */
public class ContactList implements Serialized {

    private final ArrayList<Contact> contactList;

    /**
     * Constructs a new ContactList instance. Initializes an empty contact list.
     */
    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    /**
     * Adds a new contact to the contact list based on user input.
     *
     * @param userInput The user input string containing contact details.
     * @return A string indicating the result of the add operation.
     */
    public String add(String userInput) {
        HashMap<Character, String> values = extract(userInput);

        // Extract values safely
        String name = values.get('n');
        if (name == null) {
            return "Must provide a name for contact";
        } else if (exists(name)) {
            return "Name already exists";
        }
        String phone = values.getOrDefault('p', "");
        String email = values.getOrDefault('e', "");
        String address = values.getOrDefault('a', "");

        Contact contact = new Contact(name, phone, email, address);
        contactList.add(contact);

        return addString(contact);
    }

    /**
     * Generates a confirmation string indicating the contact has been added.
     *
     * @param contact The contact that was added.
     * @return A string confirming the addition of the contact.
     */
    public String addString(Contact contact) {
        return contact.toString() + " has been added";
    }

    /**
     * Removes a contact from the contact list based on user input.
     *
     * @param userInput The user input string containing contact details.
     * @return A string indicating the result of the remove operation.
     */
    public String remove(String userInput) {
        HashMap<Character, String> values = extract(userInput);

        // Extract values safely
        String name = values.getOrDefault('n', "");
        String phone = values.getOrDefault('p', "");
        String email = values.getOrDefault('e', "");
        String address = values.getOrDefault('a', "");

        Contact contact = new Contact(name, phone, email, address);
        if (name == null) {
            return "invalid contact";
        }
        if (!removeByName(contact)) {
            return "Contact not found";
        }
        return removeString(contact);
    }

    /**
     * Removes a contact by name from the contact list.
     *
     * @param contact The contact to remove.
     * @return true if the contact was found and removed, false otherwise.
     */
    public Boolean removeByName(Contact contact) {
        String name = contact.getName();
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getName().equals(name)) {
                contactList.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a confirmation string indicating the contact has been removed.
     *
     * @param contact The contact that was removed.
     * @return A string confirming the removal of the contact.
     */
    public String removeString(Contact contact) {
        return contact.toString() + " has been removed";
    }

    /**
     * Finds a contact in the contact list that matches the user input.
     *
     * @param userInput The user input string to match against contacts.
     * @return The found contact, or null if not found.
     */
    public Contact find(String userInput) {
        for (Contact contact : contactList) {
            if (contact.toString().equals(userInput)) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Edits an existing contact based on user input.
     *
     * @param userInput The user input string containing updated contact details.
     * @return A string indicating the result of the edit operation.
     */
    public String edit(String userInput) {
        HashMap<Character, String> values = extract(userInput);

        // Extract values safely
        String name = values.getOrDefault('n', "");
        String phone = values.getOrDefault('p', "");
        String email = values.getOrDefault('e', "");
        String address = values.getOrDefault('a', "");

        Contact contact = new Contact(name, phone, email, address);
        remove(name);
        contactList.add(contact);
        return editString(contact);
    }

    /**
     * Generates a confirmation string indicating the contact has been updated.
     *
     * @param contact The contact that was updated.
     * @return A string confirming the update of the contact.
     */
    public String editString(Contact contact) {
        return contact.toString() + " has been updated";
    }

    /**
     * Serializes the current state of the contact list for storage.
     *
     * @return A string representation of the serialized contact list.
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Contact contact : contactList) {
            sb.append(DataTypes.CONTACT).append("|||");
            sb.append(contact.serialize());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Reads a string to add a contact to the list.
     *
     * @param readString The string to read and process for adding a contact.
     */
    public void read(String readString) {
        add(readString);
    }

    /**
     * Clears all contacts from the contact list.
     */
    public void clear() {
        contactList.clear();
    }

    /**
     * Lists all contacts in the contact list.
     *
     * @return A string representation of all contacts, formatted for display.
     */
    public String list() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.contactList.size(); i++) {
            stringBuilder.append(i + 1).append('.');
            stringBuilder.append(this.contactList.get(i).toString());
            if (i != this.contactList.size() - 1) {
                stringBuilder.append('\n');
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Extracts contact details from the user input string using regular expressions.
     *
     * @param userInput The user input string to extract values from.
     * @return A HashMap containing extracted values with keys 'n', 'p', 'e', 'a'.
     */
    public HashMap<Character, String> extract(String userInput) {
        Pattern pattern = Pattern.compile("/([npea])\\s+([^/]+)");
        Matcher matcher = pattern.matcher(userInput);

        // Store extracted values in a HashMap
        HashMap<Character, String> values = new HashMap<>();

        while (matcher.find()) {
            char key = matcher.group(1).charAt(0);  // 'n', 'p', 'e', or 'a'
            String value = matcher.group(2).trim(); // Extracted value
            values.put(key, value);
        }

        return values;
    }

    /**
     * Checks if a contact with the specified name already exists in the contact list.
     *
     * @param name The name of the contact to check for existence.
     * @return true if the contact exists, false otherwise.
     */
    public boolean exists(String name) {
        for (Contact contact : contactList) {
            if (contact.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
