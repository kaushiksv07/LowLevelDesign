package exception;

public class ElevatorNotFound extends RuntimeException{
    public ElevatorNotFound(){
        super("Elevator not found");
    }
    public ElevatorNotFound(String message){
        super(message);
    }
}
