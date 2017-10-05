package pet.store.spring.main.test.system.hello;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pet.store.spring.main.test.system.utiles.TokensExamplesI;
import pet.store.spring.web.swagger.controllers.interfaces.HelloWorldControllerI;

/**
 Test the HelloWorld api over entire system including:
 filters, spring config, controllers and services.
 Focuse on security testing.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloWorldTestC {
	
	@Value("${local.server.port}")
	private int m_port;
	
	@Autowired
	private TestRestTemplate m_restTemplate;
	
	private String getBaseUrl() {
		return "http://localhost:" + m_port + HelloWorldControllerI.URL_PATH;
	}
	
	@Test
	public void helloByInvalid() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.INVALID);
		assertThat(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}

	@Test
	public void helloByAnonymous() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.ANONYMOUS);
		assertThat(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}

	@Test
	public void helloByLimited() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.LIMITED);
		assertThat(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertThat(response.getBody()).isEqualTo(HelloWorldControllerI.HELLO_RESULT);
	}

	@Test
	public void helloByAdmin() {
		ResponseEntity<String> response = getHelloFromServer (TokensExamplesI.ADMIN);
		assertThat(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertThat(response.getBody()).isEqualTo(HelloWorldControllerI.HELLO_RESULT);
	}

	protected ResponseEntity<String> getHelloFromServer (String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);   
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		String strUrl = getBaseUrl()+HelloWorldControllerI.HELLO_URL_PATH;
		ResponseEntity<String> response = m_restTemplate.exchange
				(strUrl, HttpMethod.GET, httpEntity, String.class);
		return response;
	}
}