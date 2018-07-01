package br.com.dambor.qrcash.userservice.config;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer {

	private static final String SPRING_PROFILE_DEVELOPMENT = "dev";

	private final Environment env;

	private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

	public WebConfigurer(Environment env) {
		this.env = env;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		if (env.getActiveProfiles().length != 0) {
			log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
		}
//		if (env.acceptsProfiles(SPRING_PROFILE_DEVELOPMENT)) {
//			initH2Console(servletContext);
//		}
		log.info("Web application fully configured");
	}

	// @Bean
	// public CorsFilter corsFilter() {
	// UrlBasedCorsConfigurationSource source = new
	// UrlBasedCorsConfigurationSource();
	// CorsConfiguration config = new CorsConfiguration();
	// if (config.getAllowedOrigins() != null &&
	// !config.getAllowedOrigins().isEmpty()) {
	// log.debug("Registering CORS filter");
	// source.registerCorsConfiguration("/api/**", config);
	// }
	// return new CorsFilter(source);
	// }

	/**
	 * Initializes H2 console.
	 */
//	private void initH2Console(ServletContext servletContext) {
//		log.debug("Initialize H2 console");
//		try {
//			// We don't want to include H2 when we are packaging for the "prod" profile and
//			// won't
//			// actually need it, so we have to load / invoke things at runtime through
//			// reflection.
//			ClassLoader loader = Thread.currentThread().getContextClassLoader();
//			Class<?> servletClass = Class.forName("org.h2.server.web.WebServlet", true, loader);
//			Servlet servlet = (Servlet) servletClass.newInstance();
//
//			ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", servlet);
//			h2ConsoleServlet.addMapping("/h2-console/*");
//			h2ConsoleServlet.setInitParameter("-properties", "src/main/resources/");
//			h2ConsoleServlet.setLoadOnStartup(1);
//
//		} catch (ClassNotFoundException | LinkageError e) {
//			throw new RuntimeException("Failed to load and initialize org.h2.server.web.WebServlet", e);
//
//		} catch (IllegalAccessException | InstantiationException e) {
//			throw new RuntimeException("Failed to instantiate org.h2.server.web.WebServlet", e);
//		}
//	}
}
