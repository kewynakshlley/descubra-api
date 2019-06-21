package co.descubra.descubraapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.descubra.descubraapi.core.model.AdministratorEventInterest;
import co.descubra.descubraapi.core.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	@Query("SELECT e FROM Event e WHERE e.city = :city AND e.category = :category")
	List<Event> findByNamedParams(String city, String category);
	/*@Query("SELECT e FROM Event e WHERE " + 
					"("
							+ "6371 * acos(" + 
								" cos(radians(:latitude))" + 
								" * cos(radians(e.latitude))" + 
								" * cos(radians(:longitude) - radians(e.longitude) )" + 
								" + sin(radians(:latitude))" + 
								" * sin(radians(e.latitude))" + 
							" )" + 
						" ) < :radius")
	public List<Event> findByNamedParams(
			@Param("radius") Double radius,
			@Param("longitude") Float longitude,
			@Param("latitude") Float latitude);
*/

	List<Event> findByCity(String city);
	@Query(value = "SELECT * FROM Events WHERE end_date < CURDATE()", nativeQuery = true)
	List<Event> findByDate(long administratorId);
	@Query(value = "select count(*) from events "
			+ "inner join administrators "
			+ "on events.administrator_id = administrators.administrator_id "
			+ "inner join interest on interest.event_id=events.event_id "
			+ "where events.end_date > curdate() and events.event_id = :event_id", nativeQuery = true)
	int countEventInterests(long event_id);
	@Query(value = "select avg(stars) from event_feedback "
			+ "where event_event_id=:eventId", nativeQuery=true)
	double eventRatingAverage(Long eventId);
	@Query(value="select avg(event_feedback.stars) from events "
			+ "inner join event_feedback "
			+ "on events.event_id=event_feedback.event_event_id "
			+ "where events.category = :category and events.end_date > curdate()", nativeQuery = true)
	double eventRatingAverageByCategory(String category);
	@Query(value = "select avg(event_feedback.stars) from administrators inner join events on administrators.administrator_id=events.administrator_id inner join event_feedback on event_feedback.event_event_id=events.event_id where administrators.administrator_id = :admId" 
		, nativeQuery = true)
	double eventRatingAverageByAdministrator(long admId);
	List<Event> findByCategory(String category);
	@Query(value = "SELECT new AdministratorEventInterest(event_d, adminstrator_id) FROM event_user_interest WHERE event_id = :id", nativeQuery = true)
	List<AdministratorEventInterest> findByShowInterest(Long id);
	@Query(value = "select * from events where start_date > curdate() or start_date = curdate()", nativeQuery = true)
	List<Event> futureEvents();
	@Query(value = "select * from events where end_date < curdate()", nativeQuery = true)
	List<Event> pastEvents();
	@Query(value = "select * from events where start_date < curdate() and end_date > curdate()", nativeQuery = true)
	List<Event> currentEvents();

	


}
