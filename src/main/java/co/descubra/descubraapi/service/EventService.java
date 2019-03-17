package co.descubra.descubraapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import co.descubra.descubraapi.core.model.Event;
import co.descubra.descubraapi.core.model.ShowInterest;
import co.descubra.descubraapi.core.model.User;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.repository.EventRepository;
import co.descubra.descubraapi.repository.UserRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;

	public List<Event> getAllEvents() {
		return eventRepository.findAll();

	}
	
	
	/*public List<Event> getEventsByRatio(double radius, float longitude, float latitude) {
		return eventRepository.findByNamedParams(radius, longitude, latitude);
	}*/
	
	
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


	public List<Event> getEventsFiltered(String city, String category) {
		// TODO Auto-generated method stub
		return eventRepository.findByNamedParams(city, category);
	}


	public List<Event> getEventsFilteredByCity(String city) {
		// TODO Auto-generated method stub
		return eventRepository.findByCity(city);
	}


	public List<Event> getEventsFilteredByCategory(String category) {
		// TODO Auto-generated method stub
		return eventRepository.findByCategory(category);
	}


	public ResponseEntity<?> showInterstEvent(ShowInterest showInterest) {
		User user = userRepository.findById(showInterest.getUser().getId()).get();
		if (user == null) 
			throw new DataNotFoundException("O administrador não existe!");
		
		Event event = eventRepository.findById(showInterest.getEvent().getEventId()).get();
		if (event == null)
			throw new DataNotFoundException("o evento não existe.");
		user.setShowInterest(showInterest);
		event.setShowInterest(showInterest);
		userRepository.save(user);
		eventRepository.save(event);
		return new ResponseEntity<ShowInterest>(showInterest, HttpStatus.CREATED);
	}

}