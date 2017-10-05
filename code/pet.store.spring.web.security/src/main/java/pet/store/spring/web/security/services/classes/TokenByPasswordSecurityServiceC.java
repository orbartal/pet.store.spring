package pet.store.spring.web.security.services.classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import pet.store.spring.web.security.services.interfaces.TokenByPasswordSecurityServiceI;

@Service("TokenByPasswordSecurityServiceC")
public class TokenByPasswordSecurityServiceC implements TokenByPasswordSecurityServiceI {

	@Override
	public Authentication getAuth(String strToken) throws Exception {
		Map<String, String> map = stringToMap(strToken); 
		Authentication auth = mapToAuth(map);
		return auth;
	}
	
	protected Map<String, String> stringToMap(String strToken) {
		Gson gson = new GsonBuilder().create();
		java.lang.reflect.Type typeOfHashMap = 
				new TypeToken<Map<String, String>>() { }.getType();
		Map<String, String> map = gson.fromJson(strToken, typeOfHashMap);
		return map;
	}
	
	protected Authentication mapToAuth(Map<String, String> map) {
		String name = map.get("name");
		String role = map.get("role");
		String credentials = map.get("credentials");
		List<GrantedAuthority> lstAuth = Arrays.asList(new SimpleGrantedAuthority(role));
		Authentication auth = new UsernamePasswordAuthenticationToken (name, credentials, lstAuth);
		return auth;
	}

	@Override
	public String getToken(UsernamePasswordAuthenticationToken auth) throws Exception {
		validate (auth);
		Map<String, String> mapUserData = authToMap (auth); 
		Gson gson = new GsonBuilder().create();
		String strJson = gson.toJson(mapUserData);
		return strJson;
	}

	private void validate(UsernamePasswordAuthenticationToken auth) throws Exception {
		String strPassword = (String) auth.getCredentials();
		String strUserName = auth.getName();
		boolean isAdmin = strUserName.equalsIgnoreCase("admin") && strPassword.equalsIgnoreCase("admin");
		boolean isLimit = strUserName.equalsIgnoreCase("limit") && strPassword.equalsIgnoreCase("limit");
		if (!isAdmin && !isLimit) {
			throw new Exception ();
		}
	}

	protected Map<String, String> authToMap(UsernamePasswordAuthenticationToken auth) {
		Map<String, String> mapUserData =  new HashMap<>();
		mapUserData.put("credentials", (String)auth.getCredentials());
		mapUserData.put("name", auth.getName());
		if ("admin".equalsIgnoreCase(auth.getName())) {
			mapUserData.put("role", "admin"); 
		}else {
			mapUserData.put("role", "limited");
		}
		return mapUserData;
	}
}
