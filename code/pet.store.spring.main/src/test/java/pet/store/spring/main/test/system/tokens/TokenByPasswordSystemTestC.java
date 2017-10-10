package pet.store.spring.main.test.system.tokens;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pet.store.spring.main.test.system.AbstractSystemTestC;
import pet.store.spring.main.test.system.utiles.PasswordsExampesI;
import pet.store.spring.web.security.controllers.interfaces.SecurityTokenControllerI;
import pet.store.spring.web.security.tokens.TokensExamplesI;

/**System Test for the TokenByPassword api.*/
public class TokenByPasswordSystemTestC extends AbstractSystemTestC {

	@Before
	public void before() throws Exception {
		setUrl (SecurityTokenControllerI.URL_PATH+SecurityTokenControllerI.TOKEN_BY_PASSWORD_URL_PATH);
	}
	
	protected ResponseEntity<String> getTokenFromServer (String strUserName, String strUserPassword) {
		String strUrl = getUrl().replace("{username}", strUserName).replace("{password}", strUserPassword);
		return m_restTemplate.exchange (strUrl, HttpMethod.GET, getHttpEntity(""), String.class);
	}

	@Test
	public void getTokenForAdmin () {
		ResponseEntity<String> response = getTokenFromServer (PasswordsExampesI.ADMIN_USER, PasswordsExampesI.ADMIN_PASSWORD);
		assertTrue(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertTrue(TokensExamplesI.STRING_TOKEN_ADMIN.equals(response.getBody()));
	}
	
	@Test
	public void getTokenForLimit () {
		ResponseEntity<String> response = getTokenFromServer (PasswordsExampesI.LIMIT_USER, PasswordsExampesI.LIMIT_PASSWORD);
		assertTrue(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertTrue(TokensExamplesI.STRING_TOKEN_LIMITED.equals(response.getBody()));
	}
	
	@Test
	public void getTokenForInvalid () {
		ResponseEntity<String> response = getTokenFromServer (PasswordsExampesI.INVALID_USER, PasswordsExampesI.INVALID_PASSWORD);
		assertTrue(org.springframework.http.HttpStatus.BAD_REQUEST.equals(response.getStatusCode()));
	}	
}