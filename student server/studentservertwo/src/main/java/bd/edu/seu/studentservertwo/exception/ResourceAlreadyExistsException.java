package bd.edu.seu.studentservertwo.exception;

public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException(String message) {
        super(message + "Resource Already Exits");
    }
}
