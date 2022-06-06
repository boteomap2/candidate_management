package fa.academy.custom;

public class EmailException extends RuntimeException {

    public EmailException(String errorMessage) {
        super(errorMessage);
    }
}
