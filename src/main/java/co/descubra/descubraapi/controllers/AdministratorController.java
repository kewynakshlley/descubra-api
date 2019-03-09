package co.descubra.descubraapi.controllers;

import java.util.List;


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
import org.springframework.web.bind.annotation.RestController;

import co.descubra.descubraapi.core.model.Administrator;
import co.descubra.descubraapi.core.model.Event;
import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.service.AdministratorService;


@RestController
public class AdministratorController {
	public static final Logger log =  LoggerFactory.getLogger(AdministratorController.class);

   @Autowired
   private AdministratorService administratorService;
      
    
   @GetMapping(path = "/administrators")
   public List<Administrator> getAllAdministrators() {
       return this.administratorService.getAllAdministrators();
   }
    
   @GetMapping (path = "/administrators/{admId}")
   public Administrator getAdministrator(@PathVariable long admId) throws DataNotFoundException {
	   return this.administratorService.getAdministrator(admId);
   }

    
   @PostMapping(path = "/administrators")
   public ResponseEntity<?> createAdministrator(@RequestBody Administrator administrator) throws DataAlreadyExistsException {
	   return this.administratorService.createAdministrator(administrator);
   }

    
   @DeleteMapping(path = "/administrators/{admId}")
   public void deleteAdministrator(@PathVariable long admId) {
	   administratorService.deleteAdministrator(admId);
   }

   
   @GetMapping(path = "/administrators/{admId}/events")
   public List<Event> getAllEventsFromAdministrator(@PathVariable long admId){
	   return this.administratorService.getAllEventsFromAdministrator(admId);
   }

   
   @PostMapping(path = "/administrators/{admId}/events")
   public ResponseEntity<?> createEventForASpecificAdministrator(@PathVariable long admId, @RequestBody Event event) {
	   return this.administratorService.createEventForASpecificAdministrator(admId, event);
   }
   
   @PutMapping(path = "/administrators/{admId}/events/{eventsId}")
   public ResponseEntity<?> updateEventForASpecificAdministrator
   (@PathVariable long admId, @RequestBody Event event, @PathVariable long eventsId) {
	   return this.administratorService.updateEventForASpecificAdministrator(admId, event, eventsId);
   }
   
}



   
  
    
