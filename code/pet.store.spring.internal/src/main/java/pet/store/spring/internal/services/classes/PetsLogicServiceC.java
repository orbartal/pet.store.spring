package pet.store.spring.internal.services.classes;

import org.springframework.stereotype.Service;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsDaoServiceI;
import pet.store.spring.internal.services.interfaces.PetsLogicServiceI;

@Service("PetsLogicServiceC")
public class PetsLogicServiceC implements PetsLogicServiceI {
	
	protected PetsDaoServiceI m_petsDaoService;
	
	public PetsLogicServiceC(PetsDaoServiceI petsDaoService) {
		m_petsDaoService = petsDaoService;
	}

	@Override
	public void create(PetLogicEntityI pet) throws Exception {
		m_petsDaoService.create(pet);;
	}

	@Override
	public PetLogicEntityI read(int id) throws Exception {
		return m_petsDaoService.read(id);
	}

	@Override
	public void delete(int id) throws Exception {
		m_petsDaoService.delete(id);
	}

}