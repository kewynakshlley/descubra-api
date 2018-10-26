package co.descubra.descubraapi.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
* Represents the administrator of events.
* 
* @author Kewyn Akshlley
*
*/

/*é possível usar o @JsonIgnoreProperties(values={"password", "cpf"})*/
@Entity
@Table(name = "Administrators")
public class Administrator {
    
   @Id
   @GeneratedValue
   private long administratorId;
   private String cpf;
   private String enterprise;
   private String contact;
   @OneToMany(mappedBy="administrator")
   private List<Event> events;
   @OneToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "user_id", nullable = false)
   private User user;

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
    * @param cpf             The cpf of this administrator.
    * @param enterprise      The enterprise of this administrator.
    * @param contact         The contact of this administrator.
    */
   public Administrator(String cpf,
           String enterprise, String contact, User user) {
       this.cpf = cpf;
       this.enterprise = enterprise;
       this.contact = contact;
       this.user = user;
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




   public User getUser() {
	   return user;
   }

   public void setUser(User user) {
	   this.user = user;
   }

   @Override
   public String toString() {
	return "Administrator [administratorId=" + administratorId + ", cpf=" + cpf + ", enterprise=" + enterprise
			+ ", contact=" + contact + ", events=" + events + "]";
   }

   @Override
   public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + (int) (administratorId ^ (administratorId >>> 32));
       result = prime * result + ((contact == null) ? 0 : contact.hashCode());
       result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
       result = prime * result + ((enterprise == null) ? 0 : enterprise.hashCode());
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
  
       if (enterprise == null) {
           if (other.enterprise != null)
               return false;
       } else if (!enterprise.equals(other.enterprise))
           return false;
       return true;
   }

}
