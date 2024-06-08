package tn.esprit.spring.control;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;

// userRestControl
@RestController // = @Controller + @ResponseBody 
@RequestMapping("/user")
public class UserRestControl {

	@Autowired 
	IUserService userService; 

	
	
	@GetMapping("/retrieve-all-users")
	public List<User> retrieveAllUsers() {
		return userService.retrieveAllUsers();
		//return list;
	}
 
	
	@GetMapping("/retrieve-user/{user-id}")
	public User retrieveUser(@PathVariable("user-id") String userId) {
		return userService.retrieveUser(userId);
	}
	
	 

	
	@PostMapping("/add-user")
	public User addUser(@RequestBody User u) {
		User user = userService.addUser(u); 
		return user;
	}

	
	
	@DeleteMapping("/remove-user/{user-id}") 
	public void removeUser(@PathVariable("user-id") String userId) { 
		userService.deleteUser(userId);
	} 

	
	@PutMapping("/modify-user") 
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	 
} 
 