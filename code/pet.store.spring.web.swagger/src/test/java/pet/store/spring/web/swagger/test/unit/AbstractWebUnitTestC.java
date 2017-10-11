package pet.store.spring.web.swagger.test.unit;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/*
 * https://thepracticaldeveloper.com/2017/07/31/guide-spring-boot-controller-tests/
 */
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc

public class AbstractWebUnitTestC {

	protected MockMvc m_mockMvc;
	protected String m_baseUrl;
	protected Object m_controller;

	public void init (String strUrl, Object controller) throws Exception {
		m_baseUrl = strUrl;
		m_controller = controller;
    	MockitoAnnotations.initMocks(this);
    	m_mockMvc = MockMvcBuilders.standaloneSetup(m_controller).build();
        assertThat(m_controller).isNotNull();
        assertThat(m_mockMvc).isNotNull();
	}
}
