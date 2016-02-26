package model.exception;

public class NotUniqueCodeException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public String toString() {
        return "Not Unique Code:";
    }

}
