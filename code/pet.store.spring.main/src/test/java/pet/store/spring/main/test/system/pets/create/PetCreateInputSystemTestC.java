package pet.store.spring.main.test.system.pets.create;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pet.store.spring.main.test.system.AbstractSystemTestC;
import pet.store.spring.main.test.system.utiles.TokensExamplesI;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

/**System Test for the create pet api.*/

public class PetCreateInputSystemTestC extends AbstractSystemTestC {
		
	@Before
	public void before() throws Exception {
		setUrl (PetsControllerI.URL_PATH+PetsControllerI.CREATE_URL_PATH);
	}
	
	protected ResponseEntity<String> createPetInServer (String token, PetUiEntityI pet) {
		HttpEntity<PetUiEntityI> request = 
				new HttpEntity<PetUiEntityI>(pet, getHttpEntity(token).getHeaders());
		return m_restTemplate.exchange (getUrl(), HttpMethod.POST, request, String.class);
	}
	
	@Test
	public void createValidPetByAdmin() {
		PetUiEntityI pet = new PetUiEntityC (1, "dog1", "free");
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.ADMIN, pet);
		assertTrue(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
	}

	@Test
	public void createInValidPetByAdmin() {
		PetUiEntityI pet = new PetUiEntityC (-1, "dog1", "free");
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.ADMIN, pet);
		assertTrue(org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED.equals(response.getStatusCode()));
	}
}