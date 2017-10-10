package pet.store.spring.web.pet.test.unit.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import pet.store.spring.web.pet.controllers.classes.PetsControllerC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.exceptions.PetNotFoundException;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;
import pet.store.spring.web.pet.utils.JsonSpringUtilityC;

@SpringBootTest(classes={
		PetsControllerC.class, 
		PetsWebServiceI.class, 
		JsonSpringUtilityC.class})
public class PetReadByIdControllerUnitTestC extends AbstractPetWebUnitTestC {

	@Before
    public void setup() throws Exception {
		super.init(PetsControllerI.READ_BY_ID_URL_PATH);
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
    	return m_url.replace("{petId}", id+"");
    }
}