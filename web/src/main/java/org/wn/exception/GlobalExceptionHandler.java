package org.wn.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.wn.model.ErrorMessage;
import org.wn.util.ResponseUtil;

@ControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleServerException(Exception ex) throws IOException{
		return ResponseUtil.constructErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNonExistance(Exception ex) {
		return ResponseUtil.constructErrorResponse(ex, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorMessage> handleUserDuplication(Exception ex) {
		return ResponseUtil.constructErrorResponse(ex, HttpStatus.CONFLICT);
	}
}
