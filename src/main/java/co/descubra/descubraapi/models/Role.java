package co.descubra.descubraapi.models;


import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Role {
	@Id
	@GeneratedValue
	private long id;
	private String codeName;
	private String description;
	@ManyToMany(mappedBy = "role")
	private List<User> user;
	
	public Role(int id, String codeName, String description, List<User> user) {
		super();
		this.id = id;
		this.codeName = codeName;
		this.description = description;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", codeName=" + codeName + ", description=" + description + ", user=" + user + "]";
	}
	
	
	
	
	
	
	

}
