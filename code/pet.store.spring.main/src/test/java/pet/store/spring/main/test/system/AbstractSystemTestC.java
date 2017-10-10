package pet.store.spring.main.test.system;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import pet.store.spring.web.swagger.services.interfaces.SitePropertiesServiceI;

/**
Test the server api over entire system including:
filters, spring configuration, controllers and services.
*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableJpaRepositories(basePackages = {"pet"})
@EntityScan(basePackages = {"pet"})
@ComponentScan(basePackages = {"pet"})
public abstract class AbstractSystemTestC {
	
	@Autowired
	protected TestRestTemplate m_restTemplate;
	
	@Autowired
	protected SitePropertiesServiceI m_sitePropertiesService;
	
	protected String m_baseUrl;
	
	public void setUrl (String strUrl) throws Exception {
		m_baseUrl = m_sitePropertiesService.getBaseUrl() + strUrl;
	}
	
	public String getUrl() {
		return m_baseUrl;
	}

	protected HttpEntity<?> getHttpEntity (String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);   
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		return httpEntity;
	}
}