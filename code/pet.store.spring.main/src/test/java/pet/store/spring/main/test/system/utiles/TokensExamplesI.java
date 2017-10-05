package pet.store.spring.main.test.system.utiles;

public interface TokensExamplesI {
	final static String INVALID  =  "blabla";
	final static String ANONYMOUS  =  null;
	final static String LIMITED =  "{\"role\":\"limited\",\"credentials\":\"pass\",\"name\":\"limited\"}";
	final static String ADMIN =  "{\"role\":\"admin\",\"credentials\":\"pass\",\"name\":\"admin\"}";
}
