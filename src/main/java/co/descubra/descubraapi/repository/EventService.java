package co.descubra.descubraapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.descubra.descubraapi.models.Event;

@Repository
public interface EventService extends JpaRepository<Event, Long>{
	@Query("SELECT *,\n" + 
			"			(6371 * acos(\n" + 
			"			 cos( radians(:latitude) )\n" + 
			"			 * cos( radians( latitude ) )\n" + 
			"			 * cos( radians( longitude ) - radians(:longitude) )\n" + 
			"			 + sin( radians(:latitude) )\n" + 
			"			 * sin( radians( latitude ) ) \n" + 
			"			 )\n" + 
			"			) AS distance\n" + 
			"			FROM `Events` \n" + 
			"			HAVING distance < :radius\n" + 
			"			ORDER BY distance ASC;\";")
	public List<Event> findByLatitudeAndLongitude(@Param("latitude") int latitude,
			@Param("longitude") int longitude,
			@Param("radius") double radius);

}
