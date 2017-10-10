package pet.store.spring.web.pet.test.unit.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import pet.store.spring.web.pet.controllers.classes.PetsControllerC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.exceptions.PetNotFoundException;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;
import pet.store.spring.web.pet.utils.JsonSpringUtilityC;

@SpringBootTest(classes={
		PetsControllerC.class, 
		PetsWebServiceI.class, 
		JsonSpringUtilityC.class})
public class PetDeleteByIdControllerUnitTestC extends AbstractPetWebUnitTestC {
	
	
  
	@Before
    public void setup() throws Exception {
		super.init(PetsControllerI.DELETE_BY_ID_URL_PATH);
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
    	return m_url.replace("{petId}", id+"");
    }
}