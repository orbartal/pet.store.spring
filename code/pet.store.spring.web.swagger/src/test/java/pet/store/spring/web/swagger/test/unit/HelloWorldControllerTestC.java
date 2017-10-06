package pet.store.spring.web.swagger.test.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pet.store.spring.web.swagger.controllers.classes.HelloWorldControllerC;
import pet.store.spring.web.swagger.controllers.interfaces.HelloWorldControllerI;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={HelloWorldControllerC.class})
/*
@RunWith(SpringRunner.class)
@SpringBootTest(classes={HelloWorldControllerC.class})
@AutoConfigureMockMvc
*/
public class HelloWorldControllerTestC {
	
	@InjectMocks
	protected HelloWorldControllerC m_controller;
	
	protected MockMvc m_mockMvc;
	
    @Test
    public void testAutowired() throws Exception {
    	MockitoAnnotations.initMocks(this);
        m_mockMvc = MockMvcBuilders.standaloneSetup(m_controller).build();

        assertThat(m_controller).isNotNull();
        assertThat(m_mockMvc).isNotNull();
    }
    
    @Test
    public void getHelloFromController() throws Exception {
		m_mockMvc = MockMvcBuilders.standaloneSetup(m_controller).build();
		ResultActions result = m_mockMvc.perform(get(getHelloUrl()));
		result.andExpect(status().isOk());
		result.andExpect(content().string(HelloWorldControllerI.HELLO_RESULT));
    }
    
    protected String getHelloUrl() {
    	String strBase = "http://localhost:8080/";
    	return strBase + 
    			HelloWorldControllerI.URL_PATH +
    			HelloWorldControllerI.HELLO_URL_PATH;
    }
}