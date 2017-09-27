package pet.store.spring.web.pet.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=org.springframework.http.HttpStatus.NOT_FOUND, reason="No such pet in store")  // 404
public class PetNotFoundException extends Exception {
	
	private static final long serialVersionUID = 6778649783098374895L;
	
	public PetNotFoundException(String message) {
		super(message);
	}
	public PetNotFoundException(Exception e) {
		super(e);
	}
}
