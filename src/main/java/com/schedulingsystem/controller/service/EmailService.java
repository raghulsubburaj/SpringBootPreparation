package com.schedulingsystem.controller.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.schedulingsystem.dao.EmailDAO;
import com.schedulingsystem.model.AppoinmentModel;
import com.schedulingsystem.model.User;

@Service
public class EmailService {

	@Autowired
	private EmailDAO emailDAO;

	@Autowired
	private JavaMailSender javaMailSender;

	public boolean getAppointmentDetails(AppoinmentModel appoinmentModel) {
		String email = null;
		String patientId = null;
		List<AppoinmentModel> appointmentModel = emailDAO.getEmailDetails(appoinmentModel);

		AppoinmentModel patientList = appointmentModel.get(1);

		long patienId = patientList.getPatientId();

		if (appointmentModel.isEmpty()) {
			return false;

		} else {

//			List<User> userList = emailDAO.getPatientEmail(patientId);
//			
//			for (int i = 0; i < userList.size(); i++) {
//				
//				patientId = toString(userList.get(1));
//				break;
			return true;
		}

	}

//		String subject = "Appointment Reminder From YYYY";
//		String message = "Dear user, this is a reminder for your appointment with doctor  "
//				+ appoinmentModel.getDoctorId() + "Scheduled For " + appoinmentModel.getAppointmentTime();
//
//		// String email = "raghul.s@teleapps.com";
//		return sendReminderEmail(email, subject, message);
//
//	}

	public boolean sendReminderEmail(String to, String subject, String message) {
		try {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(to);
			email.setSubject(subject);
			email.setText(message);
			javaMailSender.send(email);
			return true;
		} catch (Exception e) {

			System.out.println("Email Send Failure " + e);

		}

		return false;
	}

}
