package pet.store.spring.web.pet.test.unit.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pet.store.spring.web.pet.controllers.classes.PetsControllerC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.exceptions.InvalidPetInputException;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;
import pet.store.spring.web.pet.utils.JsonSpringUtilityC;

@SpringBootTest(classes={
		PetsControllerC.class, 
		PetsWebServiceI.class, 
		JsonSpringUtilityC.class})
public class PetCreateControllerUnitTestC extends AbstractPetWebUnitTestC {

	@Before
    public void setup() throws Exception {
        super.init(PetsControllerI.CREATE_URL_PATH);
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
		return m_mockMvc.perform(post(m_url).
				contentType(MediaType.APPLICATION_JSON).content(strPet));
	}
}