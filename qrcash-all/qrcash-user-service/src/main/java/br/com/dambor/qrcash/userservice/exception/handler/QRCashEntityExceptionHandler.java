package br.com.dambor.qrcash.userservice.exception.handler;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dambor.qrcash.userservice.exception.DuplicatedRegisterException;


@ControllerAdvice
public class QRCashEntityExceptionHandler extends ResponseEntityExceptionHandler{

	private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler({ DuplicatedRegisterException.class })
	public ResponseEntity<Object> handleDuplicatedRegisterException(DuplicatedRegisterException ex, WebRequest request) {
		log.error(ex.getMessage());
		Error error = new Error("Duplicate Register", ex.getMessage());
		
		return super.handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}

class Error {
	private String message;
	private String messageTecnical;
	
	public Error(String message, String messageTecnical) {
		super();
		this.message = message;
		this.messageTecnical = messageTecnical;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageTecnical() {
		return messageTecnical;
	}
	public void setMessageTecnical(String messageTecnical) {
		this.messageTecnical = messageTecnical;
	}
	
	
}
