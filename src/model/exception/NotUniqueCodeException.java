package model.exception;

/**
 * This Exception is launched when there are more same codes.
 * 
 * @author josephgiovanelli
 *
 */
public class NotUniqueCodeException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * This method return the message error.
     * @return : the message error.
     */
    public String toString() {
        return "Not Unique Code:";
    }

}
