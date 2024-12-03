package com.schedulingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedulingsystem.controller.service.EmailService;
import com.schedulingsystem.model.AppoinmentModel;

@RestController
@RequestMapping("/email")
public class NotificationController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/notify")
	public ResponseEntity<String> getAppointmentDetails(@RequestBody AppoinmentModel appoinmentModel) {

		boolean value = emailService.getAppointmentDetails(appoinmentModel);

		if (value) {

			return ResponseEntity.status(HttpStatus.CREATED).body("Email Send Successfully");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Email Send Failure");

	}

}
