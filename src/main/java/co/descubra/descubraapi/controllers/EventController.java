package co.descubra.descubraapi.controllers;


import java.net.URI;
import java.util.List;
 
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.models.Event;
import co.descubra.descubraapi.repository.EventService;
 

 
@RestController
public class EventController {
 
    @Autowired
    private EventService eventService;
     
    @GetMapping(path = "/events")
    public List<Event> getAllEvents() {
        return eventService.findAll();
         
         
    }
     
    @GetMapping (path = "/events/{eventId}")
    public Event getEvent(@PathVariable long eventId) throws DataNotFoundException {
     
        return eventService.getOne(eventId);
    }
     
    @PostMapping(path = "/events")
    public ResponseEntity<Object> createEvent(@Valid @RequestBody Event event) throws DataAlreadyExistsException {
        Event createdEvent = eventService.save(event);
         
        /* Wil return the current request URI 
         * When we create a resource, the best status code to return is 201 Created.
         * */
         URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdEvent.getEventId())
        .toUri();
         
        return ResponseEntity.created(location).build();
    }
     
    @DeleteMapping(path = "/events/{eventId}")
    public void deleteEvent(@PathVariable long eventId) {
    	eventService.deleteById(eventId);
    }
     
    @PutMapping(path = "/events/{eventId}")
    public void updateEvent(@PathVariable("eventId") long id, Event event) {
        System.out.println(id + "TESTE ID");
        event.setEventId(id);
        deleteEvent(id);
        eventService.save(event);
    }
 
}