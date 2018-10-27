package co.descubra.descubraapi.core.security;

import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import co.descubra.descubraapi.core.config.AppContext;
import co.descubra.descubraapi.core.dto.LoginDTO;
import co.descubra.descubraapi.core.model.AbstractUser;
import co.descubra.descubraapi.repository.UserService;

public class JwtAuthenticationManager implements AuthenticationManager {

	private UserService userService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		AbstractUser user = this.getUserService().findByUsernameAndPassword(auth.getName(), (String) auth.getCredentials());
		if(user != null) {
			return new UsernamePasswordAuthenticationToken(toDTO(user), auth.getCredentials());
		}
		throw new BadCredentialsException("Usuário e/ou senha inválidos.");
	}

	
	private LoginDTO toDTO(AbstractUser user) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUsername(user.getUsername());
		loginDTO.setRoles(user.getRole().stream().map(item -> "ROLE_" + item.getCodeName()).collect(Collectors.toList()));
		return loginDTO;
	}
	
	protected UserService getUserService() {

        if (this.userService == null) {
            this.userService = AppContext.getBean(UserService.class);
        }

        return this.userService;
    }

}
