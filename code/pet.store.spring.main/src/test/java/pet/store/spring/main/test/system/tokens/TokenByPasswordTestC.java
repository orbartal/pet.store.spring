package pet.store.spring.main.test.system.tokens;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pet.store.spring.main.test.system.utiles.PasswordsExampesI;
import pet.store.spring.main.test.system.utiles.TokensExamplesI;
import pet.store.spring.web.security.controllers.interfaces.SecurityTokenControllerI;

/**
 Test the tokens provider api over entire system including:
 filters, spring configuration, controllers and services.
 Focus on security testing.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableJpaRepositories(basePackages = {"pet"})
@EntityScan(basePackages = {"pet"})
@ComponentScan(basePackages = {"pet"})
public class TokenByPasswordTestC {

	@Value("${local.server.port}")
	private int m_port;
	
	@Autowired
	private TestRestTemplate m_restTemplate;
	
	private String getBaseUrl() {
		return "http://localhost:" + m_port + SecurityTokenControllerI.URL_PATH;
	}
	
	@Test
	public void getTokenForAdmin () {
		ResponseEntity<String> response = getTokenFromServer (PasswordsExampesI.ADMIN_USER, PasswordsExampesI.ADMIN_PASSWORD);
		assertThat(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertThat(TokensExamplesI.ADMIN.equals(response.getBody()));
	}
	
	@Test
	public void getTokenForLimit () {
		ResponseEntity<String> response = getTokenFromServer (PasswordsExampesI.LIMIT_USER, PasswordsExampesI.LIMIT_PASSWORD);
		assertThat(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
		assertThat(TokensExamplesI.LIMITED.equals(response.getBody()));
	}
	
	protected ResponseEntity<String> getTokenFromServer(String strUserName, String strUserPassword) {
		String strUrl = getBaseUrl()+SecurityTokenControllerI.TOKEN_BY_PASSWORD_URL_PATH;
		strUrl = strUrl.replace("{username}", strUserName);
		strUrl = strUrl.replace("{password}", strUserPassword);
		HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<String> result = m_restTemplate.exchange
				(strUrl, HttpMethod.GET, httpEntity, String.class);
		return result;
	}
}