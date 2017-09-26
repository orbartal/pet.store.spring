package pet.store.spring.web.swagger.services.classes;

import org.springframework.stereotype.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pet.store.spring.web.swagger.services.interfaces.SitePropertiesServiceI;
import pet.store.spring.web.swagger.services.interfaces.SwaggerUrlServiceI;

@Service("SwaggerUrlServiceC")
public class SwaggerUrlServiceC implements SwaggerUrlServiceI {

	SitePropertiesServiceI m_sitePropertiesService;
	
	public SwaggerUrlServiceC(SitePropertiesServiceI sitePropertiesService) {
		m_sitePropertiesService = sitePropertiesService;
	}
	
	@Override
	public String get() throws Exception {
		String strBaseUrl = m_sitePropertiesService.getBaseUrl();
		String strResult = get(strBaseUrl);
		return strResult;
	}

	@Override
	public String get(String siteUrl) throws Exception {
		JsonObject jsonSite = new JsonObject();
		jsonSite.addProperty("siteUrl", siteUrl);
		jsonSite.add("paths", getJsonSwagger(siteUrl));
		String jsonString = jsonSite.toString();
		return jsonString;
	}
	
	protected JsonElement getJsonSwagger(String siteUrl) {
		JsonObject jsonSwagger = new JsonObject();
		jsonSwagger.addProperty("api-docs", siteUrl+"/v2/api-docs");
		jsonSwagger.addProperty("swagger-ui", siteUrl+"/swagger-ui.html");
		return jsonSwagger;
	}
}
