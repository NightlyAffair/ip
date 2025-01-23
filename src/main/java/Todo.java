public class Todo extends Task{

    Todo(String userInput) {
        super(userInput);
    }

    Todo(Task other) {
        super(other);
    }

    Todo(String check, String description) {
        super(check, description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public Task mark() {
        return new Todo(super.mark());
    }

    @Override
    public Task unmark() {
        return new Todo(super.unmark());
    }

    @Override
    public String serialize() {
        return TaskType.TODO + "||" + this.check + "||" + this.description;
    }
}
