package com.schedulingsystem.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schedulingsystem.dao.AppoinmentDAO;
import com.schedulingsystem.model.AppoinmentModel;
import com.schedulingsystem.model.User;

@Service
public class AppoinmentService {

	@Autowired
	private AppoinmentDAO appoinmentDAO;

	public boolean bookAppointment(AppoinmentModel appoinmentModel) {

		int value = appoinmentDAO.saveAppoinment(appoinmentModel);

		if (value != 0) {

			return true;

		}

		return false;

	}

	public List<AppoinmentModel> getAppointmentList(User user) {

		if (user.getRole().equals("patient")) {
			List<AppoinmentModel> appointmentModel = appoinmentDAO.getAppointmentPatientList(user);
			return appointmentModel;
		} else {
			List<AppoinmentModel> appointmentModel = appoinmentDAO.getAppointmentDoctorList(user);
			return appointmentModel;
		}
	}

	public boolean updateAppointmentStatus(AppoinmentModel appoinmentModel) {

		int value;

		System.out.println("Patient Id : " + appoinmentModel.getPatientId());
		System.out.println("Doctor Id : " + appoinmentModel.getDoctorId());
		System.out.println("Status :" + appoinmentModel.getStatus());

		if (appoinmentModel.getPatientId() != null && ("cancelled").equals(appoinmentModel.getStatus())) {

			value = appoinmentDAO.updateAppoinmentPatientStatus(appoinmentModel);

		} else if (appoinmentModel.getDoctorId() != null && (("cancelled").equals(appoinmentModel.getStatus())
				|| ("confirmed").equals(appoinmentModel.getStatus()))) {

			value = appoinmentDAO.updateAppoinmentDoctorStatus(appoinmentModel);

		} else {
			return false;
		}

		if (value > 0) {

			return true;
		} else {
			return false;
		}

	}
	
	public List<AppoinmentModel> getSpecificAppointmentList(AppoinmentModel appoinmentModel) {

	
			List<AppoinmentModel> appointmentModel = appoinmentDAO.getAppointmentSpecificList(appoinmentModel);
			return appointmentModel;
		
	}

}
