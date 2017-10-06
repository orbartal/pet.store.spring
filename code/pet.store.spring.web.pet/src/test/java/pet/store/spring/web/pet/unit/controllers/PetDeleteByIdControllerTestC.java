package pet.store.spring.web.pet.unit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={
		PetsControllerC.class, 
		PetsWebServiceI.class, 
		JsonSpringUtilityC.class})
public class PetDeleteByIdControllerTestC {
	
	@Mock
	PetsWebServiceI m_petsWebService;

	@InjectMocks
	protected PetsControllerC m_controller;
	
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
    }
	
	@Test
    public void getPetByValidId() throws Exception {
		long petId = 1;
		doNothing().when(m_petsWebService).delete(petId);
		ResultActions result = deletePet(petId);
		result.andExpect(status().isOk());
    }

	@Test
    public void getPetByInValidId() throws Exception {
		long petId = 1;
		doThrow(new PetNotFoundException("")).when(m_petsWebService).delete(petId);;
		ResultActions result = deletePet(petId);
		result.andExpect(status().isNotFound());
    }
	
	protected ResultActions deletePet (long petId) throws Exception {
		return m_mockMvc.perform(delete(getPetDeleteByIdUrl(petId)));
	}
	
    protected String getPetDeleteByIdUrl(long id) {
    	String methodPath = PetsControllerI.DELETE_BY_ID_URL_PATH.replace("{petId}", id+"");
    	String strBase = "http://localhost:8080/";
    	return strBase + PetsControllerI.URL_PATH +methodPath;
    }
}