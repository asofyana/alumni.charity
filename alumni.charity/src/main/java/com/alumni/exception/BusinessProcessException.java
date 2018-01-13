package com.alumni.exception;

public class BusinessProcessException extends Exception {

	public String message;
	
	public BusinessProcessException(String message) {
		this.message = message;
	}

    @Override
    public String getMessage(){
        return message;
    }
    
	private static final long serialVersionUID = 8998010350192459652L;

}
