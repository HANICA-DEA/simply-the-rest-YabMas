package nl.han.dea.services.exceptions;

public class ItemNotAvailableException extends RuntimeException {

    public ItemNotAvailableException(){
        super("Item is not available.");
    }

}
