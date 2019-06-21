package com.agrieasy.order.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author sumilon.mondal
 *
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	/**
	 * @param exception
	 * @param request
	 * @return ExceptionResponse Object
	 */
	@ExceptionHandler(OrderNotFound.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFound(final OrderNotFound exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorCode("Data Error");
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());

		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param exception
	 * @param request
	 * @return ExceptionResponse Object
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(final Exception exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setErrorCode("Server Error");
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());

		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * @param argumentNotValidException
	 * @param request
	 * @return ExceptionResponse Object
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException argumentNotValidException,
			HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Validation Error");
		response.setErrorMessage(argumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
		response.setRequestedURI(request.getRequestURI());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

}