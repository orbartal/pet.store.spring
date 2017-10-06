package pet.store.spring.web.pet.test.integration;

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
import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsLogicServiceI;
import pet.store.spring.web.pet.controllers.classes.PetsControllerC;
import pet.store.spring.web.pet.converters.classes.ConversionWebServiceC;
import pet.store.spring.web.pet.exceptions.PetNotFoundException;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.classes.PetsWebServiceC;
import pet.store.spring.web.pet.utils.JsonSpringUtilityC;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={
		PetsLogicServiceI.class,
		PetsWebServiceC.class,
		ConversionWebServiceC.class,
		PetsControllerC.class, 
		JsonSpringUtilityC.class})
public class PetReadByIdServiceIntegrationTestC {
	
	@Mock
	PetsLogicServiceI m_petsLogicService;

	@Autowired
	@Spy
	protected ConversionWebServiceC m_conversionWebService;
	
	@InjectMocks
	protected PetsWebServiceC m_petsWebService;

	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
        
        assertThat(m_petsLogicService).isNotNull();
        assertThat(m_conversionWebService).isNotNull();
        assertThat(m_petsWebService).isNotNull();
    }
	
	@Test
    public void getPetByValidId() throws Exception {
		long petId = 1;
		PetUiEntityI uiPetExpected = new PetUiEntityC (1, "dog1", "free");
		PetLogicEntityI logicPet = new PetLogicEntityC (1, "dog1", "free");
		when(m_petsLogicService.read(uiPetExpected.getId())).thenReturn(logicPet);
		PetUiEntityI uiPetResult = m_petsWebService.read(petId);
		assertTrue (uiPetResult.equals(uiPetExpected));
    }
	
	@Test(expected = PetNotFoundException.class)
    public void getPetByInValidId() throws Exception {
		long petId = 1;
		when(m_petsLogicService.read(petId)).thenThrow(new PetNotFoundException(""));		
		m_petsWebService.read(petId);
    }
}