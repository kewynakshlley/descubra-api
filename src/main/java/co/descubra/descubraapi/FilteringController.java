package co.descubra.descubraapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.descubra.descubraapi.models.Administrator;


@RestController
public class FilteringController {
    
   @GetMapping("/filtering")
   public Administrator retrieveAdministratorBean() {
       return new Administrator(null, null, null, null, null, null);
   }
    

}