package co.descubra.descubraapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import co.descubra.descubraapi.core.model.Event;
import co.descubra.descubraapi.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<Event> getAllEvents() {
		return eventRepository.findAll();

	}
	
	
	public List<Event> getEventsByRatio(double radius, float longitude, float latitude) {
		return eventRepository.findByNamedParams(radius, longitude, latitude);
	}
	
	
	public ResponseEntity<?> getEvent(long eventId) {
		Event event = eventRepository.findById(eventId).get();
        return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> createEvent(Event event) {
		
		Event createdEvent = eventRepository.save(event);
        return new ResponseEntity<Event>(createdEvent, HttpStatus.CREATED);
	}
	
	
	public void deleteEvent(@PathVariable long eventId) {
    	eventRepository.deleteById(eventId);
    }
	
	
	public ResponseEntity<?> updateEvent(long id, Event event) {
		event.setEventId(id);
		
        event = eventRepository.save(event);
        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
	}

}