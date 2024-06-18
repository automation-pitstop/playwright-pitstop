package org.nimit.core;

public class WrongPageException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public WrongPageException(String message){
        super(message);
    }
}

