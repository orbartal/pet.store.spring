package pet.store.spring.web.pet.controllers;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.stereotype.Component;

@Component
public class JsonSpringUtilityC {
	
	 @SuppressWarnings("rawtypes")
	 private HttpMessageConverter m_httpMessageConverter;
	 
	 public JsonSpringUtilityC () {
		m_httpMessageConverter = new MappingJackson2HttpMessageConverter();
	 }

	@SuppressWarnings("unchecked")
	public String toJson(Object o) throws IOException {
    	MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
    	m_httpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
	  }
}
