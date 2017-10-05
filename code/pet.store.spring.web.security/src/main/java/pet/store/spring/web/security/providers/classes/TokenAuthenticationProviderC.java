package pet.store.spring.web.security.providers.classes;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pet.store.spring.web.security.exceptions.InvalidAuthenticationTokenException;
import pet.store.spring.web.security.model.classes.TokenAuthenticationC;
import pet.store.spring.web.security.services.interfaces.TokenByPasswordSecurityServiceI;

@Component
public class TokenAuthenticationProviderC implements AuthenticationProvider {

	protected TokenByPasswordSecurityServiceI m_tokenService;
	
	public TokenAuthenticationProviderC (TokenByPasswordSecurityServiceI tokenService) {
		m_tokenService = tokenService;
	}

	public boolean supports(Class<?> authentication) {
		return TokenAuthenticationC.class.isAssignableFrom(authentication);
	}
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String strJwt =  getToken(authentication);
		return getAuth(strJwt);
	}
	
	protected String getToken(Authentication authentication) throws InvalidAuthenticationTokenException{
    	Object credentials = authentication.getCredentials();
		if ((credentials==null) || (credentials.equals("null"))) {
			throw new InvalidAuthenticationTokenException ("User have no credentials. Please login.");
		}else if (!(credentials instanceof  String)) {
			throw new InvalidAuthenticationTokenException (credentials+"");
		}
		return (String)credentials;
	}
	
	protected Authentication getAuth(String strToken) {
    	try {
    		return m_tokenService.getAuth(strToken);
		} catch (Exception e) {
			throw new AuthenticationServiceException (strToken, e);
		}
	}
}