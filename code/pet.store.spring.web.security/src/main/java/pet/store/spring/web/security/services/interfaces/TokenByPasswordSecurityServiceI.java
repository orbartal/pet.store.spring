package pet.store.spring.web.security.services.interfaces;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface TokenByPasswordSecurityServiceI extends TokenSecurityServiceI <UsernamePasswordAuthenticationToken>{
	public String getToken(String username, String password)  throws Exception;
}
