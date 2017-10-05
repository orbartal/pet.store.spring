package pet.store.spring.web.security.exceptions;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=org.springframework.http.HttpStatus.UNAUTHORIZED, reason="Invalid security token")  // 401
public class InvalidAuthenticationTokenException extends AuthenticationCredentialsNotFoundException {
	public InvalidAuthenticationTokenException(String msg, Throwable t) {
		super(msg, t);
	}
	public InvalidAuthenticationTokenException(String msg) {
		super(msg);
	}
	private static final long serialVersionUID = 1L;
}
