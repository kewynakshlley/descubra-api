package co.descubra.descubraapi.core.model;



public class AdministratorEventInterest {
	private long eventId;
	private long administratorId;
	
	
	public AdministratorEventInterest() {
	}
	
	public AdministratorEventInterest(long eventId, long administratorId) {
		super();
		this.administratorId = administratorId;
		this.eventId = eventId;
	}

	public long getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(long userId) {
		this.administratorId = userId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	
	
	

}
