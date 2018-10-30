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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.models.Administrator;
import co.descubra.descubraapi.models.Event;
import co.descubra.descubraapi.repository.AdministratorService;
import co.descubra.descubraapi.repository.EventService;
import co.descubra.descubraapi.repository.UserService;

@RestController

public class AdministratorController {
	public static final Logger log =  LoggerFactory.getLogger(AdministratorController.class);

   @Autowired
   private AdministratorService administratorService;
   
   @Autowired
   private UserService userService;
   
   @Autowired 
   private EventService eventService;
   
    
   @GetMapping(path = "/administrators")
   public List<Administrator> getAllAdministrators() {
       return administratorService.findAll();
   }
  
    
   @GetMapping (path = "/administrators/{admId}")
   public Administrator getAdministrator(@PathVariable long admId) throws DataNotFoundException {
	   Administrator administrator = administratorService.getOne(admId);
	   if(administrator == null) {
		   throw new DataNotFoundException("Administrator not found: " + admId);
	   }
       return administrator;
   }
    
   @PostMapping(path = "/administrators")
   public ResponseEntity<?> createAdministrator(@RequestBody Administrator administrator) throws DataAlreadyExistsException {
	   
	   administrator.setUser(userService.save(administrator.getUser()));
	   Administrator createdAdministrator = administratorService.save(administrator);
       return new ResponseEntity<Administrator>(createdAdministrator, HttpStatus.OK);
   }
    
   @DeleteMapping(path = "/administrators/{admId}")
   public void deleteEvent(@PathVariable long admId) {
	   administratorService.deleteById(admId);
   }
    
   @GetMapping(path = "/administrators/{admId}/events")
   public List<Event> getAllEventsFromAdministrator(@PathVariable long admId){
	   Administrator adm = administratorService.getOne(admId);
	   if(adm == null) {
		   throw new DataNotFoundException("O administrador não existe!");
	   }
       return adm.getEvents();
   }
   
   @PostMapping(path = "/administrators/{admId}/events")
   public ResponseEntity<?> createEventForASpecificAdministrator(@PathVariable long admId, @RequestBody Event event) {
	   Administrator adm = administratorService.findById(admId).get();
	   if(adm == null) {
		   throw new DataNotFoundException("O administrador não existe!");
	   }
	   event.setAdministrator(adm);
	   event = eventService.save(event);
	   return new ResponseEntity<Event>(event, HttpStatus.CREATED);
   }
   
  
    

}
