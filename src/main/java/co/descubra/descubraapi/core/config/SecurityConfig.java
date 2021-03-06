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
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers("/administrators").permitAll()
				.antMatchers("/users").permitAll()
				.antMatchers(HttpMethod.GET, "/events").permitAll()
				.antMatchers("/recovery").permitAll()
				.antMatchers(HttpMethod.GET, "/events/filtering_both").permitAll()
				.antMatchers(HttpMethod.GET, "/events/filtering_city").permitAll()
				.antMatchers(HttpMethod.GET, "/events/filtering_category").permitAll()
				.antMatchers(HttpMethod.GET, "/events/future_events").permitAll()
				.antMatchers(HttpMethod.GET, "/events/past_events").permitAll()
				.antMatchers(HttpMethod.GET, "/events/{eventId}/interests_number").permitAll()
				
				.antMatchers(HttpMethod.GET, "/events/current_events").permitAll()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.anyRequest().authenticated()
				.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterBefore(new LoginFilter("/login"), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	
	

}

