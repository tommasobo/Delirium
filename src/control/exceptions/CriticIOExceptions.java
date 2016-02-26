package control.exceptions;

import java.io.IOException;

public class CriticIOExceptions extends RuntimeException {

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
