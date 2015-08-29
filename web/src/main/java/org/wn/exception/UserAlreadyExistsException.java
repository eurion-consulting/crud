package org.wn.exception;


public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(){
		super("User Already Exists!");
	}
	
	public UserAlreadyExistsException(String s){
		super(s);
	}
	
	public UserAlreadyExistsException(String s, Throwable t){
		super(s, t);
	}
}
