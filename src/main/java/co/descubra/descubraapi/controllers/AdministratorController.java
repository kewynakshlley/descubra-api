package co.descubra.descubraapi.controllers;


import java.net.URI;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.models.Administrator;
import co.descubra.descubraapi.models.Event;
import co.descubra.descubraapi.repository.AdministratorService;
import co.descubra.descubraapi.repository.EventService;




@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdministratorController {
	public static final Logger log =  LoggerFactory.getLogger(AdministratorController.class);

   @Autowired
   private AdministratorService administratorService;
   
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
   public ResponseEntity<Object> createAdministrator(@RequestBody Administrator administrator) throws DataAlreadyExistsException {
       Administrator createdAdministrator = administratorService.save(administrator);
        
       /* Wil return the current request URI 
        * When we create a resource, the best status code to return is 201 Created.
        * */
		URI location = ServletUriComponentsBuilder
       .fromCurrentRequest()
       .path("/{id}")
       .buildAndExpand(createdAdministrator.getAdministratorId())
       .toUri();
        
       return ResponseEntity.created(location).build();
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
   public ResponseEntity<Object> createEventForASpecificAdministrator(@PathVariable long admId, @RequestBody Event event) {
	   Administrator adm = administratorService.getOne(admId);
	   if(adm == null) {
		   throw new DataNotFoundException("O administrador não existe!");
	   }
	   event.setAdministrator(adm);
	   eventService.save(event);
	   URI location = ServletUriComponentsBuilder
		       .fromCurrentRequest()
		       .path("/{id}")
		       .buildAndExpand(event.getEventId())
		       .toUri();
	   return ResponseEntity.created(location).build();
   }
   
  
    

}
