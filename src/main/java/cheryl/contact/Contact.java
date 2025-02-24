package cheryl.contact;

import cheryl.util.Serialized;

/**
 * Represents a contact with details such as name, phone number, email, and address. Implements the
 * Serialized interface for data persistence.
 *
 * <p>Author: Nithvin Leelakrishnan Version: 1.0
 */
public class Contact implements Serialized {

  private final String name;
  public final String phone;
  public final String email;
  public final String address;

  /**
   * Constructs a new Contact instance with the specified details.
   *
   * @param name The name of the contact.
   * @param phone The phone number of the contact.
   * @param email The email address of the contact.
   * @param address The physical address of the contact.
   */
  Contact(String name, String phone, String email, String address) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  /**
   * Returns a string representation of the contact, including all details.
   *
   * @return A formatted string containing the contact's details.
   */
  public String toString() {
    return "Name: "
        + name
        + " "
        + "Phone: "
        + phone
        + " "
        + "Email: "
        + email
        + " "
        + "Address: "
        + address;
  }

  /**
   * Serializes the contact's details for storage.
   *
   * @return A string representation of the serialized contact.
   */
  @Override
  public String serialize() {
    return "/n " + name + " /p " + phone + " /e " + email + " /a " + address;
  }

  /**
   * Retrieves the name of the contact.
   *
   * @return The name of the contact.
   */
  public String getName() {
    return name;
  }
}
