package cheryl.task;

import cheryl.ui.TaskType;

/**
 * Represents a "Todo" task, a specific type of task with no additional attributes. Extends the
 * {@link Task} class and provides specific behavior for marking, unmarking, and serializing a todo
 * task.
 *
 * @author Nithvin Leelakrishnan
 */
public class Todo extends Task {

  /**
   * Constructs a new Todo task with the specified description. The task is initially marked as
   * incomplete.
   *
   * @param userInput The description of the todo task.
   */
  public Todo(String userInput) {
    super(userInput);
  }

  /**
   * Constructs a new Todo task as a copy of an existing task.
   *
   * @param other The task to copy.
   */
  Todo(Task other) {
    super(other);
  }

  /**
   * Constructs a new Todo task with the specified completion status and description.
   *
   * @param check The completion status of the task ("[ ]" or "[X]").
   * @param description The description of the todo task.
   */
  public Todo(String check, String description) {
    super(check, description);
  }

  /**
   * Returns a string representation of the todo task, including its type, check, and description.
   *
   * @return A string in the format "[T][X] Task description".
   */
  public String toString() {
    return "[T]" + super.toString();
  }

  /**
   * Marks the todo task as completed and returns a new Todo instance with the updated status.
   *
   * @return A new todo task marked as completed.
   */
  @Override
  public Task mark() {
    return new Todo(super.mark());
  }

  /**
   * Unmarks the todo task as incomplete and returns a new Todo instance with the updated status.
   *
   * @return A new todo task marked as incomplete.
   */
  @Override
  public Task unmark() {
    return new Todo(super.unmark());
  }

  /**
   * Serializes the todo task into a string format for storage or transmission. The format is
   * "TODO||[status]||[description]".
   *
   * @return A serialized string representing the todo task.
   */
  @Override
  public String serialize() {
    return TaskType.TODO + "||" + this.check + "||" + this.description;
  }
}
