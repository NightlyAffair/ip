package cheryl.task;

import cheryl.ui.TimeProcessor;
import cheryl.ui.TaskType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an "Event" task, which includes a description, a start date/time, and an end date/time.
 * Extends the {@link Task} class and provides specific behavior for marking, unmarking,
 * and serializing an event task.
 *
 * @author Nithvin Leelakrishnan
 */
public class Event extends Task {

    /**
     * The start date and time of the event.
     */
    private final LocalDateTime startDate;

    /**
     * The end date and time of the event.
     */
    private final LocalDateTime endDate;

    /**
     * Constructs a new Event task with the specified user input.
     * Extracts the description, start date, and end date from the input string.
     *
     * @param userInput The user's input containing the description, start date, and end date
     *                  (formatted as "description /from startDate /to endDate").
     */
    public Event(String userInput) {
        super(userInput.split("/from ")[0]);
        startDate = new TimeProcessor(userInput.split("/from ")[1].split(" /to ")[0]).getDateTime();
        endDate = new TimeProcessor(userInput.split("/from ")[1].split(" /to ")[1]).getDateTime();
    }

    /**
     * Constructs a new Event task as a copy of an existing task, with specified start and end dates.
     *
     * @param other The task to copy.
     * @param startDate The start date of the event, as a string.
     * @param endDate The end date of the event, as a string.
     */
    Event(Task other, String startDate, String endDate) {
        super(other);
        this.startDate = new TimeProcessor(startDate).getDateTime();
        this.endDate = new TimeProcessor(endDate).getDateTime();
    }

    /**
     * Constructs a new Event task as a copy of an existing task, with specified start and end dates.
     *
     * @param other The task to copy.
     * @param startDate The start date of the event, as a {@link LocalDateTime} object.
     * @param endDate The end date of the event, as a {@link LocalDateTime} object.
     */
    Event(Task other, LocalDateTime startDate, LocalDateTime endDate) {
        super(other);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a new Event task with a specified completion status, description, start date, and end date.
     *
     * @param check The completion status of the task ("[ ]" or "[X]").
     * @param description The description of the event task.
     * @param startDate The start date of the event, as a string.
     * @param endDate The end date of the event, as a string.
     */
    Event(String check, String description, String startDate, String endDate) {
        super(check, description);
        this.startDate = new TimeProcessor(startDate).getDateTime();
        this.endDate = new TimeProcessor(endDate).getDateTime();
    }

    /**
     * Constructs a new Event task with a specified completion status, description, start date, and end date.
     *
     * @param check The completion status of the task ("[ ]" or "[X]").
     * @param description The description of the event task.
     * @param startDate The start date of the event, as a {@link LocalDateTime} object.
     * @param endDate The end date of the event, as a {@link LocalDateTime} object.
     */
    public Event(String check, String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(check, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the event task, including its type, status, description,
     * start date, and end date.
     *
     * @return A string in the format "[E][status] Task description (from: startDate to: endDate)".
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy HHmm");
        return "[E]" + super.toString() + "(from: " + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")";
    }

    /**
     * Marks the event task as completed and returns a new Event instance with the updated status.
     *
     * @return A new event task marked as completed.
     */
    @Override
    public Task mark() {
        return new Event(super.mark(), this.startDate, this.endDate);
    }

    /**
     * Unmarks the event task as incomplete and returns a new Event instance with the updated status.
     *
     * @return A new event task marked as incomplete.
     */
    @Override
    public Task unmark() {
        return new Event(super.unmark(), this.startDate, this.endDate);
    }

    /**
     * Serializes the event task into a string format for storage or transmission.
     * The format is "EVENT||[status]||[description]||[startDate]||[endDate]".
     *
     * @return A serialized string representing the event task.
     */
    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return TaskType.EVENT + "||" + this.check + "||" + this.description + "||"
                + this.startDate.format(formatter) + "||" + this.endDate.format(formatter);
    }
}