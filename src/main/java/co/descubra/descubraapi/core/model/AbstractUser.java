package co.descubra.descubraapi.core.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import co.descubra.descubraapi.models.Role;

@MappedSuperclass
public abstract class AbstractUser {

	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	
	public abstract Set<Role> getRole();

	public abstract void setRole(Set<Role> role);
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
