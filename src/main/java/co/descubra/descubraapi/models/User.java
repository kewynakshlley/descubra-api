package co.descubra.descubraapi.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import co.descubra.descubraapi.core.model.AbstractUser;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User extends AbstractUser {
	
	@Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
    private String dateOfBirthday;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
		joinColumns = {@JoinColumn(name = "user_id")},
		inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> role;

    public User() { }

    public User(long id, String name, String email, String dateOfBirthday, Set<Role> roles){
       this.id = id;
       this.name = name;
       this.email = email;
       this.dateOfBirthday = dateOfBirthday;
       this.role = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                '}';
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

