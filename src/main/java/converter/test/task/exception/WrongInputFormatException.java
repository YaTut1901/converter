package converter.test.task.exception;

public class WrongInputFormatException extends RuntimeException {
    public WrongInputFormatException(String message) {
        super(message);
    }
}
