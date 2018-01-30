package br.com.dambor.qrcash.userservice.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ServerResourceConfig extends ResourceServerConfigurerAdapter {

	@Inject
	private DataSource datasource; 

	/**
	 * LOGGER
	 */
	private static final Logger log = LoggerFactory.getLogger(ServerResourceConfig.class);

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.jdbcAuthentication()
//			.passwordEncoder(new PasswordEncoder() {
//				
//				
//				@Override
//				public boolean matches(CharSequence rawPassword, String encodedPassword) {
//					try {
//						return CryptUtil.encrypt(rawPassword.toString()).equals(encodedPassword);
//					} catch (Exception e) {
//						log.error(e.getMessage(), e);
//					}
//					return false;
//				}
//				
//				@Override
//				public String encode(CharSequence rawPassword) {
//					try {
//						return CryptUtil.encrypt(rawPassword.toString());
//					} catch (Exception e) {
//						log.error(e.getMessage(), e);
//					}
//					return "";
//				}
//			})
			.passwordEncoder(new BCryptPasswordEncoder())
			.dataSource(datasource)
			// Usuário deve ter um perfil que possui alguma permissão na aplicação
			.usersByUsernameQuery("SELECT email, password, inactive_date is null" + 
					" FROM userservice.USER" + 
					" WHERE email = ?")
			.authoritiesByUsernameQuery("SELECT email, profile" + 
					" FROM userservice.USER u JOIN userservice.USER_PROFILE p ON u.id = p.id_user" + 
					" WHERE u.email = ?");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//.antMatchers("/**").permitAll()
				//.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf().disable()
				.headers()
				.frameOptions().sameOrigin()
				.httpStrictTransportSecurity().disable();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}	
}