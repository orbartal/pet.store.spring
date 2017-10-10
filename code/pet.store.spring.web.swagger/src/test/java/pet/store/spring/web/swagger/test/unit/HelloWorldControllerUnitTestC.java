package pet.store.spring.web.swagger.test.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import pet.store.spring.web.swagger.controllers.classes.HelloWorldControllerC;
import pet.store.spring.web.swagger.controllers.interfaces.HelloWorldControllerI;

@SpringBootTest(classes={HelloWorldControllerC.class})
public class HelloWorldControllerUnitTestC extends AbstractWebUnitTestC{
	
	@InjectMocks
	protected HelloWorldControllerC m_controller;
	
    @Before
    public void testAutowired() throws Exception {
    	init (HelloWorldControllerI.URL_PATH +HelloWorldControllerI.HELLO_URL_PATH, m_controller);
    }
    
    @Test
    public void getHelloFromController() throws Exception {
		ResultActions result = m_mockMvc.perform(get(m_baseUrl));
		result.andExpect(status().isOk());
		result.andExpect(content().string(HelloWorldControllerI.HELLO_RESULT));
    }
}