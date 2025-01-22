public class Todo extends Task{

    Todo(String userInput) {
        super(userInput);
    }

    Todo(String check, String description) {
        super(check, description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public Task mark() {
        return new Todo("[X]", super.description);
    }

    @Override
    public Task unmark() {
        return new Todo("[]", super.description);
    }
}
