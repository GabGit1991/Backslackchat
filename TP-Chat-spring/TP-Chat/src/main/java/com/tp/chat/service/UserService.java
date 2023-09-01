package com.tp.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.chat.entity.User;
import com.tp.chat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Boolean addUser(User user) {
		if (user.getEmail() != null && user.getName() != null && user.getNickname() != null) {
			System.out.println("3");
			userRepository.save(user);
			return true;
		} else {
			System.out.println("4");
			return false;
		}
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User foundUser = optionalUser.get();
			return foundUser;
		} else {
			return new User();
		}
	}

	public Boolean deleteUserById(Integer id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
			;
			return true;
		} else
			return false;
	}

	public Boolean updateUserById(Integer id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent() && user.getId() == id) {
			userRepository.save(null);
			return true;
		} else {
			return false;
		}
	}
	public Optional<User> signIn(String email, String password) {
       
        // return userRepository.findByEmailAndPassword(email, password).isPresent();
		return userRepository.findByEmailAndPassword(email, password);
    }

}
