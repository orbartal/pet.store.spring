package pet.store.spring.web.pet.test.unit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pet.store.spring.web.pet.controllers.classes.PetsControllerC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.exceptions.InvalidPetInputException;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;
import pet.store.spring.web.pet.utils.JsonSpringUtilityC;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={
		PetsControllerC.class, 
		PetsWebServiceI.class, 
		JsonSpringUtilityC.class})
public class PetCreateControllerUnitTestC {
	
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
    public void createValidPet() throws Exception {
		PetUiEntityI pet = new PetUiEntityC (1, "dog1", "free");
		doNothing().when(m_petsWebService).create(pet);
		ResultActions result = createPet(pet);
		result.andExpect(status().isOk());
    }

	@Test
    public void createInValidPet() throws Exception {
		PetUiEntityI pet = new PetUiEntityC (-1, "dog1", "free");
		doThrow(InvalidPetInputException.class).when(m_petsWebService).create(pet);
		ResultActions result = createPet(pet);
		result.andExpect(status().isMethodNotAllowed());
    }

	protected ResultActions createPet (PetUiEntityI pet) throws Exception {
		String strPet =  m_JsonUtility.toJson(pet);
		return m_mockMvc.perform(post(getCreatePetUrl()).
				contentType(MediaType.APPLICATION_JSON).content(strPet));
	}
	
    protected String getCreatePetUrl() {
    	String methodPath = PetsControllerI.CREATE_URL_PATH;
    	String strBase = "http://localhost:8080/";
    	return strBase + PetsControllerI.URL_PATH +methodPath;
    }
}