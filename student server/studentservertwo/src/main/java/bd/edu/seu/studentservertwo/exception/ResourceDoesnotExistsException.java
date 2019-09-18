package bd.edu.seu.studentservertwo.exception;

public class ResourceDoesnotExistsException extends Exception {
    public ResourceDoesnotExistsException(String message) {
        super(message + "doesn't Exists");
    }
}
