package pet.store.spring.web.pet.api.uses.classes;

import org.springframework.stereotype.Component;

import pet.store.spring.web.pet.api.uses.interfaces.PetsWebAdapterI;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

@Component
public class PetsWebAdapterC implements PetsWebAdapterI {

	@Override
	public void create(PetUiEntityI pet) throws Exception {
	}

	@Override
	public PetUiEntityI read(int id) throws Exception {
		return new PetUiEntityC (id, "example", "available");
	}

	@Override
	public void delete(int id) throws Exception {
	}

}
