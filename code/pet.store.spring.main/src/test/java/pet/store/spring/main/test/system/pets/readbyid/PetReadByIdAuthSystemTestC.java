package pet.store.spring.main.test.system.pets.readbyid;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pet.store.spring.main.test.system.AbstractSystemTestC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.security.tokens.TokensExamplesI;

/**System Test for the read pet by id api.*/
public class PetReadByIdAuthSystemTestC extends AbstractSystemTestC {
	
	@Before
	public void before() throws Exception {
		setUrl (PetsControllerI.URL_PATH+PetsControllerI.READ_BY_ID_URL_PATH);
	}
	
	protected ResponseEntity<String> getPetFromServer (String token) {
		String strUrl = getUrl().replace("{petId}", Long.MAX_VALUE+"");
		return m_restTemplate.exchange (strUrl, HttpMethod.GET, getHttpEntity(token), String.class);
	}

	@Test
	public void readByInvalid() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.STRING_TOKEN_INVALID);
		assertTrue(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}

	@Test
	public void readByAnonymous() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.STRING_TOKEN_ANONYMOUS);
		assertTrue(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}
	
	@Test
	public void readByLimited() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.STRING_TOKEN_LIMITED);
		assertTrue(org.springframework.http.HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}

	@Test
	public void readByAdmin() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.STRING_TOKEN_ADMIN);
		assertTrue(org.springframework.http.HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}

}