package pet.store.spring.main.test.system.hello;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pet.store.spring.main.test.system.AbstractSystemTestC;
import pet.store.spring.web.security.tokens.TokensExamplesI;
import pet.store.spring.web.swagger.controllers.interfaces.HelloWorldControllerI;


/**System Test for the HelloWorld api.*/
public class HelloWorldSystemTestC extends AbstractSystemTestC {

	@Before
	public void before() throws Exception {
		setUrl (HelloWorldControllerI.URL_PATH);
	}
	
	protected ResponseEntity<String> getHelloFromServer (String token) {
		return m_restTemplate.exchange (getUrl(), HttpMethod.GET, getHttpEntity(token), String.class);
	}

	@Test
	public void helloByInvalid() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.STRING_TOKEN_INVALID);
		assertTrue(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}
	
	@Test
	public void helloByAnonymous() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.STRING_TOKEN_ANONYMOUS);
		assertTrue(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}

	@Test
	public void helloByLimited() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.STRING_TOKEN_LIMITED);
		assertTrue(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertTrue(response.getBody().equals(HelloWorldControllerI.HELLO_RESULT));
	}

	@Test
	public void helloByAdmin() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.STRING_TOKEN_ADMIN);
		assertTrue(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertTrue(response.getBody().equals(HelloWorldControllerI.HELLO_RESULT));
	}
	
}