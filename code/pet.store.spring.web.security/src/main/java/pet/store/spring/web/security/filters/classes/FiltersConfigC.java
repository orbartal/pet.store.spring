package pet.store.spring.web.security.filters.classes;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import pet.store.spring.web.security.converters.interfaces.ConversionServiceSecurityI;
import pet.store.spring.web.security.filters.interfaces.AuthenticationFilterI;
import pet.store.spring.web.security.filters.interfaces.FiltersConfigI;

@Component
public class FiltersConfigC implements FiltersConfigI {
	
	protected AuthenticationFilterI m_authenticationFilter; 
	public FiltersConfigC (	ProviderManager authenticationManager, ConversionServiceSecurityI converter) { //TODO
		m_authenticationFilter = new AuthenticationByTokenFilterC (authenticationManager, converter);
	}

	public void setFilters(HttpSecurity config) throws Exception {
		 config.addFilterBefore (m_authenticationFilter,  UsernamePasswordAuthenticationFilter.class);
		 config.addFilter(new CorsFilterC ());
	}
}