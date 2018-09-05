package co.descubra.descubraapi.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* Represents the administrator of events.
* 
* @author Kewyn Akshlley
*
*/

/*é possível usar o @JsonIgnoreProperties(values={"password", "cpf"})*/
@Entity
public class Administrator {
    
   @Id
   @GeneratedValue
   private long administratorId;
   @Column
   private String name;
   @JsonIgnore
   @Column
   private String password;
   @Column
   private String email;
   @Column
   private String cpf;
   @Column
   private String enterprise;
   @Column
   private String contact;
   @OneToMany(mappedBy="administrator")
   private List<Event> events;

   public static final long DEFAULT_ADM_ID = -1;

   /**
    * Default constructor.
    */
   protected Administrator() {
        
   }

   /**
    * Constructor.
    * 
    * @param administratorId The id of this administrator.
    * @param name            The name of this administrator.
    * @param password        The password of this administrator.
    * @param email           The email of this administrator.
    * @param cpf             The cpf of this administrator.
    * @param enterprise      The enterprise of this administrator.
    * @param contact         The contact of this administrator.
    */
   public Administrator(String name, String password, String email, String cpf,
           String enterprise, String contact) {
       this.name = name;
       this.password = password;
       this.email = email;
       this.cpf = cpf;
       this.enterprise = enterprise;
       this.contact = contact;
   }

   /**
    * Returns the id of this administrator.
    * 
    * @return the id of this administrator.
    */
   public long getAdministratorId() {
       return administratorId;
   }

   /**
    * Changes the id of this administrator.
    * 
    * @param administratorId The new id for this administrator.
    */
   public void setAdministratorId(long administratorId) {
       this.administratorId = administratorId;
   }

   /**
    * Returns the name of this administrator.
    * 
    * @return the name of this administrator.
    */
   public String getName() {
       return name;
   }

   /**
    * Changes the name of this administrator.
    * 
    * @param name The new name for this administrator.
    */
   public void setName(String name) {
       this.name = name;
   }

   /**
    * Returns the password of this administrator.
    * 
    * @return the password of this administrator.
    */
   public String getPassword() {
       return password;
   }

   /**
    * Changes the password of this administrator.
    * 
    * @param password The new password for this administrator.
    */
   public void setPassword(String password) {
       this.password = password;
   }

   /**
    * Returns the email of this administrator.
    * 
    * @return the email of this administrator.
    */
   public String getEmail() {
       return email;
   }

   /**
    * Changes the email of this administrator.
    * 
    * @param email The new email for this administrator.
    */
   public void setEmail(String email) {
       this.email = email;
   }

   /**
    * Returns the cpf of this administrator.
    * 
    * @return the cpf of this administrator.
    */
   public String getCpf() {
       return cpf;
   }

   /**
    * Changes the cpf of this administrator.
    * 
    * @param cpf The new cpf for this administrator.
    */
   public void setCpf(String cpf) {
       this.cpf = cpf;
   }

   /**
    * Returns the enterprise of this administrator.
    * 
    * @return the enterprise of this administrator.
    */
   public String getEnterprise() {
       return enterprise;
   }

   /**
    * Changes the enterprise of this administrator.
    * 
    * @param enterprise The new enterprise for this administrator.
    */
   public void setEnterprise(String enterprise) {
       this.enterprise = enterprise;
   }

   /**
    * Returns the contact of this administrator.
    * 
    * @return the contact of this administrator.
    */
   public String getContact() {
       return contact;
   }

   /**
    * Changes the contact of this administrator.
    * 
    * @param contact The new contact for this administrator.
    */
   public void setContact(String contact) {
       this.contact = contact;
   }
   
   

   public List<Event> getEvents() {
	return events;
   }

   public void setEvents(List<Event> events) {
	this.events = events;
   }

   @Override
   public String toString() {
       return "Administrator [administratorId=" + administratorId + ", name=" + name + ", password=" + password
               + ", email=" + email + ", cpf=" + cpf + ", enterprise=" + enterprise + ", contact=" + contact + "]";
   }

   @Override
   public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + (int) (administratorId ^ (administratorId >>> 32));
       result = prime * result + ((contact == null) ? 0 : contact.hashCode());
       result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
       result = prime * result + ((email == null) ? 0 : email.hashCode());
       result = prime * result + ((enterprise == null) ? 0 : enterprise.hashCode());
       result = prime * result + ((name == null) ? 0 : name.hashCode());
       result = prime * result + ((password == null) ? 0 : password.hashCode());
       return result;
   }

   @Override
   public boolean equals(Object obj) {
       if (this == obj)
           return true;
       if (obj == null)
           return false;
       if (getClass() != obj.getClass())
           return false;
       Administrator other = (Administrator) obj;
       if (administratorId != other.administratorId)
           return false;
       if (contact == null) {
           if (other.contact != null)
               return false;
       } else if (!contact.equals(other.contact))
           return false;
       if (cpf == null) {
           if (other.cpf != null)
               return false;
       } else if (!cpf.equals(other.cpf))
           return false;
       if (email == null) {
           if (other.email != null)
               return false;
       } else if (!email.equals(other.email))
           return false;
       if (enterprise == null) {
           if (other.enterprise != null)
               return false;
       } else if (!enterprise.equals(other.enterprise))
           return false;
       if (name == null) {
           if (other.name != null)
               return false;
       } else if (!name.equals(other.name))
           return false;
       if (password == null) {
           if (other.password != null)
               return false;
       } else if (!password.equals(other.password))
           return false;
       return true;
   }

}
