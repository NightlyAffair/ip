package cheryl.exception;

public class MissingInputException extends Exception {
    public MissingInputException() {
        super("Erm...I think you forgot to write what you want me to remember...");
    }
}
