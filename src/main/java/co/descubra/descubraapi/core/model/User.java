package co.descubra.descubraapi.core.model;


import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="user")
public class User extends AbstractUser {
	
	private String name;
	private String lastName;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { 
			@JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> role;
	public User() {
	}

	public User(String name, String lastName, Set<Role> roles) {
		this.name = name;
		this.lastName = lastName;
		this.role = roles;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	@Override
	public Set<Role> getRole() {
		return this.role;
	}

	@Override
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	

}
