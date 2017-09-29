package pet.store.spring.web.security.converters.classes;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import pet.store.spring.web.security.model.classes.TokenAuthenticationC;

public class ConverterServletRequestToAuthenticationC implements Converter <ServletRequest, Authentication>{

	@Override
	public Authentication convert(ServletRequest source) {
		String strJwt;
		try {
			strJwt = getJwtAuthorization(source);
			Authentication auth1 = new TokenAuthenticationC (strJwt);
			return auth1;
		} catch (Exception e) {
			return null;
		}
	}
	
	protected String getJwtAuthorization(ServletRequest request) throws Exception{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String strJwt =  httpRequest.getHeader("Authorization");
		Enumeration<String> eNames = httpRequest.getHeaderNames();
		List<String> lstNames = Collections.list(eNames);
        if (strJwt == null) {
            throw new InsufficientAuthenticationException("Authorization header not found");
        }
        return strJwt;
	}
}