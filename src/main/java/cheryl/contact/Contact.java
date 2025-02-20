package cheryl.contact;

import cheryl.util.Serialized;

public class Contact implements Serialized {

    private final String name;
    private final String phone;
    private final String email;
    private final String address;

    Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String toString() {
        return "Name:" + name + " " + "Phone:" + phone + " " + "Email:" + email + " " + "Address:" + address;
    }

    @Override
    public String serialize() {
        return "";
    }
}
