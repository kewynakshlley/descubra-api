package co.descubra.descubraapi.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.descubra.descubraapi.core.model.Event;
import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.service.EventService;
 

 
@RestController
public class EventController {
	
	public static final Logger log =  LoggerFactory.getLogger(AdministratorController.class);
    @Autowired
    private EventService eventService;
     
    @GetMapping(path = "/events")
    public List<Event> getAllEvents() {
        return this.eventService.getAllEvents();         
         
    }
    
    
   @GetMapping(path = "/events/nearby_events")
    public List<Event> getEventsByRatio(
    		@PathParam("radius") double radius,
    		@PathParam("longitude") float longitude,
    		@PathParam("latitude") float latitude){
		return this.eventService.getEventsByRatio(radius, longitude, latitude);
    	
    }

     
    @GetMapping (path = "/events/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable long eventId) throws DataNotFoundException {
        return this.eventService.getEvent(eventId);
    }

     
    @PostMapping(path = "/events")
    public ResponseEntity<?> createEvent(@RequestBody Event event) throws DataAlreadyExistsException {
        return this.eventService.createEvent(event);
    }

     
    @DeleteMapping(path = "/events/{eventId}")
    public void deleteEvent(@PathVariable long eventId) {
    	this.eventService.deleteEvent(eventId);
    }
     
    @PutMapping(path = "/events/{eventId}")
    public @ResponseBody ResponseEntity<?> updateEvent(@PathVariable("eventId") long id, @RequestBody Event event) {
        return this.eventService.updateEvent(id, event);
    }


	
 
}