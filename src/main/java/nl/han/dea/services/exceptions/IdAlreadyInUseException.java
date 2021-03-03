package nl.han.dea.services.exceptions;

public class IdAlreadyInUseException extends RuntimeException {

    public IdAlreadyInUseException() {
        super("Id is already in use.");
    }

}
