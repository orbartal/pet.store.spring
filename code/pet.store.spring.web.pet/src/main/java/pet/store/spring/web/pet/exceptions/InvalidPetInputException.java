package pet.store.spring.web.pet.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED, reason="The given pet data is invalid")  // 405
public class InvalidPetInputException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidPetInputException(String message) {
		super(message);
	}
	public InvalidPetInputException(Exception e) {
		super(e);
	}
	public InvalidPetInputException(String message, Exception e) {
		super(message, e);
	}

}
