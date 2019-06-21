package co.descubra.descubraapi.core.dto;

public class EventRatingAVGByCategoryDTO {
	private double ratingAverage;
	
	public EventRatingAVGByCategoryDTO() {
	}

	public EventRatingAVGByCategoryDTO(double ratingAverage) {
		super();
		this.ratingAverage = ratingAverage;
	}

	public double getRatingAverage() {
		return ratingAverage;
	}

	public void setRatingAverage(double ratingAverage) {
		this.ratingAverage = ratingAverage;
	}
	
	
	

}
