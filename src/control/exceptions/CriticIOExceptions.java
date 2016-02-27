package control.exceptions;

import java.io.IOException;

/**
 * Custom runtime exception to notify insolvable IO exception
 * @author magna
 *
 */
public class CriticIOExceptions extends RuntimeException {

    private static final long serialVersionUID = 142811943478530991L;
    IOException exception;

    public CriticIOExceptions(IOException exception) {
        super();
        this.exception = exception;
    }
    
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        this.exception.printStackTrace();
    }
}
