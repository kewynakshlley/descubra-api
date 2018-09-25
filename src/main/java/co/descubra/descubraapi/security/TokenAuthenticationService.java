package co.descubra.descubraapi.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {
	
	private static final String SECRET_KEY = "PALAVRA_SECRETA";

    public static final String HEADER = "Authorization";
    
    protected static final boolean AUTHORIZED = true;
    
	protected static final boolean UNUNAUTHORIZED = false;
    
    public static String generateToken(String email) {
    	return Jwts.builder().setSubject(email)
    			.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public static boolean validateJWT(HttpServletRequest request, HttpServletResponse response) {
    	String token = request.getHeader(HEADER);
    	if(token != null) {
    		Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    		return AUTHORIZED;
    	}
    	response.setStatus(HttpStatus.UNAUTHORIZED.value());
    	return UNUNAUTHORIZED;
    }
}
