package pet.store.spring.internal.services.interfaces;

import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

public interface PetsDaoServiceI {
	public void create(PetLogicEntityI pet) throws Exception;
	public PetLogicEntityI read(long id) throws Exception;
	public void delete(long id) throws Exception;
}