package co.descubra.descubraapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.descubra.descubraapi.core.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
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


}