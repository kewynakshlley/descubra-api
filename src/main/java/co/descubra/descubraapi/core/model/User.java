package co.descubra.descubraapi.core.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.JoinColumn;

@Entity
public class User extends AbstractUser {

	private String name;
	private String lastName;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { 
			@JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> role;
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<ShowInterest> showInterest;
	

	public User() {
	}

	public User(String name, String lastName, Set<Role> roles, List<ShowInterest> showInterest) {
		this.name = name;
		this.lastName = lastName;
		this.role = roles;
		this.showInterest = showInterest;
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

	public List<ShowInterest> getShowInterest() {
		return showInterest;
	}

	public void setShowInterestList(List<ShowInterest> showInterest) {
		this.showInterest = showInterest;
	}
	
	public void setShowInterest(ShowInterest showInterest) {
		this.showInterest.add(showInterest);
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
