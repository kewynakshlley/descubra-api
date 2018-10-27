package co.descubra.descubraapi.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.models.Event;
import co.descubra.descubraapi.repository.EventService;
 

 
@RestController
public class EventController {
	
	public static final Logger log =  LoggerFactory.getLogger(AdministratorController.class);
    @Autowired
    private EventService eventService;
     
    @GetMapping(path = "/events")
    public List<Event> getAllEvents() {

        return eventService.findAll();
         
         
    }
    
   /* @GetMapping(path = "/events/ratio={ratio}/longitude={longitude}/latitude={latitude}/")
    public List<Event> getEventsByRatio(
    		@PathVariable int ratio,
    		@PathVariable float longitude,
    		@PathVariable float latitude){
		return eventService.findByLatitudeAndLongitude(latitude, longitude, ratio);
    	
    }*/
     
    @GetMapping (path = "/events/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable long eventId) throws DataNotFoundException {
        Event event = eventService.findById(eventId).get();
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }
     
    @PostMapping(path = "/events")
    public ResponseEntity<?> createEvent(@RequestBody Event event) throws DataAlreadyExistsException {
        Event createdEvent = eventService.save(event);
        return new ResponseEntity<Event>(createdEvent, HttpStatus.CREATED);
    }
     
    @DeleteMapping(path = "/events/{eventId}")
    public void deleteEvent(@PathVariable long eventId) {
    	eventService.deleteById(eventId);
    }
     
    @PutMapping(path = "/events/{eventId}")
    public @ResponseBody ResponseEntity<?> updateEvent(@PathVariable("eventId") long id, @RequestBody Event event) {
        event.setEventId(id);
        event = eventService.save(event);
        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }
 
}