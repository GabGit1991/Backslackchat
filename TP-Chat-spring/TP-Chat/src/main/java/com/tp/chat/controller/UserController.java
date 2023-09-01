package com.tp.chat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.chat.entity.LoginRequest;
import com.tp.chat.entity.User;
import com.tp.chat.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity getUserById(@PathVariable Integer id) {
		User userSearched = userService.getUserById(id);
		if (userSearched.getName() != null) {
			return ResponseEntity.ok(userSearched);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity addUser(@RequestBody User user) {
		Boolean hasBeenAdded = userService.addUser(user);
		if (hasBeenAdded) {
			System.out.println("1");
			return ResponseEntity.ok(user);
		} else {
			System.out.println("2");
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity updateUserById(@PathVariable Integer id, @RequestBody User user) {
		if (userService.updateUserById(id, user)) {
			return ResponseEntity.ok("L'utilisateur " + id + " a bien été modifié : " + user.toString());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteUserById(@PathVariable Integer id) {
		if (userService.deleteUserById(id)) {
			return ResponseEntity.ok("L'utilisateur " + id + " a bien été supprimé");
		}
		return ResponseEntity.notFound().build();
	}
	
	// @PostMapping("/signIn")
    // public ResponseEntity<Boolean> signIn(@RequestBody LoginRequest request) {
    //     boolean isAuthenticated = userService.signIn(request.getEmail(), request.getPassword());
    //     return ResponseEntity.ok(isAuthenticated);
    // }
	 @PostMapping("/signIn")
    public ResponseEntity<User> signIn(@RequestBody LoginRequest request) {
        Optional<User> authenticatedUser = userService.signIn(request.getEmail(), request.getPassword());

        if (authenticatedUser.isPresent()) {
            User user = authenticatedUser.get();

            // Optionnel: Vous pouvez vouloir effacer le mot de passe avant de le renvoyer pour des raisons de sécurité.
            user.setPassword(null);

            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // ou tout autre réponse appropriée
    }
    

}
