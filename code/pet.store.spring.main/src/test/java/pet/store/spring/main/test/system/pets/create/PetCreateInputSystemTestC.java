package pet.store.spring.main.test.system.pets.create;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
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
import pet.store.spring.web.pet.model.classes.PetUiEntityC;
import pet.store.spring.web.pet.model.interfaces.PetUiEntityI;

/**
 Test the HelloWorld api over entire system including:
 filters, spring config, controllers and services.
 Focuse on security testing.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PetCreateInputSystemTestC {
	
	@Value("${local.server.port}")
	protected int m_port;

	@Autowired
	protected TestRestTemplate m_restTemplate;
	
	protected String getBaseUrl() {
		return "http://localhost:" + m_port + PetsControllerI.URL_PATH;
	}
	
	@Before
    public void setUp() {
	}

	@Test
	public void createValidPetByAdmin() {
		PetUiEntityI pet = new PetUiEntityC (1, "dog1", "free");
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.ADMIN, pet);
		assertThat(org.springframework.http.HttpStatus.OK.equals(response.getStatusCode()));
	}

	@Test
	public void createInValidPetByAdmin() {
		PetUiEntityI pet = new PetUiEntityC (-1, "dog1", "free");
		ResponseEntity<String> response = createPetInServer (TokensExamplesI.ADMIN, pet);
		assertThat(org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED.equals(response.getStatusCode()));
	}
	
	protected ResponseEntity<String> createPetInServer (String token, PetUiEntityI pet) {
		String strUrl = getBaseUrl()+PetsControllerI.CREATE_URL_PATH;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);   
		HttpEntity<PetUiEntityI> request = new HttpEntity<PetUiEntityI>(pet, headers);
		ResponseEntity<String> result = m_restTemplate.exchange(strUrl, HttpMethod.POST, request, String.class);
		return result;
	}
}