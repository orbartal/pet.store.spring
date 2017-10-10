package pet.store.spring.web.pet.test.unit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pet.store.spring.web.pet.controllers.classes.PetsControllerC;
import pet.store.spring.web.pet.controllers.interfaces.PetsControllerI;
import pet.store.spring.web.pet.services.interfaces.PetsWebServiceI;
import pet.store.spring.web.pet.utils.JsonSpringUtilityC;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class AbstractPetWebUnitTestC {

	protected String m_url;
	
	@Mock
	PetsWebServiceI m_petsWebService;

	@InjectMocks
	protected PetsControllerC m_controller;
	
	@InjectMocks
	protected JsonSpringUtilityC m_JsonUtility;
	
	protected MockMvc m_mockMvc;
	
	public void init (String strMethodUrl) throws Exception {
		m_url = "http://localhost:8080/" + PetsControllerI.URL_PATH + strMethodUrl;
		
		MockitoAnnotations.initMocks(this);
        m_mockMvc = MockMvcBuilders.standaloneSetup(m_controller).build();
        assertThat(m_JsonUtility).isNotNull();
        assertThat(m_petsWebService).isNotNull();
        assertThat(m_controller).isNotNull();
        assertThat(m_mockMvc).isNotNull();
	}
}
