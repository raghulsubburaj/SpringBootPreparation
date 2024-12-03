package com.schedulingsystem.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schedulingsystem.dao.UserDAO;
import com.schedulingsystem.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public void registerUser(User user) {

		userDAO.saveUser(user);

	}

	public boolean loginAuthentication(User user) {

		String email = user.getEmail();
		String password = user.getPassword();

		User userDetails = userDAO.getUserDetails(email);

		if (userDetails != null && userDetails.getPassword().equals(password)) {
			return true;

		}

		return false;

	}
}
