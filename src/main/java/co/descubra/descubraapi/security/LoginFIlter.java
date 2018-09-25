package co.descubra.descubraapi.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginFIlter extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("passou pelo filtro");
		String uri = request.getRequestURI();
		// deixar apenas a url de login publica
		if (uri.endsWith("/login")) {
			System.out.println("entrou no if");
			return TokenAuthenticationService.AUTHORIZED;
		}
		return TokenAuthenticationService.validateJWT(request, response);
	}
	
}
