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
 Test the pets delete api over entire system including:
 filters, spring configuration, controllers and services.
 Focus on security testing.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PetDeleteByIdInputTestC {
	
	@Value("${local.server.port}")
	protected int m_port;

	@Autowired
	protected TestRestTemplate m_restTemplate;
	
	protected String getBaseUrl() {
		return "http://localhost:" + m_port + PetsControllerI.URL_PATH;
	}

	@Test
	public void deleteInValidIdByAdmin() {
		ResponseEntity<String> response = deletePetInServer (TokensExamplesI.ADMIN, -1);
		assertThat(org.springframework.http.HttpStatus.BAD_REQUEST.equals(response.getStatusCode()));
	}
	
	@Test
	public void deleteValidIdByAdmin() {
		ResponseEntity<String> response = deletePetInServer (TokensExamplesI.ADMIN, 1);
		assertThat(org.springframework.http.HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}
	
	protected ResponseEntity<String> deletePetInServer (String token, long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);   
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		String strUrl = getBaseUrl()+PetsControllerI.DELETE_BY_ID_URL_PATH;
		strUrl = strUrl.replace("{petId}", id+"");
		ResponseEntity<String> response = m_restTemplate.exchange
				(strUrl, HttpMethod.DELETE, httpEntity, String.class);
		return response;
	}
}