package co.descubra.descubraapi.controllers;

import java.net.URI;

import java.util.List;




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
import co.descubra.descubraapi.models.User;
import co.descubra.descubraapi.repository.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
    @GetMapping(path = "/users")
    public List<User> getAllEvents() {
        return userService.findAll();
    }
    
    
    @GetMapping (path = "/users/{userId}")
    public User getUser(@PathVariable long userId) throws DataNotFoundException {
     
        return userService.getOne(userId);
    }
     
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) throws DataAlreadyExistsException {
        User createdUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdUser.getId())
        .toUri();
         
        return ResponseEntity.created(location).build();
    }
     
    @DeleteMapping(path = "/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
    	userService.deleteById(userId);
    }
     
    @PutMapping(path = "/users/{userId}")
    public void updateUser(@PathVariable("userId") long id, User user) {
        user.setId(id);
        deleteUser(id);
        userService.save(user);
    }
    
    // lembrar de adicionar esse metodo para um service
    
 
}