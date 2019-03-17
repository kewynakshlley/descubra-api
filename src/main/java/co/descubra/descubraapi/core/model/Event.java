package co.descubra.descubraapi.core.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Represents the event that will be shown to mobile application users of the
 * Descubra.
 * 
 * @author Kewyn Akshlley
 *
 */
@Entity
public class Event {
	@Id
	@GeneratedValue
	private long eventId;
	private long administratorId;
	private String name;
	@Column(length=1000)
	private String description;
	private String category;
	// @Future(message = "The date must be in te future.")
	private String startDate;
	private String endDate;
	private String startHour;
	private String endHour;
	private String addresss;
	private String location;
	private String imageLink;
	private String city;
	private String freePaid;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	private Administrator administrator;
	@OneToMany(mappedBy = "event")
	@JsonBackReference
	private List<ShowInterest> showInterest;

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
	public Event(long administratorId, String name, String description, String category, String startDate,
			String endDate, String startHour, String endHour, String addresss, String location, String freePaid,
			String city, String imageLink, List<ShowInterest> showInterest) {
		this.administratorId = administratorId;

		this.name = name;
		this.description = description;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.endHour = endHour;
		this.addresss = addresss;
		this.location = location;
		this.freePaid = freePaid;
		this.city = city;
		this.imageLink = imageLink;
		this.showInterest = showInterest;
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

	public List<ShowInterest> getShowInterest() {
		return showInterest;
	}

	public void setShowInterestList(List<ShowInterest> showInterest) {
		this.showInterest = showInterest;
	}
	public void setShowInterest(ShowInterest showInterest) {
		this.showInterest.add(showInterest);
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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

	public String getAddresss() {
		return addresss;
	}

	public void setAddresss(String addresss) {
		this.addresss = addresss;
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

}
