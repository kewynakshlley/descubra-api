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
import co.descubra.descubraapi.repository.AdministratorRepository;
import co.descubra.descubraapi.repository.UserRepository;

public class JwtAuthenticationManager implements AuthenticationManager {

	private UserRepository userService;
	private AdministratorRepository administratorRepository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		AbstractUser user = this.getUserRepository().findByEmailAndPassword(auth.getName(), (String) auth.getCredentials());
		if(user != null) {
			int idad = this.getAdministratorRepository().findByUser(user.getId());
			return new UsernamePasswordAuthenticationToken(toDTO(user, idad), auth.getCredentials());
		}
		
		throw new BadCredentialsException("Usuário e/ou senha inválidos.");
	}

	
	private LoginDTO toDTO(AbstractUser user, int idad) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(user.getEmail());
		loginDTO.setId(user.getId());
		loginDTO.setAdministrator_id(idad);
		loginDTO.setRoles(user.getRole().stream().map(item -> "ROLE_" + item.getCodeName()).collect(Collectors.toList()));
		return loginDTO;
	}
	
	protected UserRepository getUserRepository() {

        if (this.userService == null) {
            this.userService = AppContext.getBean(UserRepository.class);
        }

        return this.userService;
    }
	
	protected AdministratorRepository getAdministratorRepository() {

        if (this.administratorRepository == null) {
            this.administratorRepository = AppContext.getBean(AdministratorRepository.class);
        }

        return this.administratorRepository;
    }

}
