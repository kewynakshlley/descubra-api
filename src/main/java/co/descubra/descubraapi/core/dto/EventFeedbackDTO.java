package co.descubra.descubraapi.core.dto;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;



public class EventFeedbackDTO {
	private String comment;
	private int stars;
	@ManyToOne(fetch = FetchType.EAGER)
	private long eventId;
	
	public EventFeedbackDTO() {
	}
	
	public EventFeedbackDTO(String comment, int stars, long eventId) {
		super();
		this.comment = comment;
		this.stars = stars;
		this.eventId = eventId;
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

	public long getEventId() {
		return this.eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	
	
	
	
	

}
