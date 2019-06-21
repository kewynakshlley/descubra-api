package co.descubra.descubraapi.controllers;

import java.util.List;
import java.util.Set;

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
import co.descubra.descubraapi.core.dto.EventFeedbackDTO;
import co.descubra.descubraapi.core.dto.EventRatingAVGByAdministratorDTO;
import co.descubra.descubraapi.core.dto.EventRatingAVGByCategoryDTO;
import co.descubra.descubraapi.core.dto.EventRatingAverageDTO;
import co.descubra.descubraapi.core.dto.InterestCountDTO;
import co.descubra.descubraapi.core.model.Administrator;
import co.descubra.descubraapi.core.model.AdministratorEventInterest;
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
    
    @GetMapping(path = "/events/filtering_city")
    public List<Event> getEventsFilteredByCity(
    		@PathParam("city") String city){
		return this.eventService.getEventsFilteredByCity(city);
    	
    }
    
    @GetMapping(path = "/events/filtering_both")
    public List<Event> getEventsFilteredByBoth(
    		@PathParam("city") String city,
    		@PathParam("category") String category){
		return this.eventService.getEventsFiltered(city, category);
    	
    }
    
    @GetMapping(path = "/events/filtering_category")
    public List<Event> getEventsFilteredByCategory(
    		@PathParam("category") String category){
		return this.eventService.getEventsFilteredByCategory(category);
    	
    }
   /*@GetMapping(path = "/events/nearby_events")
    public List<Event> getEventsByRatio(
    		@PathParam("radius") double radius,
    		@PathParam("longitude") float longitude,
    		@PathParam("latitude") float latitude){
		return this.eventService.getEventsByRatio(radius, longitude, latitude);
    	
    }*/

     
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
    
    @PostMapping(path = "/events/showInterest")
    public @ResponseBody ResponseEntity<?> showInterest(@RequestBody AdministratorEventInterest event) throws Exception {
        return this.eventService.showInterest(event);
    }
    
    @DeleteMapping(path = "/events/showInterest/cancel")
    public @ResponseBody ResponseEntity<?> showInterestCancel(@RequestBody AdministratorEventInterest event) {
        return this.eventService.showInterestCancel(event);
    }
    
    @GetMapping(path = "/events/interested_list/{eventId}")
    public Set<Administrator> getUsersnterested(@PathVariable("eventId") long id){
		return this.eventService.getInterestedList(id);
    	
    }
    
    @GetMapping(path = "/events/{eventId}/interests_number")
    public InterestCountDTO getInterestsNumber(@PathVariable("eventId") long id){
		return this.eventService.getInterestsNumber(id);
    	
    }
    
    @GetMapping(path = "/events/{eventId}/rating_average")
    public EventRatingAverageDTO getEventAverageRating(@PathVariable("eventId") long id){
		return this.eventService.getEventAverageRating(id);
    	
    }
    @GetMapping(path = "/events/{category}/rating_average_category")
    public EventRatingAVGByCategoryDTO getEventAverageRatingByCategory(@PathVariable("category") String category){
		return this.eventService.getEventRatingAverageByCategory(category);
    	
    }
    
    @GetMapping(path = "/events/{adminId}/rating_average_administrator")
    public EventRatingAVGByAdministratorDTO getEventAverageRatingByAdministrator(@PathVariable("adminId") long adminId){
		return this.eventService.getEventRatingAverageByCategory(adminId);
    	
    }
    
    @PostMapping(path = "/events/feedback")
    public @ResponseBody ResponseEntity<?> giveFeedback(@RequestBody EventFeedbackDTO eventFeedback){
    	return this.eventService.giveFeedBack(eventFeedback);
    }
    
    @DeleteMapping(path = "/events/feedback/{feedbackdId}/remove")
    public void removeFeedBack(@PathVariable("feedbackdId") long id){
    	this.eventService.removeFeedBack(id);
    }
    
    @GetMapping(path = "/events/future_events")
    public List<Event> getFutureEvents(){
    	return eventService.getFutureEvents();
    }
    
    @GetMapping(path = "/events/past_events")
    public List<Event> getPastEvents(){
    	return eventService.getPastEvents();
    }
    @GetMapping(path = "/events/current_events")
    public List<Event> getCurrentEvents(){
    	return eventService.getCurrentEvents();
    }
 
}