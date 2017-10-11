package pet.store.spring.web.security.test.unit.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pet.store.spring.web.security.controllers.classes.SecurityTokenControllerC;
import pet.store.spring.web.security.controllers.interfaces.SecurityTokenControllerI;
import pet.store.spring.web.security.services.interfaces.TokenByPasswordSecurityServiceI;
import pet.store.spring.web.security.tokens.TokensExamplesI;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={
		SecurityTokenControllerC.class, 
		TokenByPasswordSecurityServiceI.class})
public class TokenByPasswordControllerUnitTestC {
	
	@Mock
	TokenByPasswordSecurityServiceI m_tokensService;

	@InjectMocks
	protected SecurityTokenControllerC m_controller;
	
	protected MockMvc m_mockMvc;
	
  
	@Before
    public void setup() {
        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under test.
		MockitoAnnotations.initMocks(this);
        m_mockMvc = MockMvcBuilders.standaloneSetup(m_controller).build();
        
        assertThat(m_tokensService).isNotNull();
        assertThat(m_controller).isNotNull();
        assertThat(m_mockMvc).isNotNull();
    }

	@Test
    public void getTokenForAdmin() throws Exception {
		String username = "admin";
		String password = "admin";
		when(m_tokensService.getToken(username, password)).thenReturn(TokensExamplesI.STRING_TOKEN_ADMIN);
		ResultActions result = getTokenByPassword(username, password);
		result.andExpect(status().isOk());
		result.andExpect(content().string(TokensExamplesI.STRING_TOKEN_ADMIN));
    }
	
	@Test
    public void getTokenForLimit() throws Exception {
		String username = "limit";
		String password = "pass";
		when(m_tokensService.getToken(username, password)).thenReturn(TokensExamplesI.STRING_TOKEN_LIMITED);
		ResultActions result = getTokenByPassword(username, password);
		result.andExpect(status().isOk());
		result.andExpect(content().string(TokensExamplesI.STRING_TOKEN_LIMITED));
    }
	
	@Test
    public void getTokenForInvalid() throws Exception {
		String username = "invalid";
		String password = "pass";
		when(m_tokensService.getToken(username, password)).thenThrow(new Exception(""));
		ResultActions result = getTokenByPassword(username, password);
		result.andExpect(status().isBadRequest());
    }

	protected ResultActions getTokenByPassword (String name, String password) throws Exception {
		String strUrl  = getTokenByPasswordUrl(name, password);
		return m_mockMvc.perform(get(strUrl));
	}
	
    protected String getTokenByPasswordUrl(String strUserName, String strUserPassword) {
    	String methodPath = SecurityTokenControllerI.TOKEN_BY_PASSWORD_URL_PATH;
    	methodPath = methodPath.replace("{username}", strUserName);
    	methodPath = methodPath.replace("{password}", strUserPassword);
    	return SecurityTokenControllerI.URL_PATH +methodPath;
    }
}