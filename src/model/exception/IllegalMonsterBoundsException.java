package model.exception;

/**
 * This Exception is launched when the position of the entity don't respect the limits.
 * 
 * @author josephgiovanelli
 *
 */
public class IllegalMonsterBoundsException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * This method return the message error.
     * @return : the message error.
     */
    public String toString() {
        return "Illegal Monster's Bounds";
    }

}
