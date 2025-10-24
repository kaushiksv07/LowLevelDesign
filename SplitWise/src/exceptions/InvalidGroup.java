package exceptions;

public class InvalidGroup extends RuntimeException {
    public InvalidGroup(){
        super("Invalid group");
    }
    public InvalidGroup(String message) {
        super(message);
    }
}
