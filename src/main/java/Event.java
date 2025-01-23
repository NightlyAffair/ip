public class Event extends Task{
    private String startDate;
    private String endDate;

    Event(String userInput) {
        super(userInput.split("/from")[0]);
        startDate = userInput.split("/from")[1].split("/to")[0];
        endDate = userInput.split("/from")[1].split("/to")[1];
    }

    Event(Task other, String startDate, String endDate) {
        super(other);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Event(String check, String description, String startDate, String endDate) {
        super(check, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        return "[E]" + super.toString() + "(from:" + startDate + "to:" + endDate + ")";
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
