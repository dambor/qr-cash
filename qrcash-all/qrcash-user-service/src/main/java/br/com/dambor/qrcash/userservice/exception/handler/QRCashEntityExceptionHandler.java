package br.com.dambor.qrcash.userservice.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.dambor.qrcash.userservice.exception.DuplicatedRegisterException;


@ControllerAdvice
public class QRCashEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;	
	
	private Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler({ DuplicatedRegisterException.class })
	public ResponseEntity<Object> handleDuplicatedRegisterException(DuplicatedRegisterException ex, WebRequest request) {
		
		log.debug(ex.getMessage(), ex);
		Error error = new Error("Duplicate Register", ex.getMessage());
		
		return super.handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		log.debug(ex.getMessage(), ex);
		String mensagemUsuario = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Error> erros = Arrays.asList(new Error(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		log.debug(ex.getMessage(), ex);
		List<Error> erros = createErroList(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		log.debug(ex.getMessage(), ex);
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Error> erros = Arrays.asList(new Error(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		
		log.debug(ex.getMessage(), ex);
		String mensagemUsuario = messageSource.getMessage("resource.operation.forbidden", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> erros = Arrays.asList(new Error(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}	
	
	/**
	 * @param bindingResult
	 * @return
	 */
	private List<Error> createErroList(BindingResult bindingResult) {
		List<Error> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String messageDev = fieldError.toString();
			erros.add(new Error(message, messageDev));
		}
		return erros;
	}
}

/**
 * @author saulo.borges
 *
 */
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
