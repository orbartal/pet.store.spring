package pet.store.spring.dao.converters.classes;

import org.springframework.core.convert.converter.Converter;
import pet.store.spring.dao.model.interfaces.PetDaoEntityI;
import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

public class ConverterPetDaoToPetLogicC implements Converter <PetDaoEntityI, PetLogicEntityI>{

	@Override
	public PetLogicEntityI convert(PetDaoEntityI source) {
		PetLogicEntityI target = new PetLogicEntityC();
		target.setId(source.getId());
		target.setName(source.getName());
		target.setStatus(source.getStatus());
		return target;
	}
}