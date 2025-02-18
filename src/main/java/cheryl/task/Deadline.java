package cheryl.task;

import cheryl.inputproccessor.TimeProcessor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a "Deadline" task, which includes a description and a due date/time. Extends the
 * {@link Task} class and provides specific behavior for marking, unmarking, and serializing a
 * deadline task.
 *
 * @author Nithvin Leelakrishnan
 */
public class Deadline extends Task {

  /** The date and time by which the task is due. */
  private final LocalDateTime date;

  /**
   * Constructs a new Deadline task with the specified user input. Extracts the description and due
   * date from the input string.
   *
   * @param userInput The user's input containing the description and due date (formatted as
   *     "description /by dueDate").
   */
  public Deadline(String userInput) {
    super(userInput.split("/by ")[0]);
    this.date = new TimeProcessor(userInput.split("/by ")[1]).getDateTime();
  }

  /**
   * Constructs a new Deadline task as a copy of an existing task, with a specified due date.
   *
   * @param other The task to copy.
   * @param date The due date of the deadline task, as a string.
   */
  Deadline(Task other, String date) {
    super(other);
    this.date = new TimeProcessor(date).getDateTime();
  }

  /**
   * Constructs a new Deadline task as a copy of an existing task, with a specified due date.
   *
   * @param other The task to copy.
   * @param date The due date of the deadline task, as a {@link LocalDateTime} object.
   */
  public Deadline(Task other, LocalDateTime date) {
    super(other);
    this.date = date;
  }

  /**
   * Constructs a new Deadline task with a specified completion status, description, and due date.
   *
   * @param check The completion status of the task ("[ ]" or "[X]").
   * @param description The description of the deadline task.
   * @param date The due date of the deadline task, as a string.
   */
  Deadline(String check, String description, String date) {
    super(check, description);
    this.date = new TimeProcessor(date).getDateTime();
  }

  /**
   * Returns a string representation of the deadline task, including its type, status, description,
   * and due date.
   *
   * @return A string in the format "[D][X] Task description (by: dueDate)".
   */
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy HHmm");
    return "[D]" + super.toString() + "(by: " + date.format(formatter) + ")";
  }

  /**
   * Marks the deadline task as completed and returns a new Deadline instance with the updated
   * status.
   *
   * @return A new deadline task marked as completed.
   */
  @Override
  public Task mark() {
    return new Deadline(super.mark(), this.date);
  }

  /**
   * Unmarks the deadline task as incomplete and returns a new Deadline instance with the updated
   * status.
   *
   * @return A new deadline task marked as incomplete.
   */
  @Override
  public Task unmark() {
    return new Deadline(super.unmark(), this.date);
  }

  /**
   * Serializes the deadline task into a string format for storage or transmission. The format is
   * "DEADLINE||[status]||[description]||[dueDate]".
   *
   * @return A serialized string representing the deadline task.
   */
  @Override
  public String serialize() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    return TaskType.DEADLINE
        + "||"
        + this.check
        + "||"
        + this.description
        + "||"
        + this.date.format(formatter);
  }
}
