package pet.store.spring.web.security.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pet.store.spring.web.security.services.interfaces.TokenByPasswordSecurityServiceI;

@RestController
@RequestMapping("/tokens")
@Api(value = "Security token provider", description = "Security token provider for token filter")

public 	class SecurityTokenControllerC {
	
	protected TokenByPasswordSecurityServiceI m_tokenService;
	
	public SecurityTokenControllerC(TokenByPasswordSecurityServiceI tokenPasswordService) {
		m_tokenService = tokenPasswordService;
	}

	@ApiOperation(value = "Get a security token based on user name and password")
	@RequestMapping(method = RequestMethod.GET, value = "password/{username}/{password}") //, produces =  MediaType.TEXT_PLAIN_VALUE
	public String get(	@ApiParam(value = "User name", required = true) @PathVariable  String username, 
						@ApiParam(value = "User password", required = true) @PathVariable  String password) throws Exception {
		UsernamePasswordAuthenticationToken auth = 
				new UsernamePasswordAuthenticationToken (username, password, null);
		String token = m_tokenService.getToken(auth);
		return token;
	}
}