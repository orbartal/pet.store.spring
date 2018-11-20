package pet.store.spring.web.security.controllers.classes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(method = RequestMethod.POST, value = "", produces = "application/json")
	public String get(	@ApiParam(value = "User name", required = true) @RequestParam  String username, 
						@ApiParam(value = "User password", required = true) @RequestParam  String password) throws Exception {
		return m_tokenService.getToken(username, password);
	}
	
	@ApiOperation(value = "Get an empty token based on no input")
	@RequestMapping(method = RequestMethod.GET, value = SecurityTokenControllerI.TOKEN_BY_NOTHING_URL_PATH, produces = "application/json")
	public String get() throws Exception {;
		return "anonymous";
	}

	//////////////////////////////
	// Handle server exception //
	////////////////////////////
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=org.springframework.http.HttpStatus.BAD_REQUEST, reason="The given credentials are not valid for a token")  // 400
	public void handle(HttpServletRequest request, Exception e) {
		e.toString();
	}

}