package co.descubra.descubraapi.core.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "showInterest")
@IdClass(ShowInterestPK.class)
public class ShowInterest {
	@Id
	@ManyToOne
	@JoinColumn(name = "event_id", referencedColumnName = "eventId")
	@JsonManagedReference
	private Event event;
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonManagedReference
	private User user;
	private boolean isInterested;
	
	
	
	public ShowInterest() {
		super();
	}
	public ShowInterest(Event event, User user, boolean isInterested) {
		super();
		this.event = event;
		this.user = user;
		this.isInterested = isInterested;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isInterested() {
		return isInterested;
	}
	public void setInterested(boolean isInterested) {
		this.isInterested = isInterested;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + (isInterested ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ShowInterest other = (ShowInterest) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (isInterested != other.isInterested)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
	

}
