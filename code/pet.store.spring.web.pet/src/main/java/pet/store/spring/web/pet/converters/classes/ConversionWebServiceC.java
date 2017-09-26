package pet.store.spring.web.pet.converters.classes;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;
import pet.store.spring.web.pet.converters.interfaces.ConversionWebServiceI;

@Service ("ConversionServiceWeb")
public 	class ConversionWebServiceC 
		extends GenericConversionService
		implements ConversionWebServiceI {
	
	public ConversionWebServiceC () {
		addConverter(new ConverterPetLogicToPetUiC());
		addConverter(new ConverterPetUiToPetLogicC());
	}
}