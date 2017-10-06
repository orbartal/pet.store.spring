package pet.store.spring.web.pet.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pet.store.spring.web.pet.controllers.classes.PetsControllerC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.exceptions.PetNotFoundException;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={
		PetsControllerC.class, 
		PetsWebServiceI.class, 
		JsonSpringUtilityC.class})
public class PetReadByIdControllerTestC {
	
	@Mock
	PetsWebServiceI m_petsWebService;

	@InjectMocks
	protected PetsControllerC m_controller;
	
	@InjectMocks
	protected JsonSpringUtilityC m_JsonUtility;
	
	protected MockMvc m_mockMvc;
	
  
	@Before
    public void setup() {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
		MockitoAnnotations.initMocks(this);
        m_mockMvc = MockMvcBuilders.standaloneSetup(m_controller).build();
        
        assertThat(m_petsWebService).isNotNull();
        assertThat(m_controller).isNotNull();
        assertThat(m_mockMvc).isNotNull();
        assertThat(m_JsonUtility).isNotNull();
    }
	
	@Test
    public void getPetByValidId() throws Exception {
		PetUiEntityI pet = new PetUiEntityC (1, "dog1", "free");
		String strPet =  m_JsonUtility.toJson(pet);
		when(m_petsWebService.read(pet.getId())).thenReturn(pet);
		ResultActions result = getPet(pet.getId());
		result.andExpect(status().isOk());
		result.andExpect(content().json(strPet));
    }
	
	@Test
    public void getPetByInValidId() throws Exception {
		long petId = 1;
		when(m_petsWebService.read(petId)).thenThrow(new PetNotFoundException(""));
		ResultActions result = getPet(petId);
		result.andExpect(status().isNotFound());
    }
	
	protected ResultActions getPet (long petId) throws Exception {
		return m_mockMvc.perform(get(getPetReadByIdUrl(petId)));
	}
	
    protected String getPetReadByIdUrl(long id) {
    	String methodPath = PetsControllerI.READ_BY_ID_URL_PATH.replace("{petId}", id+"");
    	String strBase = "http://localhost:8080/";
    	return strBase + PetsControllerI.URL_PATH +methodPath;
    }
}