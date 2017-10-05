package pet.store.spring.web.swagger.controllers.classes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pet.store.spring.web.swagger.services.interfaces.SwaggerUrlServiceI;

@RestController
@RequestMapping("/")
@Api(value = "Default", description = "A welcome page with help info about the api")
public class DefualtControllerC {

	protected SwaggerUrlServiceI m_swaggerUrlService;
	
	public DefualtControllerC(SwaggerUrlServiceI swaggerUrlService) {
		m_swaggerUrlService = swaggerUrlService;
	}

	@ApiOperation(value = "Get site url")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public String get() throws Exception {
		return m_swaggerUrlService.get();
	}
}