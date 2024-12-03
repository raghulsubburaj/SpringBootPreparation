package com.schedulingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schedulingsystem.controller.service.AppoinmentService;
import com.schedulingsystem.model.AppoinmentModel;
import com.schedulingsystem.model.User;

@RestController
@RequestMapping("/appoinment")
public class AppoinmentController {

	@Autowired
	private AppoinmentService appoinmentService;

	@PostMapping("/book")
	public ResponseEntity<String> bookAppoinment(@RequestBody AppoinmentModel appoinmentModel) {

		boolean value = appoinmentService.bookAppointment(appoinmentModel);
		if (value) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Book the Appoinment");

		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Appoinment Booking Failure");

	}

	@GetMapping("/list")
	public ResponseEntity<List<AppoinmentModel>> getAppoinmentList(@RequestBody User user) {

		List<AppoinmentModel> appoinmentModel = appoinmentService.getAppointmentList(user);
		if (appoinmentModel.isEmpty()) {

			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(appoinmentModel);

	}

	@PutMapping("/appointmentStatus")
	public ResponseEntity<String> updateAppointmentStatus(@RequestBody AppoinmentModel appoinmentModel) {
		if (appoinmentService.updateAppointmentStatus(appoinmentModel)) {

			return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Update Appointment Status");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed to Update Appointment Status");
		}

	}
	
	@GetMapping("/specificList")
	public ResponseEntity<List<AppoinmentModel>> getSpecificAppoinmentList(@RequestBody AppoinmentModel appoinmentModel) {

		List<AppoinmentModel> appointmentModel = appoinmentService.getSpecificAppointmentList(appoinmentModel);
		if (appointmentModel.isEmpty()) {

			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(appointmentModel);

	}

}
