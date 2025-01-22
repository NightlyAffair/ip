public class Event extends Task{
    private String startDate;
    private String endDate;

    Event(String userInput) {
        super(userInput.split("/from")[0]);
        startDate = userInput.split("/from")[1].split("/to")[0];
        endDate = userInput.split("/from")[1].split("/to")[1];
    }

    Event(String check, String description, String startDate, String endDate) {
        super(check, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toString() {
        return "[D]" + super.toString() + "(from:" + startDate + " to:" + endDate + ")";
    }

    @Override
    public Task mark() {
        return new Event("[X]", super.description, this.startDate, this.endDate);
    }

    @Override
    public Task unmark() {
        return new Event("[]", super.description, this.startDate, this.endDate);
    }
}
