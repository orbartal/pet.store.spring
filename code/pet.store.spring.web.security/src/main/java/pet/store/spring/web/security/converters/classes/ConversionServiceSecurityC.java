package pet.store.spring.web.security.converters.classes;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;
import pet.store.spring.web.security.converters.interfaces.ConversionServiceSecurityI;

@Service ("ConversionServiceSecurity")
public 	class ConversionServiceSecurityC 
		extends GenericConversionService 
		implements ConversionServiceSecurityI {

	public ConversionServiceSecurityC () {
		addConverter(new ConverterServletRequestToAuthenticationC());
	}
}