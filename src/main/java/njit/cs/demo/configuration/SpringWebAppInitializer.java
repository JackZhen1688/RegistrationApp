package njit.cs.demo.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import java.net.URI;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		if (System.getenv(APPConstants.APPCONF) != null) {
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			final String logConfig = String.format("file:///%s/SpringService/log4j2.xml", System.getenv(APPConstants.APPCONF));
			context.setConfigLocation(URI.create(logConfig));
			context.reconfigure();
		}
		servletContext.setInitParameter(APPConstants.SPRING_PROFILE_DEFAULT, APPConstants.JNDI);
		super.onStartup(servletContext);
	}	
}