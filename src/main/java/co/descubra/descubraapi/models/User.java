package co.descubra.descubraapi.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;
    private String email;
    private String dateOfBirthday;
    @OneToOne(fetch=FetchType.LAZY,
    		cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Administrator administrator;
    @ManyToMany(mappedBy = "user")
    private List<Role> roles;

    protected User(){

    }

    public User(long id, String name, String password, String email, String dateOfBirthday,
    		Administrator administrator, List<Role> roles){
       this.id = id;
       this.name = name;
       this.password = password;
       this.email = email;
       this.dateOfBirthday = dateOfBirthday;
       this.administrator = administrator;
       this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    

    public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                '}';
    }
}

