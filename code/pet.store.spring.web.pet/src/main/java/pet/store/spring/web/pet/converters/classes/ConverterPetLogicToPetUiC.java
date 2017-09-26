package pet.store.spring.web.pet.converters.classes;

import org.springframework.core.convert.converter.Converter;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public class ConverterPetLogicToPetUiC implements Converter <PetLogicEntityI, PetUiEntityI>{

	@Override
	public PetUiEntityI convert (PetLogicEntityI source) {
		PetUiEntityI target = new PetUiEntityC();
		target.setId(source.getId());
		target.setName(source.getName());
		target.setStatus(source.getStatus());
		return target;
	}
}