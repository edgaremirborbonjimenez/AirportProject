package exception;

public class FlightException extends RuntimeException {
    public FlightException(String message) {
        super(message);
    }
}