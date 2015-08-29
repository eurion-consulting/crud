package org.wn.util;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wn.model.ErrorMessage;

public class ResponseUtil {

	//TODO: remove from this class and make it configurable by the user
	public static final Integer NUMBER_OF_PERSONS_PER_PAGE = 5;
	public static final String NUMBER_OF_PERSONS_PER_PAGE_STR = NUMBER_OF_PERSONS_PER_PAGE.toString();
		
	
	public static ResponseEntity<ErrorMessage> constructErrorResponse(Exception ex, HttpStatus status){
		
		ErrorMessage err = new ErrorMessage();
		err.setMessage(ex.getMessage());
		Optional<Throwable> reason =  Optional.ofNullable(ex.getCause());
		reason.ifPresent(r -> err.setReason(r.getMessage()));
		return new ResponseEntity<ErrorMessage>(err, status);
	}
	
	
}
