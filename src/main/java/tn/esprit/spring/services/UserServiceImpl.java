package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger l = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public List<User> retrieveAllUsers() {
		// TODO Log to be added at the beginning of the method
		// l.info("Retrieving all users...");
		return null;
	}

	@Override
	public User addUser(User u) {
		User utilisateur = null;
		try {
			// TODO Log to be added at the beginning of the method
			// l.info("Adding a new user...");
			utilisateur = userRepository.save(u);
			// TODO Log to be added at the end of the method
			// l.info("User added successfully: " + utilisateur);
		} catch (Exception e) {
			// TODO Log to be added in case of an exception
			// l.error("Error in addUser(): " + e.getMessage(), e);
		}
		return utilisateur;
	}

	@Override
	public User updateUser(User u) {
		User userUpdated = null;
		try {
			// TODO Log to be added at the beginning of the method
			// l.info("Updating user...");
			userUpdated = userRepository.save(u);
			// TODO Log to be added at the end of the method
			// l.info("User updated successfully: " + userUpdated);
		} catch (Exception e) {
			// TODO Log to be added in case of an exception
			// l.error("Error in updateUser(): " + e.getMessage(), e);
		}
		return userUpdated;
	}

	@Override
	public void deleteUser(String id) {
		try {
			// TODO Log to be added at the beginning of the method
			// l.info("Deleting user with id: " + id);
			userRepository.deleteById(Long.parseLong(id));
			// TODO Log to be added at the end of the method
			// l.info("User deleted successfully with id: " + id);
		} catch (Exception e) {
			// TODO Log to be added in case of an exception
			// l.error("Error in deleteUser(): " + e.getMessage(), e);
		}
	}

	@Override
	public User retrieveUser(String id) {
		User u = null;
		try {
			// TODO Log to be added when retrieving user
			u = userRepository.findById(Long.parseLong(id)).get();
		} catch (Exception e) {
			// TODO Log to be added in case of an exception
			// l.error("Error in retrieveUser(): " + e.getMessage(), e);
		}
		return u;
	}
}
