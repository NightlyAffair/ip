package cheryl.task;

import cheryl.main.Data;
import cheryl.ui.TaskType;

/**
 * Represents a task with a description and completion status. Provides methods to mark the task as
 * completed, unmark it, and serialize its state. This class is used as the base for more specific
 * task types.
 *
 * @author Nithvin Leelakrishnan
 */
public class Task implements Data {
  /** The completion status of the task. Can be "[ ]" for incomplete or "[X]" for completed. */
  protected String check;

  /** The description of the task. */
  protected String description;

  /**
   * Constructs a new task with the specified description. The task is initially marked as
   * incomplete.
   *
   * @param userInput The description of the task.
   */
  public Task(String userInput) {
    this.check = "[ ]";
    this.description = userInput;
  }

  /**
   * Constructs a new task with the specified completion status and description.
   *
   * @param check The completion status of the task ("[ ]" or "[X]").
   * @param description The description of the task.
   */
  public Task(String check, String description) {
    this.check = check;
    this.description = description;
  }

  /**
   * Constructs a new task as a copy of an existing task.
   *
   * @param other The task to copy.
   */
  public Task(Task other) {
    this.check = other.check;
    this.description = other.description;
  }

  /**
   * Returns a string representation of the task, including its status and description.
   *
   * @return A string in the format "[ ] Task description" or "[X] Task description".
   */
  public String toString() {
    return this.check + " " + this.description;
  }

  /**
   * Marks the task as completed and returns a new Task instance with the updated status.
   *
   * @return A new task marked as completed.
   */
  public Task mark() {
    return new Task("[X]", this.description);
  }

  /**
   * Unmarks the task as incomplete and returns a new Task instance with the updated status.
   *
   * @return A new task marked as incomplete.
   */
  public Task unmark() {
    return new Task("[ ]", this.description);
  }

  /**
   * Serializes the task into a string format for storage or transmission. The format is
   * "TASK||[status]||[description]".
   *
   * @return A serialized string representing the task.
   */
  public String serialize() {
    return TaskType.TASK + "||" + this.check + "||" + this.description;
  }

  public boolean has(String searchPhrase) {
    return this.description.contains(searchPhrase);
  }
}
