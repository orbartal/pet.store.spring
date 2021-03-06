package pet.store.spring.web.pet.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=org.springframework.http.HttpStatus.UNAUTHORIZED, reason="Invalid id supplied")  // 400
public class InvalidPetIdInputException extends Exception {
	
	private static final long serialVersionUID = 1L;
	protected String m_id;
	
	public InvalidPetIdInputException(String id) {
		m_id = id;
	}

	public String getId() {
		return m_id;
	}

	public void setId(String id) {
		this.m_id = id;
	}
}
