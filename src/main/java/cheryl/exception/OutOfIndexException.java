package cheryl.exception;

public class OutOfIndexException extends Exception {
    public OutOfIndexException() {
        super("Please choose a valid index from the list.");
    }
}
