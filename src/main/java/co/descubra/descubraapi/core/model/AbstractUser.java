package co.descubra.descubraapi.core.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractUser {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;

	public abstract Set<Role> getRole();

	public abstract void setRole(Set<Role> role);

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
