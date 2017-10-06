package pet.store.spring.web.pet.services;

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
import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsLogicServiceI;
import pet.store.spring.web.pet.converters.interfaces.ConversionWebServiceI;
import pet.store.spring.web.pet.exceptions.PetNotFoundException;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.classes.PetsWebServiceC;

@RunWith(MockitoJUnitRunner.class)
public class PetReadByIdServiceTestC {
	
	@Mock
	PetsLogicServiceI m_petsLogicService;
	
	@Mock
	protected ConversionWebServiceI m_ConversionWebService;

	@InjectMocks
	protected PetsWebServiceC m_petsWebService;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
        
        assertThat(m_petsLogicService).isNotNull();
        assertThat(m_ConversionWebService).isNotNull();
        assertThat(m_petsWebService).isNotNull();
    }
	
	@Test
    public void getPetByValidId() throws Exception {
		long petId = 1;
		PetUiEntityI uiPet = new PetUiEntityC (1, "dog1", "free");
		PetLogicEntityI logicPet = new PetLogicEntityC (1, "dog1", "free");
		
		when(m_petsLogicService.read(logicPet.getId())).thenReturn(logicPet);
		when(m_ConversionWebService.convert(logicPet, PetUiEntityI.class)).thenReturn(uiPet);
		
		PetUiEntityI uiPet2 = m_petsWebService.read(petId);
		assertTrue (uiPet2.equals(uiPet));
    }
	
	@Test(expected = PetNotFoundException.class)
    public void getPetByInValidId() throws Exception {
		
		long petId = 1;
		PetUiEntityI uiPet = new PetUiEntityC (1, "dog1", "free");
		PetLogicEntityI logicPet = new PetLogicEntityC (1, "dog1", "free");
		
		when(m_petsLogicService.read(petId)).thenThrow(new PetNotFoundException(""));
		when(m_ConversionWebService.convert(logicPet, PetUiEntityI.class)).thenReturn(uiPet);
		
		m_petsWebService.read(petId);
    }
}
