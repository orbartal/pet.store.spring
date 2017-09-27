package pet.store.spring.web.pet.services.interfaces;

import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

public interface PetsWebServiceI {
	public void create(PetUiEntityI pet) throws Exception;
	public PetUiEntityI read(int id) throws Exception;
	public void delete(int id) throws Exception;
}