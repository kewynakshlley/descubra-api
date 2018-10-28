package co.descubra.descubraapi.core.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import co.descubra.descubraapi.core.security.AuthenticationFilter;
import co.descubra.descubraapi.core.security.LoginFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers("/administrators").permitAll()
				.antMatchers("/users").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(new LoginFilter("/login"), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	

}

