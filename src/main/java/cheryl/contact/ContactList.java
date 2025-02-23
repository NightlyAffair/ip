package cheryl.contact;

import cheryl.manager.DataTypes;
import cheryl.util.Serialized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactList implements Serialized {

    private final ArrayList<Contact> contactList;

    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    public String add(String userInput) {
        HashMap<Character, String> values = extract(userInput);

        // Extract values safely
        String name = values.get('n');
        if(name == null) {
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

    public String addString(Contact contact) {
        return contact.toString() + " has been added";
    }

    public String remove(String userInput) {
        Contact contact = find(userInput);
        if(contact == null) {
            return "invalid contact";
        }
        contactList.remove(contact);
        return removeString(contact);
    }

    public String removeString(Contact contact) {
        return contact.toString() + " has been removed";
    }

    public Contact find(String userInput) {
        for(Contact contact : contactList) {
            if(contact.toString().equals(userInput)) {
                return contact;
            }
        }
        return null;
    }

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

    public String editString(Contact contact) {
        return contact.toString() + " has been updated";
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for(Contact contact : contactList) {
            sb.append(DataTypes.CONTACT).append("|||");
            sb.append(contact.serialize());
            sb.append("\n");
        }

        return sb.toString();
    }

    public void read(String readString) {
        add(readString);
    }

    public void clear() {
        contactList.clear();
    }

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

    public boolean exists(String name) {
        for(Contact contact : contactList) {
            if(contact.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
