package pet.store.spring.web.security.controllers.classes;

import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pet.store.spring.web.security.controllers.interfaces.SecurityTokenControllerI;
import pet.store.spring.web.security.services.interfaces.TokenByPasswordSecurityServiceI;

@RestController
@RequestMapping(SecurityTokenControllerI.URL_PATH)
@Api(value = "Security token provider", description = "Security token provider for token filter")

public 	class SecurityTokenControllerC implements SecurityTokenControllerI {
	
	protected TokenByPasswordSecurityServiceI m_tokenService;
	
	public SecurityTokenControllerC(TokenByPasswordSecurityServiceI tokenPasswordService) {
		m_tokenService = tokenPasswordService;
	}

	@ApiOperation(value = "Get a security token based on user name and password")
	@RequestMapping(method = RequestMethod.GET, value = SecurityTokenControllerI.TOKEN_BY_PASSWORD_URL_PATH, produces = "application/json")
	public Map<String, String> get(	@ApiParam(value = "User name", required = true) @PathVariable  String username, 
						@ApiParam(value = "User password", required = true) @PathVariable  String password) throws Exception {
		UsernamePasswordAuthenticationToken auth = 
				new UsernamePasswordAuthenticationToken (username, password, null);
		String token = m_tokenService.getToken(auth);
		return ImmutableMap.of("token", token) ;
	}
	
	@ApiOperation(value = "Get an empty token based on no input")
	@RequestMapping(method = RequestMethod.GET, value = SecurityTokenControllerI.TOKEN_BY_NOTHING_URL_PATH, produces = "application/json")
	public Map<String, String> get() throws Exception {;
		return ImmutableMap.of("token", "anonymous") ;
	}
}