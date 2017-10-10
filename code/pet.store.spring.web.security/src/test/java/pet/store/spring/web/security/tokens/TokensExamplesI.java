package pet.store.spring.web.security.tokens;

public interface TokensExamplesI {
	final static String STRING_TOKEN_INVALID  =  "blabla";
	final static String STRING_TOKEN_ANONYMOUS  =  "";
	final static String STRING_TOKEN_LIMITED =  "{\"role\":\"limit\",\"credentials\":\"limit\",\"name\":\"limit\"}";
	final static String STRING_TOKEN_ADMIN =  "{\"role\":\"admin\",\"credentials\":\"admin\",\"name\":\"admin\"}";
	//final static String JSON_TOKEN_LIMITED =  "{\"role\":\"limited\",\"credentials\":\"pass\",\"name\":\"limited\"}";
	//final static String JSON_TOKEN_ADMIN =  "{\"role\":\"admin\",\"credentials\":\"pass\",\"name\":\"admin\"}";
}
