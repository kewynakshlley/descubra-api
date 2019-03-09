package co.descubra.descubraapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.descubra.descubraapi.core.model.User;
import co.descubra.descubraapi.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> getAllEvents() {
        return userRepository.findAll();
    }
	
	public ResponseEntity<?> createUser(User user) {
		User createdUser = userRepository.save(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
	}
	
	
	public User getUser(long userId){
        return this.userRepository.findById(userId).get();
    }
	
	
	public void deleteUser(long userId) {
    	this.userRepository.deleteById(userId);
    }
	
	public ResponseEntity<?> updateUserService(long id, User user) {
		user.setId(id);
        user = userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	
	
	
	
 

}
