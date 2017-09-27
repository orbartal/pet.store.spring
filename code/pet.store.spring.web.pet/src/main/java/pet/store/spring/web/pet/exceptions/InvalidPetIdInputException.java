package pet.store.spring.web.pet.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=org.springframework.http.HttpStatus.NOT_FOUND, reason="No such pet in store")  // 404
public class InvalidPetIdInputException extends Exception {
	
	private static final long serialVersionUID = 1L;
	protected String m_id;
	
	public InvalidPetIdInputException(String id, Exception e) {
		super (e);
		m_id = id;
	}

	public String getId() {
		return m_id;
	}

	public void setId(String id) {
		this.m_id = id;
	}
}
