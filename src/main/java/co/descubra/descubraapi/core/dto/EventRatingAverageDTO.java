package co.descubra.descubraapi.core.dto;

public class EventRatingAverageDTO {
	private double eventAverage;
	
	public EventRatingAverageDTO() {
	}

	public EventRatingAverageDTO(double eventAverage) {
		super();
		this.eventAverage = eventAverage;
	}

	public double getEventAverage() {
		return eventAverage;
	}

	public void setEventAverage(double eventAverage) {
		this.eventAverage = eventAverage;
	}
	
	
	
}
