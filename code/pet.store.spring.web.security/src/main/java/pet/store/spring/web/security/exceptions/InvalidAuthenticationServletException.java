package pet.store.spring.web.security.exceptions;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=org.springframework.http.HttpStatus.UNAUTHORIZED, reason="Invalid authentication")  // 401
public class InvalidAuthenticationServletException extends ServletException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidAuthenticationServletException(Exception e) {
		super(e);
	}
	
}
