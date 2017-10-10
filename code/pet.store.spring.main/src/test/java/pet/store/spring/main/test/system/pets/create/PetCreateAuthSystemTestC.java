package pet.store.spring.main.test.system.pets.create;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pet.store.spring.main.test.system.AbstractSystemTestC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;
import pet.store.spring.web.security.tokens.TokensExamplesI;

/**System Test for the create pet api.*/

public class PetCreateAuthSystemTestC extends AbstractSystemTestC {
	
	protected PetUiEntityI m_pet = null;
	
	@Before
	public void before() throws Exception {
		m_pet = new PetUiEntityC (1, "dog1", "free");
		setUrl (PetsControllerI.URL_PATH+PetsControllerI.CREATE_URL_PATH);
	}
	
	protected ResponseEntity<String> createPetInServer (String token) {
		HttpEntity<PetUiEntityI> request = 
				new HttpEntity<PetUiEntityI>(m_pet, getHttpEntity(token).getHeaders());
		return m_restTemplate.exchange (getUrl(), HttpMethod.POST, request, String.class);
	}
	
	@Test
	public void createByInvalid() {
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.STRING_TOKEN_INVALID);
		assertTrue(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}
	
	@Test
	public void createByAnonymous() {
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.STRING_TOKEN_ANONYMOUS);
		assertTrue(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}
	
	@Test
	public void createByLimited() {
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.STRING_TOKEN_LIMITED);
		assertTrue(org.springframework.http.HttpStatus.FORBIDDEN.equals(response.getStatusCode()));
	}

	@Test
	public void createByAdmin() {
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.STRING_TOKEN_ADMIN);
		assertTrue(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
	}
}