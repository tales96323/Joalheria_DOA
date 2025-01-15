package doa_jewelry.exception;

public class EntityNotFoundException extends RepositoryException {
    public EntityNotFoundException(Class message) {
        super(String.valueOf(message));
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
