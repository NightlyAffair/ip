public class Task {

    protected String check;
    protected String description;

    public Task (String userInput) {
        this.check = "[ ]";
        this.description = userInput;
    }

    public Task (String check, String description) {
        this.check = check;
        this.description = description;
    }

    public Task (Task other) {
        this.check = other.check;
        this.description = other.description;
    }

    public String toString() {
        return this.check + " " + this.description;
    }

    public Task mark() {
        return new Task("[X]", this.description);
    }

    public Task unmark() {
        return new Task("[ ]", this.description);
    }

}
