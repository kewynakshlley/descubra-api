package co.descubra.descubraapi.core.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EventFeedback {
	@Id
	@GeneratedValue
	private long id;
	private String comment;
	private int stars;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Event event;
	
	public EventFeedback() {
	}
	
	public EventFeedback(long id, String comment, int stars, Event event) {
		super();
		this.id = id;
		this.comment = comment;
		this.stars = stars;
		this.event = event;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	
	
	
	

}
