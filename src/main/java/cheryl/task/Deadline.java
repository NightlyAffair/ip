package cheryl.task;

import cheryl.ui.TimeProcessor;
import cheryl.ui.TaskType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime date;

    public Deadline(String userInput) {
        super(userInput.split("/by ")[0]);
        this.date = new TimeProcessor(userInput.split("/by ")[1]).getDateTime();

    }

    Deadline(Task other, String date) {
        super(other);
        this.date = new TimeProcessor(date).getDateTime();
    }

    public Deadline(Task other, LocalDateTime date) {
        super(other);
        this.date = date;
    }

    Deadline(String check, String description, String date) {
        super(check, description);
        this.date = new TimeProcessor(date).getDateTime();
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy HHmm");
        return "[D]" + super.toString() + "(by: " + date.format(formatter) + ")";
    }

    @Override
    public Task mark() {
        return new Deadline(super.mark(), this.date);
    }

    @Override
    public Task unmark() {
        return new Deadline(super.unmark(), this.date);
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return TaskType.DEADLINE + "||" + this.check + "||" + this.description + "||" + this.date.format(formatter);
    }
}
