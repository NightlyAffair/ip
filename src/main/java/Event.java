import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    Event(String userInput) {
        super(userInput.split("/from ")[0]);
        startDate = new TimeProcessor(userInput.split("/from ")[1].split(" /to ")[0]).getDateTime();
        endDate = new TimeProcessor(userInput.split("/from ")[1].split(" /to ")[1]).getDateTime();
    }

    Event(Task other, String startDate, String endDate) {
        super(other);
        this.startDate = new TimeProcessor(startDate).getDateTime();
        this.endDate = new TimeProcessor(endDate).getDateTime();
    }

    Event(Task other, LocalDateTime startDate, LocalDateTime endDate) {
        super(other);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Event(String check, String description, String startDate, String endDate) {
        super(check, description);
        this.startDate = new TimeProcessor(startDate).getDateTime();
        this.endDate = new TimeProcessor(endDate).getDateTime();
    }

    Event(String check, String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(check, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy HHmm");
        return "[E]" + super.toString() + "(from: " + startDate.format(formatter) + " to: " + endDate.format(formatter) + ")";
    }

    @Override
    public Task mark() {
        return new Event(super.mark(), this.startDate, this.endDate);
    }

    @Override
    public Task unmark() {
        return new Event(super.unmark(), this.startDate, this.endDate);
    }

    @Override
    public String serialize() {
        return TaskType.EVENT + "||" + this.check + "||" + this.description + "||" + this.startDate + "||" + this.endDate;
    }
}
