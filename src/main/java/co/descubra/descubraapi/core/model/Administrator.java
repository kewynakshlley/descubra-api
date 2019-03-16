package co.descubra.descubraapi.core.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Represents the administrator of events.
 * 
 * @author Kewyn Akshlley
 *
 */

/* é possível usar o @JsonIgnoreProperties(values={"password", "cpf"}) */
@Entity
@Table(name = "Administrators")
public class Administrator {

	@Id
	@GeneratedValue
	private long administratorId;
	@OneToMany(mappedBy = "administrator")
	@JsonManagedReference
	private List<Event> events;
	@OneToOne
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
	public Administrator(User user) {
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
}
