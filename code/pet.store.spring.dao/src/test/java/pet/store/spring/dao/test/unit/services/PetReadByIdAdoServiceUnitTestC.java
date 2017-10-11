package pet.store.spring.dao.test.unit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import pet.store.spring.dao.converters.interfaces.ConversionDaoServiceI;
import pet.store.spring.dao.model.classes.PetDaoEntityC;
import pet.store.spring.dao.repositories.PetsDaoRepositoryI;
import pet.store.spring.dao.services.classes.PetsDaoServiceC;
import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

@RunWith(MockitoJUnitRunner.class)
public class PetReadByIdAdoServiceUnitTestC {
	
	@Mock
	protected PetsDaoRepositoryI m_petsDaoRepository;
	
	@Mock
	protected ConversionDaoServiceI m_conversionAdoService;

	@InjectMocks
	protected PetsDaoServiceC m_petsAdoService;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
        assertThat(m_petsDaoRepository).isNotNull();
        assertThat(m_conversionAdoService).isNotNull();
        assertThat(m_petsAdoService).isNotNull();
    }
	
	@Test
    public void getPetByValidId() throws Exception {
		long petId = 1;
		PetDaoEntityC dbPet = new PetDaoEntityC (1, "dog1", "free");
		PetLogicEntityI logicPet = new PetLogicEntityC (1, "dog1", "free");
		
		when(m_petsDaoRepository.findOne(logicPet.getId())).thenReturn(dbPet);
		when(m_conversionAdoService.convert(dbPet, PetLogicEntityI.class)).thenReturn(logicPet);
		
		PetLogicEntityI logicPet2 = m_petsAdoService.read(petId);
		assertTrue (logicPet2.equals(logicPet));
    }

	@Test
    public void getPetByInValidId() throws Exception {
		long petId = 1;
		when(m_petsDaoRepository.findOne(petId)).thenReturn(null);		
		PetLogicEntityI logicPet2 = m_petsAdoService.read(petId);
		assertTrue (logicPet2==null);
    }

}
