public class Todo extends Task{

    Todo(String userInput) {
        super(userInput);
    }

    Todo(Task other) {
        super(other);
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
}
