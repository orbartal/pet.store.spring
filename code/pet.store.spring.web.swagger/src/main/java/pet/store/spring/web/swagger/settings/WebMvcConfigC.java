package pet.store.spring.web.swagger.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfigC extends WebMvcConfigurerAdapter {
	
	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 	//Enable swagger-ui.html
	        registry.addResourceHandler("**/**").
	        addResourceLocations("classpath:/META-INF/resources/");
	        
	        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
	 }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	//Enable access to server from any client. Not secure!!!
    	registry.addMapping("/**");//.allowedOrigins("*") ;
    }
}
