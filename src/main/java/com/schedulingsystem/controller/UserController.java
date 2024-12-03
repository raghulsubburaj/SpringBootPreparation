package com.schedulingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedulingsystem.controller.service.UserService;
import com.schedulingsystem.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {

		userService.registerUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body("Succesfully Register New User");

	}

	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody User user) {

		boolean authentication = userService.loginAuthentication(user);

		if (authentication) {

			return ResponseEntity.status(HttpStatus.CREATED).body("Authentcation Success");

		} else {

			return ResponseEntity.status(HttpStatus.CREATED).body("Authentcation failure");
		}

	}

}
