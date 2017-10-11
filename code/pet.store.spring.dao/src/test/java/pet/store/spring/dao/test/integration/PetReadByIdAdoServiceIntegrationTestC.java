package pet.store.spring.dao.test.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import pet.store.spring.dao.converters.classes.ConversionDaoServiceC;
import pet.store.spring.dao.model.classes.PetDaoEntityC;
import pet.store.spring.dao.repositories.PetsDaoRepositoryI;
import pet.store.spring.dao.services.classes.PetsDaoServiceC;
import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={
		PetsDaoRepositoryI.class,
		ConversionDaoServiceC.class,
		PetsDaoServiceC.class
})
public class PetReadByIdAdoServiceIntegrationTestC {
	
	@Mock
	PetsDaoRepositoryI m_petsDaoRepository;

	@Autowired
	@Spy
	protected ConversionDaoServiceC m_conversionDaoService;
	
	@InjectMocks
	protected PetsDaoServiceC m_petsDaoService;

	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
        
        assertThat(m_petsDaoRepository).isNotNull();
        assertThat(m_conversionDaoService).isNotNull();
        assertThat(m_petsDaoService).isNotNull();
    }
	
	@Test
    public void getPetByValidId() throws Exception {
		long petId = 1;
		PetDaoEntityC dbPet = new PetDaoEntityC (1, "dog1", "free");
		PetLogicEntityI logicPet = new PetLogicEntityC (1, "dog1", "free");
		
		when(m_petsDaoRepository.findOne(logicPet.getId())).thenReturn(dbPet);
		PetLogicEntityI logicPetResult = m_petsDaoService.read(petId);
		assertTrue (logicPetResult.equals(logicPet));
    }
	

	@Test //(expected = PetNotFoundException.class)
    public void getPetByInValidId() throws Exception {
		long petId = 1;
		when(m_petsDaoRepository.findOne(petId)).thenReturn(null);
		PetLogicEntityI logicPetResult = m_petsDaoService.read(petId);
		assertTrue (logicPetResult==null);
    }

}