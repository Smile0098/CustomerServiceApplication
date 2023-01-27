package com.bankapp.customerservice.customexception;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), "101", ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ErrorDetails> handleAllBusinessException(BusinessException ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getErrorCode(), ex.getErrorMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<ErrorDetails> handleAllNoSuchElementException(Exception ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), null, ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(value = NoSuchElementException.class)
//	@ResponseStatus(HttpStatus.CONFLICT)
//	public ErrorDetails handleCustomerAlreadyExistsException(NoSuchElementException ex) {
//		return new ErrorDetails(LocalDate.now(), ex., ex.get);
//	}

	@ExceptionHandler(ControllerException.class)
	public final ResponseEntity<ErrorDetails> handleAllControllerException(ControllerException ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getErrorCode(), ex.getErrorMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
