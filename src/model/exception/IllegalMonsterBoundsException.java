package model.exception;

public class IllegalMonsterBoundsException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public String toString() {
        return "Illegal Monster's Bounds";
    }

}
