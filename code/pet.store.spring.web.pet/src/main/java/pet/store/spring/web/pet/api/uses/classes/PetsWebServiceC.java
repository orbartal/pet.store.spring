package pet.store.spring.web.pet.api.uses.classes;

import org.springframework.stereotype.Component;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsLogicServiceI;
import pet.store.spring.web.pet.api.uses.interfaces.PetsWebServiceI;
import pet.store.spring.web.pet.converters.interfaces.ConversionWebServiceI;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

@Component
public class PetsWebServiceC implements PetsWebServiceI {

	protected PetsLogicServiceI m_petsLogicService;
	protected ConversionWebServiceI m_conversionWebService;
	
	public PetsWebServiceC (PetsLogicServiceI petsLogicService, ConversionWebServiceI conversionWebService) {
		m_petsLogicService = petsLogicService;
		m_conversionWebService = conversionWebService;
	}

	@Override
	public void create (PetUiEntityI uiPet) throws Exception {
		PetLogicEntityI logicPet = m_conversionWebService.convert(uiPet, PetLogicEntityI.class);
		m_petsLogicService.create(logicPet);
	}

	@Override
	public PetUiEntityI read (int id) throws Exception {
		PetLogicEntityI logicPet = m_petsLogicService.read(id);
		PetUiEntityI uiPet = m_conversionWebService.convert(logicPet, PetUiEntityI.class);
		return uiPet;
	}

	@Override
	public void delete (int id) throws Exception {
		m_petsLogicService.delete(id);
	}

}