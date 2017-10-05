package pet.store.spring.web.security.controllers.interfaces;

public interface SecurityTokenControllerI {
	
	final static String URL_PATH = "/tokens";
	final static String TOKEN_BY_PASSWORD_URL_PATH = "/password/{username}/{password}";
	final static String TOKEN_BY_NOTHING_URL_PATH = "/nothing";
	
}