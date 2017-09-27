package pet.store.spring.dao.converters.classes;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

import pet.store.spring.dao.converters.interfaces.ConversionDaoServiceI;

@Service ("ConversionDaoServiceC")
public 	class ConversionDaoServiceC 
		extends GenericConversionService
		implements ConversionDaoServiceI {
	
	public ConversionDaoServiceC () {
		addConverter(new ConverterPetDaoToPetLogicC());
		addConverter(new ConverterPetLogicToPetDaoC());
	}
}