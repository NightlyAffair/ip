public class Deadline extends Task{
    private String date;

    Deadline(String userInput) {
        super(userInput.split("/by")[0]);
        this.date = userInput.split("/by")[1];
    }

    Deadline(Task other, String date) {
        super(other);
        this.date = date;
    }

    Deadline(String check, String description, String date) {
        super(check, description);
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by:" + date + ")";
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
        return TaskType.DEADLINE + "||" + this.check + "||" + this.description + "||" + this.date;
    }
}
