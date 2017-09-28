package pet.store.spring.web.security.services.interfaces;

import org.springframework.security.core.Authentication;

public interface TokenSecurityServiceI<T> {
	public Authentication getAuth (String strToken) throws Exception;
	public String getToken(T auth)  throws Exception;
}
