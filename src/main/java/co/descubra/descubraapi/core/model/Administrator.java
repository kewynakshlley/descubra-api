package co.descubra.descubraapi.core.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Represents the administrator of events.
 * 
 * @author Kewyn Akshlley
 *
 */

@JsonIgnoreProperties(value="showInterest")
@Entity
@Table(name = "ADMINISTRATORS")
public class Administrator {

	@Id
	@GeneratedValue
	@Column(name = "administrator_id")
	private long administratorId;
	@OneToMany(mappedBy = "administrator")
	@JsonManagedReference(value = "event")
	private List<Event> events;
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "INTEREST",
            joinColumns = { @JoinColumn(name = "ADMINISTRATOR_ID") },
            inverseJoinColumns = { @JoinColumn(name = "EVENT_ID") })
	private Set<Event> inEvent;

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


	/**
	 * Returns the id of this administrator.
	 * 
	 * @return the id of this administrator.
	 */
	public long getAdministratorId() {
		return administratorId;
	}

	

	public Administrator(long administratorId, List<Event> events, User user, Set<Event> inEvent) {
		this.administratorId = administratorId;
		this.events = events;
		this.user = user;
		this.inEvent = inEvent;
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

	public Set<Event> getShowInterest() {
		return this.inEvent;
	}

	public void setShowInterest(Set<Event> inEvents) {
		this.inEvent = inEvents;
	}
	
	
}
