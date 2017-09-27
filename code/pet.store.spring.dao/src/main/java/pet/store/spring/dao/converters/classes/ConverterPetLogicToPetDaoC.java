package pet.store.spring.dao.converters.classes;

import org.springframework.core.convert.converter.Converter;
import pet.store.spring.dao.model.classes.PetDaoEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

public class ConverterPetLogicToPetDaoC implements Converter <PetLogicEntityI, PetDaoEntityC>{

	@Override
	public PetDaoEntityC convert(PetLogicEntityI source) {
		PetDaoEntityC target = new PetDaoEntityC();
		target.setId(source.getId());
		target.setName(source.getName());
		target.setStatus(source.getStatus());
		return target;
	}
}