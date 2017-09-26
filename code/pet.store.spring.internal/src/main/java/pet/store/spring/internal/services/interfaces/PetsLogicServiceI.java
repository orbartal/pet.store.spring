package pet.store.spring.internal.services.interfaces;

import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

public interface PetsLogicServiceI {
	public void create(PetLogicEntityI pet) throws Exception;
	public PetLogicEntityI read(int id) throws Exception;
	public void delete(int id) throws Exception;
}