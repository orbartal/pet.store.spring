package pet.store.spring.web.swagger.services.classes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import pet.store.spring.web.swagger.services.interfaces.SitePropertiesServiceI;

@Service("SitePropertiesServiceC")
public class SitePropertiesServiceC implements SitePropertiesServiceI {

	protected EmbeddedWebApplicationContext  m_ewaContext;
	protected ConfigurableApplicationContext m_caContext;
	
	public SitePropertiesServiceC (EmbeddedWebApplicationContext ewaContext) {
		m_ewaContext = ewaContext;
	}
	
	@Autowired(required = false)
	public void setConfigurableApplicationContext(ConfigurableApplicationContext caContext) {
		m_caContext =  caContext;
	}

	//https://stackoverflow.com/questions/40401383/spring-boot-get-application-base-url-outside-of-servlet-context
	@Override
	public String getBaseUrl() throws Exception {
		String scheme = getScheme ();
		String ip = getIp ();
	    int port = getPort(); 
	    String contextPath = m_ewaContext.getServletContext().getContextPath();
	    return scheme + "://" + ip + ":" + port + contextPath;
	}
	
	public String getScheme () {
		EmbeddedServletContainer container = m_ewaContext.getEmbeddedServletContainer();
		TomcatEmbeddedServletContainer tomcatContainer = (TomcatEmbeddedServletContainer) container;
		Connector connector = tomcatContainer.getTomcat().getConnector();
	    return connector.getScheme();
	}
	
	public String getIp () throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	private int getPort() throws Exception {
		try {
			return getPortByEwa();
		}catch (Exception e) {
			return getPortByCa();
		}
	}
	
	private int getPortByCa() throws Exception {
		EmbeddedServletContainer container = m_ewaContext.getEmbeddedServletContainer();
		TomcatEmbeddedServletContainer tomcatContainer = (TomcatEmbeddedServletContainer) container;
		Connector connector = tomcatContainer.getTomcat().getConnector();
	    int port = connector.getPort();
	    if (port<=0) {
	    	throw new Exception ("invalid port: " + port);
	    }
	    return port;
	}

	private int getPortByEwa() throws Exception {
		AnnotationConfigEmbeddedWebApplicationContext acewaContext  =
				(AnnotationConfigEmbeddedWebApplicationContext) m_caContext;
		TomcatEmbeddedServletContainer tmsContainer = (TomcatEmbeddedServletContainer) 
				acewaContext.getEmbeddedServletContainer();
		int port = tmsContainer.getPort();
		if (port<=0) {
	    	throw new Exception ("invalid port: " + port);
	    }
		return port;
	}
/*
	public static String getIpAddressAndPort1() throws Exception {
		MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
		        Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
		String host = InetAddress.getLocalHost().getHostAddress();
		String port = objectNames.iterator().next().getKeyProperty("port");
		String ipadd = "http" + "://" + host + ":" + port;
		return ipadd;
	}
*/
}
