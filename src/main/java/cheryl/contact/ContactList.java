package cheryl.contact;

import cheryl.util.Serialized;

import java.util.ArrayList;

public class ContactList implements Serialized {

    private final ArrayList<Contact> contactList;

    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    @Override
    public String serialize() {
        return "";
    }
}
