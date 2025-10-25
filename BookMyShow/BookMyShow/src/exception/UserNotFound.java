package exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }
    public UserNotFound(String msg){
        super(msg);
    }
}
