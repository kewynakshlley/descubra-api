package co.descubra.descubraapi.core.model;

import java.io.Serializable;

public class ShowInterestPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long user;
	private long  event;
	
	public long getUser() {
		return user;
	}
	public void setUser(long user) {
		this.user = user;
	}
	public long getEvent() {
		return event;
	}
	public void setEvent(long event) {
		this.event = event;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (event ^ (event >>> 32));
		result = prime * result + (int) (user ^ (user >>> 32));
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
		ShowInterestPK other = (ShowInterestPK) obj;
		if (event != other.event)
			return false;
		if (user != other.user)
			return false;
		return true;
	}
	
	
	
}
