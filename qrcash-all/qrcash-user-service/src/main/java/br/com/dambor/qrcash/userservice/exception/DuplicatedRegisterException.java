package br.com.dambor.qrcash.userservice.exception;

public class DuplicatedRegisterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicatedRegisterException(String message) {
		super(message);
	}

}
