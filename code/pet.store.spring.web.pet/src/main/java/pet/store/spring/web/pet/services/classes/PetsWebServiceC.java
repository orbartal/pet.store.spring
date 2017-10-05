package pet.store.spring.web.pet.services.classes;

import org.springframework.stereotype.Service;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsLogicServiceI;
import pet.store.spring.web.pet.converters.interfaces.ConversionWebServiceI;
import pet.store.spring.web.pet.exceptions.InvalidPetInputException;
import pet.store.spring.web.pet.exceptions.PetNotFoundException;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;

@Service("PetsWebServiceC")
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
	public PetUiEntityI read (long nId) throws Exception {
		try {
			PetLogicEntityI logicPet = m_petsLogicService.read(nId);
			PetUiEntityI uiPet = m_conversionWebService.convert(logicPet, PetUiEntityI.class);
			uiPet.toString();
			return uiPet;
		}catch (Exception e) {
			throw new PetNotFoundException(e);
		}
	}

	@Override
	public void delete (long nId) throws Exception {
		try {
			m_petsLogicService.delete(nId);
		}catch (Exception e) {
			throw new PetNotFoundException (e);
		}
	}
}