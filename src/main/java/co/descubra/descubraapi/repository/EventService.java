package co.descubra.descubraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.descubra.descubraapi.models.Event;

@Repository
public interface EventService extends JpaRepository<Event, Long>{

}