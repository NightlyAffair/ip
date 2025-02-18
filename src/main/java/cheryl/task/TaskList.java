package cheryl.task;

import cheryl.util.SearchSystem;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods for adding, marking, unmarking, listing, and
 * deleting tasks. It also handles string formatting for user feedback related to the tasks in the
 * list.
 *
 * @author Nithvin Leelakrishnan
 */
public class TaskList {

  /** The list that stores all the tasks. */
  private final ArrayList<Task> taskList;

  /** Constructs a new TaskList with an empty list of tasks. */
  public TaskList() {
    this.taskList = new ArrayList<>();
  }

  /**
   * Constructs a new TaskList with the specified list of tasks.
   *
   * @param taskList The list of tasks to initialize the TaskList with.
   */
  public TaskList(ArrayList<Task> taskList) {
    this.taskList = taskList;
  }

  /**
   * Adds a Todo task to the task list and returns a formatted string confirming the addition.
   *
   * @param userInput The description of the Todo task to add.
   * @return A formatted string confirming the addition of the task.
   */
  public String addTodo(String userInput) {
    Todo todo = new Todo(userInput);
    this.taskList.add(todo);
    return addTaskString(todo.toString());
  }

  /**
   * Adds a Deadline task to the task list and returns a formatted string confirming the addition.
   *
   * @param userInput The description of the Deadline task to add.
   * @return A formatted string confirming the addition of the task.
   */
  public String addDeadline(String userInput) {
    Deadline deadline = new Deadline(userInput);
    this.taskList.add(deadline);
    return addTaskString(deadline.toString());
  }

  /**
   * Adds an Event task to the task list and returns a formatted string confirming the addition.
   *
   * @param userInput The description of the Event task to add.
   * @return A formatted string confirming the addition of the task.
   */
  public String addEvent(String userInput) {
    Event event = new Event(userInput);
    this.taskList.add(event);
    return addTaskString(event.toString());
  }

  /**
   * Returns a formatted string representing the list of tasks.
   *
   * @return A string listing all tasks in the task list.
   */
  public String listOut() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < this.taskList.size(); i++) {
      stringBuilder.append(i + 1).append('.');
      stringBuilder.append(this.taskList.get(i).toString());
      if (i != this.taskList.size() - 1) {
        stringBuilder.append('\n');
      }
    }
    return stringBuilder.toString();
  }

  /**
   * Marks a task as completed by its index in the task list and returns a formatted string
   * confirming the change.
   *
   * @param userInput The index of the task to mark as completed (1-based index).
   * @return A formatted string confirming that the task has been marked as completed.
   */
  public String markList(String userInput) {
    Integer index = Integer.parseInt(userInput) - 1;
    Task task = this.taskList.get(index).mark();
    this.taskList.set(index, task);
    return task.toString();
  }

  /**
   * Unmarks a task as completed by its index in the task list and returns a formatted string
   * confirming the change.
   *
   * @param userInput The index of the task to unmark (1-based index).
   * @return A formatted string confirming that the task has been unmarked as completed.
   */
  public String unmarkList(String userInput) {
    Integer index = Integer.parseInt(userInput) - 1;
    Task task = this.taskList.get(index).unmark();
    this.taskList.set(index, task);
    return task.toString();
  }

  /**
   * Deletes a task from the task list by its index and returns a formatted string confirming the
   * deletion.
   *
   * @param userInput The index of the task to delete (1-based index).
   * @return A formatted string confirming that the task has been deleted.
   */
  public String delete(String userInput) {
    Integer index = Integer.parseInt(userInput) - 1;
    Task task = this.taskList.get(index);
    this.taskList.remove(task);
    return removeTaskString(task.toString());
  }

  public String searchTask(String searchPhrase) {
    return SearchSystem.find(searchPhrase, taskList);
  }

  /**
   * Returns a formatted string confirming that a task has been added.
   *
   * @param taskDescription The string representation of the task that was added.
   * @return A formatted string confirming the addition of the task.
   */
  public String addTaskString(String taskDescription) {
    return "Got it. I've added this task:"
        + '\n'
        + "    "
        + taskDescription
        + '\n'
        + "Now you have "
        + this.taskList.size()
        + " tasks in the list.";
  }

  /**
   * Returns a formatted string confirming that a task has been removed.
   *
   * @param taskDescription The string representation of the task that was removed.
   * @return A formatted string confirming the removal of the task.
   */
  public String removeTaskString(String taskDescription) {
    return "Got it. I've removed this task:"
        + '\n'
        + "    "
        + taskDescription
        + '\n'
        + "Now you have "
        + this.taskList.size()
        + " tasks in the list.";
  }

  /**
   * Retrieves the list of tasks.
   *
   * @return The list of tasks managed by the TaskList.
   */
  public ArrayList<Task> get() {
    return this.taskList;
  }

  /**
   * Returns the size of the task list.
   *
   * @return The number of tasks in the list.
   */
  public int size() {
    return this.taskList.size();
  }
}
