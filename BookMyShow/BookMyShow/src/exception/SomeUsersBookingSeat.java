package exception;

public class SomeUsersBookingSeat extends RuntimeException {
    public SomeUsersBookingSeat(String message) {
        super(message);
    }
}
