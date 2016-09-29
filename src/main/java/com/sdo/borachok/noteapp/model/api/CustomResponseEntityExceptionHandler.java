package com.sdo.borachok.noteapp.model.api;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sdo.borachok.noteapp.model.person.exception.EmailAlreadyExistException;
import com.sdo.borachok.noteapp.model.person.exception.IncorrectLoginOrPasswordException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {

		ApiError apiError = new ApiError(NOT_FOUND, ex.getLocalizedMessage(), Instant.now());

		return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ EmailAlreadyExistException.class })
	public ResponseEntity<ApiError> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {

		ApiError apiError = new ApiError(CONFLICT, ex.getLocalizedMessage(), Instant.now());

		return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {

		ApiError apiError = new ApiError(FORBIDDEN, ex.getLocalizedMessage(), Instant.now());

		return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ IncorrectLoginOrPasswordException.class })
	public ResponseEntity<ApiError> handleIncorrectLoginOrPasswordException(IncorrectLoginOrPasswordException ex) {

		ApiError apiError = new ApiError(UNAUTHORIZED, ex.getLocalizedMessage(), Instant.now());

		return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ApiError> handleAll(Exception ex, WebRequest request) {

		ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), Instant.now());

		return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
	}
}
