package pet.store.spring.web.swagger.services.classes;

import java.net.InetAddress;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.stereotype.Service;
import pet.store.spring.web.swagger.services.interfaces.SitePropertiesServiceI;

@Service("SitePropertiesServiceC")
public class SitePropertiesServiceC implements SitePropertiesServiceI {

	protected EmbeddedWebApplicationContext m_appContext;
	
	public SitePropertiesServiceC (EmbeddedWebApplicationContext appContext) {
		m_appContext = appContext;
	}
	
	//https://stackoverflow.com/questions/40401383/spring-boot-get-application-base-url-outside-of-servlet-context
	@Override
	public String getBaseUrl() throws Exception {
		EmbeddedServletContainer container = m_appContext.getEmbeddedServletContainer();
		TomcatEmbeddedServletContainer tomcatContainer = (TomcatEmbeddedServletContainer) container;
		Connector connector = tomcatContainer.getTomcat().getConnector();
	    String scheme = connector.getScheme();
	    String ip = InetAddress.getLocalHost().getHostAddress();
	    int port = connector.getPort();
	    String contextPath = m_appContext.getServletContext().getContextPath();
	    return scheme + "://" + ip + ":" + port + contextPath;
	}

}
