package co.descubra.descubraapi.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.descubra.descubraapi.core.model.User;
import co.descubra.descubraapi.exceptions.DataAlreadyExistsException;
import co.descubra.descubraapi.exceptions.DataNotFoundException;
import co.descubra.descubraapi.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping(path = "/users")
	public List<User> getAllEvents() {
		return this.userService.getAllEvents();
	}

	@GetMapping(path = "/users/{userId}")
	public User getUser(@PathVariable long userId) throws DataNotFoundException {
		return this.userService.getUser(userId);
	}

	@PostMapping(path = "/users")
	public ResponseEntity<?> createUser(@RequestBody User user) throws DataAlreadyExistsException {
		return createUser(user);
	}

	@DeleteMapping(path = "/users/{userId}")
	public void deleteUser(@PathVariable long userId) {
		this.userService.deleteUser(userId);
	}

	@PutMapping(path = "/users/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId") long id, @RequestBody User user) {
		return this.userService.updateUserService(id, user);
	}

}