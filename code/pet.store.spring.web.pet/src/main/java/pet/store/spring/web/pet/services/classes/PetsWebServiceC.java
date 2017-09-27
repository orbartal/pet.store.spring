package pet.store.spring.web.pet.services.classes;

import org.springframework.stereotype.Component;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsLogicServiceI;
import pet.store.spring.web.pet.converters.interfaces.ConversionWebServiceI;
import pet.store.spring.web.pet.exceptions.InvalidPetIdInputException;
import pet.store.spring.web.pet.exceptions.InvalidPetInputException;
import pet.store.spring.web.pet.exceptions.PetNotFoundException;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;

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
		try {
			PetLogicEntityI logicPet = m_conversionWebService.convert(uiPet, PetLogicEntityI.class);
			m_petsLogicService.create(logicPet);
		}catch(Exception e) {
			throw new InvalidPetInputException (e);
		}
	}

	@Override
	public PetUiEntityI read (int id) throws Exception {
		if (id<0) {
			throw new InvalidPetIdInputException (id+"");
		}
		PetLogicEntityI logicPet = m_petsLogicService.read(id);
		PetUiEntityI uiPet = m_conversionWebService.convert(logicPet, PetUiEntityI.class);
		if (uiPet==null) {
			throw new PetNotFoundException("uiPet==null");
		}
		return uiPet;
	}

	@Override
	public void delete (int id) throws Exception {
		if (id<0) {
			throw new InvalidPetIdInputException (id+"");
		}
		m_petsLogicService.delete(id);
	}

}