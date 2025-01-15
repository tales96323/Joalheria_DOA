package doa_jewelry.exception;

public class EntityAlreadyExistsException extends RepositoryException {

    public EntityAlreadyExistsException(Class<?> entityClass) {
        super(entityClass.getSimpleName() + " already exists.");
    }

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
