package com.agrieasy.uam.exception.handler;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.agrieasy.uam.exception.InvalidMobileNoException;
import com.agrieasy.uam.exception.InvalidUsercredential;
import com.agrieasy.uam.exception.UserNotFoundException;

@ControllerAdvice
public class UAMExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Exceptionresponse> userNotFoundException(final HttpServletRequest request,
			final UserNotFoundException ex) {
		Exceptionresponse response = new Exceptionresponse();
		response.setExceptionMessage(populateServiceErrorWithCodeAndMessage(ex.getMessage(),new Locale("en")));
		response.setExceptionCode(ex.getMessage());
		return new ResponseEntity<Exceptionresponse>(response, HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidUsercredential.class)
	public ResponseEntity<Exceptionresponse> invalidUsercredential(final HttpServletRequest request,
			final InvalidUsercredential ex) {
		Exceptionresponse response = new Exceptionresponse();
		response.setExceptionMessage(populateServiceErrorWithCodeAndMessage(ex.getMessage(),new Locale("en")));
		response.setExceptionCode(ex.getMessage());
		return new ResponseEntity<Exceptionresponse>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidMobileNoException.class)
	public ResponseEntity<Exceptionresponse> invalidMobileNoException(final HttpServletRequest request,
			final InvalidMobileNoException ex) {
		Exceptionresponse response = new Exceptionresponse();
		response.setExceptionMessage(populateServiceErrorWithCodeAndMessage(ex.getMessage(),new Locale("en")));
		response.setExceptionCode(ex.getMessage());
		return new ResponseEntity<Exceptionresponse>(response, HttpStatus.OK);
	}
	
	public String populateServiceErrorWithCodeAndMessage(String errorCode, Locale message) {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		String str = messageSource.getMessage(errorCode, null, message);
		return str;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Exceptionresponse> handleValidationError(MethodArgumentNotValidException ex) {
		Exceptionresponse response = new Exceptionresponse();
		BindingResult bindingResult = ex.getBindingResult();
		FieldError fieldError = bindingResult.getFieldError();
		String code = fieldError.getDefaultMessage();
		System.out.println("#### Exception Handler #### "+ code);
		response.setExceptionMessage(populateServiceErrorWithCodeAndMessage(code,new Locale("en")));
		response.setExceptionCode(code);
		return new ResponseEntity<Exceptionresponse>(response, HttpStatus.BAD_REQUEST);
	
	}

}
