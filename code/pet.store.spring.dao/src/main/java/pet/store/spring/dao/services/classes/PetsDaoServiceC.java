package pet.store.spring.dao.services.classes;

import org.springframework.stereotype.Service;
import pet.store.spring.dao.converters.interfaces.ConversionDaoServiceI;
import pet.store.spring.dao.model.classes.PetDaoEntityC;
import pet.store.spring.dao.repositories.PetsDaoRepositoryI;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsDaoServiceI;

@Service("PetsDaoServiceC")
public class PetsDaoServiceC implements PetsDaoServiceI {
	
	protected PetsDaoRepositoryI m_petsDaoRepository;
	protected ConversionDaoServiceI m_conversionDaoService;

	public PetsDaoServiceC(PetsDaoRepositoryI petsDaoRepository, ConversionDaoServiceI conversionDaoService) {
		m_petsDaoRepository = petsDaoRepository;
		m_conversionDaoService = conversionDaoService;
	}

	@Override
	public void create(PetLogicEntityI logiPet) throws Exception {
		PetDaoEntityC daoPet = m_conversionDaoService.convert(logiPet, PetDaoEntityC.class);
		@SuppressWarnings("unused")
		PetDaoEntityC daoPet2 =  m_petsDaoRepository.save(daoPet);
		daoPet.toString();
	}

	@Override
	public PetLogicEntityI read(long id) throws Exception {
		PetDaoEntityC daoPet = m_petsDaoRepository.findOne(id);
		PetLogicEntityI logiPet = m_conversionDaoService.convert(daoPet, PetLogicEntityI.class);
		return logiPet;
	}

	@Override
	public void delete(long id) throws Exception {
		m_petsDaoRepository.delete(id);
	}
}