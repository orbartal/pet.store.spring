package pet.store.spring.web.swagger.controllers.interfaces;

public interface HelloWorldControllerI {
	final static String URL_PATH = "/hello";
	final static String HELLO_URL_PATH = "";
	final static String HELLO_RESULT = "Hello from server";
	
	public String hello() throws Exception;
}
