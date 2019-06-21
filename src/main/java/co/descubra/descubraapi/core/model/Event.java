package co.descubra.descubraapi.core.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the event that will be shown to mobile application users of the
 * Descubra.
 * 
 * @author Kewyn Akshlley
 *
 */
@Entity
@Table(name="EVENTS")
@JsonIgnoreProperties(value="showInterest")
public class Event {
	@Id
	@GeneratedValue
	@Column(name = "EVENT_ID")
	private long eventId;
	@Column(name = "ADMINISTRATOR_ID")
	private long administratorId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION", length=1000)
	private String description;
	@Column(name = "CATEGORY")
	private String category;
	// @Future(message = "The date must be in te future.")
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "START_HOUR")
	private String startHour;
	@Column(name = "END_HOUR")
	private String endHour;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "LOCATION")
	private String location;
	@Column(name = "IMAGE_LINK", length=1000)
	private String imageLink;
	@Column(name = "VIDEO_LINK", length=1000)
	private String videoLink;
	@Column(name = "CITY")
	private String city;
	@Column(name = "FREE_PAID")
	private String freePaid;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "event")
	private Administrator administrator;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            }, mappedBy = "inEvent")
	private Set<Administrator> administrators;
	@OneToMany(mappedBy = "event")
	private List<EventFeedback> eventFeedbacks;

	/**
	 * Default constructor.
	 */
	protected Event() {
	}

	/**
	 * Constructor
	 * 
	 * @param eventId         The id of this event.
	 * @param administratorId The id of the administrator that created this event.
	 * @param name            The name of this event.
	 * @param description     The description of this event.
	 * @param category        The category of this event.
	 * @param date            The date of this event.
	 * @param hour            The hour of this event.
	 * @param latitude        The latitude of this event.
	 * @param longitude       The longitude of this event.
	 */
	public Event(long administratorId, String name, String description, String category, Date startDate,
			Date endDate, String startHour, String endHour, String address, String location, String freePaid,
			String city, String imageLink, String videoLink, Set<Administrator> adminstrators, List<EventFeedback> eventFeedbacks) {
		this.administratorId = administratorId;

		this.name = name;
		this.description = description;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.endHour = endHour;
		this.address = address;
		this.location = location;
		this.freePaid = freePaid;
		this.city = city;
		this.imageLink = imageLink;
		this.videoLink = videoLink;
		this.administrators = adminstrators;
		this.eventFeedbacks = eventFeedbacks;
	}

	/**
	 * Returns the id of this event.
	 * 
	 * @return the id of this event.
	 */
	public long getEventId() {
		return eventId;
	}

	/**
	 * Changes the id of this event.
	 * 
	 * @param eventId The new id for this event.
	 */
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	/**
	 * Returns the id of the administrator that created this event.
	 * 
	 * @return the id of the administrator that created this event.
	 */
	public long getAdministratorId() {
		return administratorId;
	}

	/**
	 * Changes the id of the administrator that created this event.
	 * 
	 * @param administratorId The new id of the administrator that created this
	 *                        event.
	 */
	public void setAdministratorId(long administratorId) {
		this.administratorId = administratorId;
	}

	/**
	 * Returns the name of this event.
	 * 
	 * @return the name of this event.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of this event.
	 * 
	 * @param name The new name for this event.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of this event.
	 * 
	 * @return the description of this event.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Changes the description of this event.
	 * 
	 * @param description The new description for this event.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the category of this event.
	 * 
	 * @return the category of this event.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Changes the category of this event.
	 * 
	 * @param category The new category for this event.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Administrator> getShowInterest() {
		return administrators;
	}
	
	public void setShowInterest(Set<Administrator> adminstrators) {
		this.administrators = adminstrators;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFreePaid() {
		return freePaid;
	}

	public void setFreePaid(String freePaid) {
		this.freePaid = freePaid;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public List<EventFeedback> getEventFeedbacks() {
		return eventFeedbacks;
	}

	public void setEventFeedbacks(List<EventFeedback> eventFeedbacks) {
		this.eventFeedbacks = eventFeedbacks;
	}
	
	
	

}
