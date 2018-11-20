
package pet.store.spring.main.test.system.tokens;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pet.store.spring.main.test.system.AbstractSystemTestC;
import pet.store.spring.main.test.system.utiles.PasswordsExampesI;
import pet.store.spring.web.security.controllers.interfaces.SecurityTokenControllerI;
import pet.store.spring.web.security.tokens.TokensExamplesI;

//System Test for the TokenByPassword api.
public class TokenByPasswordSystemTestC extends AbstractSystemTestC {

	@Before
	public void before() throws Exception {
		setUrl (SecurityTokenControllerI.URL_PATH);
	}
	
	protected ResponseEntity<String> getTokenFromServer(String strUserName, String strUserPassword) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("username", strUserName);
		map.add("password", strUserPassword);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		return m_restTemplate.postForEntity(getUrl(), request, String.class);
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
//*/