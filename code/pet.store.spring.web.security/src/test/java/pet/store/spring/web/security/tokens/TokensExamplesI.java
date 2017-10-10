package pet.store.spring.web.security.tokens;

public interface TokensExamplesI {
	final static String INVALID  =  "blabla";
	final static String ANONYMOUS  =  null;
	final static String LIMITED =  "{\"role\":\"limited\",\"credentials\":\"pass\",\"name\":\"limited\"}";
	final static String ADMIN =  "{\"role\":\"admin\",\"credentials\":\"pass\",\"name\":\"admin\"}";
	/*
	final static String INVALID  =  "blabla";
	final static String ANONYMOUS  =  null;
	final static String LIMITED =  "{token:\"{\"role\":\"limited\",\"credentials\":\"pass\",\"name\":\"limited\"}\"}";
	final static String ADMIN = "{token:\"{\"role\":\"admin\",\"credentials\":\"admin\",\"name\":\"admin\"}\"}";
	*/
}
