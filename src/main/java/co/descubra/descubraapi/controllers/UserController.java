package co.descubra.descubraapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.models.User;
import co.descubra.descubraapi.repository.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(path = "/users")
    public List<User> getAllEvents() {
        return userService.findAll();
    }
    
    
    @GetMapping (path = "/users/{userId}")
    public User getUser(@PathVariable long userId) throws DataNotFoundException {
        return userService.findById(userId).get();
    }
     
    @PostMapping(path = "/users")
    public ResponseEntity<?> createUser(@RequestBody User user) throws DataAlreadyExistsException {
        User createdUser = userService.save(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }
     
    @DeleteMapping(path = "/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
    	userService.deleteById(userId);
    }
     
    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") long id, @RequestBody User user) {
        user.setId(id);
        user = userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
}