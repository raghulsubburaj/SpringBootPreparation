package com.schedulingsystem.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.schedulingsystem.model.AppoinmentModel;
import com.schedulingsystem.model.User;

@Repository
public class EmailDAO {

	private final JdbcTemplate jdbcTemplate;

	public EmailDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	@SuppressWarnings("deprecation")
	public List<AppoinmentModel> getEmailDetails(AppoinmentModel appoinmentModel) {

		String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

		return jdbcTemplate.query(sql, new Object[] { appoinmentModel.getAppointmentId() }, rs -> {
			List<AppoinmentModel> appointmentList = new ArrayList<>();
			while (rs.next()) {
				AppoinmentModel appoinmentModelList = new AppoinmentModel();
				appoinmentModelList.setAppointmentId(rs.getLong("appointment_id"));
				appoinmentModelList.setPatientId(rs.getLong("patient_id"));
				appoinmentModelList.setDoctorId(rs.getLong("doctor_id"));
				appoinmentModelList.setAppointmentTime(rs.getTimestamp("appointment_time").toLocalDateTime());
				appoinmentModelList.setStatus(rs.getString("status"));
				appointmentList.add(appoinmentModelList);

			}
			return appointmentList;
		});

	}

	@SuppressWarnings("deprecation")
	public List<User> getPatientEmail(String patientId) {

		String sql = "SELECT * FROM users WHERE user_id = ?";

		return jdbcTemplate.query(sql, new Object[] { patientId }, rs -> {
			List<User> usersList = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				usersList.add(user);
			}
			return usersList;

		});

	}

}
