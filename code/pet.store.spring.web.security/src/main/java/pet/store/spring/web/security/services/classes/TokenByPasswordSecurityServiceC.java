package pet.store.spring.web.security.services.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
	
	protected Authentication mapToAuth(Map<String, String> map) {
		GrantedAuthority roleAdmin = new SimpleGrantedAuthority("admin");
		List<GrantedAuthority> lstAuth = new ArrayList<>();
	    lstAuth.add(roleAdmin);
		Authentication auth = new UsernamePasswordAuthenticationToken ("admin", "pass", lstAuth);
		return auth;
	}

	protected Map<String, String> stringToMap(String strToken) {
		Gson gson = new GsonBuilder().create();
		java.lang.reflect.Type typeOfHashMap = 
				new TypeToken<Map<String, String>>() { }.getType();
		Map<String, String> map = gson.fromJson(strToken, typeOfHashMap);
		return map;
	}

	@Override
	public String getToken(UsernamePasswordAuthenticationToken auth) throws Exception {
		Map<String, String> mapUserData = new HashMap<>();
		mapUserData.put("name", auth.getName());
		mapUserData.put("role", "admin"); //TODO: validate password and check server
		Gson gson = new GsonBuilder().create();
		String strJson = gson.toJson(mapUserData);
		return strJson;
	}
}
