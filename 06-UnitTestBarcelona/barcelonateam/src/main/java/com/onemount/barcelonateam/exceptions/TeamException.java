package com.onemount.barcelonateam.exceptions;

public class TeamException extends RuntimeException{
    private static final long serialVersionUID = 6171350264427361501L;
    public TeamException(String message) {
        super(message);
    }
    public TeamException(String message, Throwable cause) {
        super(message, cause);
    }   
}
