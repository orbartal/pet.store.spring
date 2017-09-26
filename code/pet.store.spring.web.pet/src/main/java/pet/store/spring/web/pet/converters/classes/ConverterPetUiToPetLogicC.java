package pet.store.spring.web.pet.converters.classes;

import org.springframework.core.convert.converter.Converter;

import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public class ConverterPetUiToPetLogicC implements Converter <PetUiEntityI, PetLogicEntityI>{

	@Override
	public PetLogicEntityI convert(PetUiEntityI source) {
		PetLogicEntityI target = new PetLogicEntityC();
		target.setId(source.getId());
		target.setName(source.getName());
		target.setStatus(source.getStatus());
		return target;
	}

}
