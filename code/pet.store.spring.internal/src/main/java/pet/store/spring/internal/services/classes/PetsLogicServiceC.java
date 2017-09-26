package pet.store.spring.internal.services.classes;

import org.springframework.stereotype.Service;

import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsLogicServiceI;

@Service("PetsLogicServiceC")
public class PetsLogicServiceC implements PetsLogicServiceI {

	@Override
	public void create(PetLogicEntityI pet) throws Exception {
		System.out.println("PetsLogicServiceC.create: " + pet);
	}

	@Override
	public PetLogicEntityI read(int id) throws Exception {
		System.out.println("PetsLogicServiceC.read: " + id);
		return new PetLogicEntityC (10, "Logic", "free");
	}

	@Override
	public void delete(int id) throws Exception {
		System.out.println("PetsLogicServiceC.delete: " + id);
	}

}