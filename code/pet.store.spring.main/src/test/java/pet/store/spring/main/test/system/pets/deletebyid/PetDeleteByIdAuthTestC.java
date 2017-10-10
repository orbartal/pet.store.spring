package pet.store.spring.main.test.system.pets.deletebyid;

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
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;

/**
 Test the Pets delete api over entire system including:
 filters, spring configuration, controllers and services.
 Focus on security testing.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PetDeleteByIdAuthTestC {
	
	@Value("${local.server.port}")
	protected int m_port;

	@Autowired
	protected TestRestTemplate m_restTemplate;
	
	protected int m_id = Integer.MAX_VALUE;
	
	protected String getBaseUrl() {
		return "http://localhost:" + m_port + PetsControllerI.URL_PATH;
	}
	
	@Test
	public void deleteByInvalid() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.INVALID);
		assertThat(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}
	
	@Test
	public void deleteByAnonymous() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.ANONYMOUS);
		assertThat(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}
	
	@Test
	public void deleteByLimited() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.LIMITED);
		assertThat(org.springframework.http.HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	}

	@Test
	public void deleteByAdmin() {
		ResponseEntity<String> response = getPetFromServer (TokensExamplesI.ADMIN);
		assertThat(org.springframework.http.HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}
	
	protected ResponseEntity<String> getPetFromServer (String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);   
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		String strUrl = getBaseUrl()+PetsControllerI.DELETE_BY_ID_URL_PATH;
		strUrl = strUrl.replace("{petId}", m_id+"");
		ResponseEntity<String> response = m_restTemplate.exchange
				(strUrl, HttpMethod.DELETE, httpEntity, String.class);
		return response;
	}
}