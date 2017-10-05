package pet.store.spring.web.swagger.controllers.classes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pet.store.spring.web.swagger.controllers.interfaces.HelloWorldControllerI;

@RestController
@RequestMapping(HelloWorldControllerI.URL_PATH)
@Api(value = "Hello world", description = "Rest API for hello world")
public 	class HelloWorldControllerC implements HelloWorldControllerI {
	
	@ApiOperation(value = "Get hello world string")
	@RequestMapping(method = RequestMethod.GET, value =  HelloWorldControllerI.HELLO_URL_PATH, produces = "application/json")
	public String hello() throws Exception {
		return HelloWorldControllerI.HELLO_RESULT;
	}
}