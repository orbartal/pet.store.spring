package pet.store.spring.web.security.settings;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The open to all un secure version of the
 * Configuration class of the spring security: 
 * authentication and permission on files and resources.
 * 
 * @author Or Bartal
 */

/*
@EnableGlobalMethodSecurity
@EnableWebSecurity
@Configuration
*/
class WebUnSecureConfigC extends WebSecurityConfigurerAdapter {

    //Allow anonymous users to enter the entire site without login.
    protected void configure (HttpSecurity http) throws Exception {
    	//Allow request from client outside the server domain. XAuthTokenFilter is the only filter.
    	http.csrf().disable();
  	  	http.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
    }
}