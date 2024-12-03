package com.schedulingsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.schedulingsystem.model.AppoinmentModel;
import com.schedulingsystem.model.User;

@Repository
public class AppoinmentDAO {

	private final JdbcTemplate jdbcTemplate;

	public AppoinmentDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	public int saveAppoinment(AppoinmentModel appoinmentModel) {

		String query = "INSERT INTO appointments (patient_id, doctor_id) VALUES (?, ?)";

		return jdbcTemplate.update(query, appoinmentModel.getPatientId(), appoinmentModel.getDoctorId());

	}

	@SuppressWarnings({ "deprecation"})
	public List<AppoinmentModel> getAppointmentPatientList(User user) {

		String query = "SELECT * FROM appointments WHERE patient_id = ?";

		
		return jdbcTemplate.query(query, new Object[] { user.getUserId() }, rs -> {
			List<AppoinmentModel> appointmentList = new ArrayList<>();
			while (rs.next()) {
				AppoinmentModel appoinmentModel = new AppoinmentModel();
				appoinmentModel.setAppointmentId(rs.getLong("appointment_id"));
				appoinmentModel.setPatientId(rs.getLong("patient_id"));
				appoinmentModel.setDoctorId(rs.getLong("doctor_id"));
				appoinmentModel.setAppointmentTime(rs.getTimestamp("appointment_time").toLocalDateTime());
				appoinmentModel.setStatus(rs.getString("status"));
				appointmentList.add(appoinmentModel);
			}
			return appointmentList;
		});
	}

	@SuppressWarnings({ "deprecation"})
	public List<AppoinmentModel> getAppointmentDoctorList(User user) {

		//String query = "SELECT * FROM appointments WHERE doctor_id = ? ";
        String query = "SELECT a.* FROM appointments a JOIN doctors d ON a.doctor_id = d.doctor_id WHERE d.user_id = ?";
		return jdbcTemplate.query(query, new Object[] { user.getUserId() }, rs -> {
			List<AppoinmentModel> appointmentList = new ArrayList<>();
			while (rs.next()) {
				AppoinmentModel appoinmentModel = new AppoinmentModel();
				appoinmentModel.setAppointmentId(rs.getLong("appointment_id"));
				appoinmentModel.setPatientId(rs.getLong("patient_id"));
				appoinmentModel.setDoctorId(rs.getLong("doctor_id"));
				appoinmentModel.setAppointmentTime(rs.getTimestamp("appointment_time").toLocalDateTime());
				appoinmentModel.setStatus(rs.getString("status"));
				appointmentList.add(appoinmentModel);
			}
			return appointmentList;
		});

	}

	public int updateAppoinmentPatientStatus(AppoinmentModel appoinmentModel) {

		String sql = "UPDATE appointments SET status = ? WHERE patient_id = ? and appointment_id = ?";

		int value = jdbcTemplate.update(sql, appoinmentModel.getStatus(), appoinmentModel.getPatientId(),
				appoinmentModel.getAppointmentId());

		return value;

	}

	public int updateAppoinmentDoctorStatus(AppoinmentModel appoinmentModel) {

		String sql = "UPDATE appointments SET status = ? WHERE doctor_id = ? and appointment_id = ?";

		int value = jdbcTemplate.update(sql, appoinmentModel.getStatus(), appoinmentModel.getDoctorId(),
				appoinmentModel.getAppointmentId());

		return value;

	}

	@SuppressWarnings({ "deprecation"})
	public List<AppoinmentModel> getAppointmentSpecificList(AppoinmentModel appoinmentModel) {

		String query = "SELECT * FROM appointments WHERE appointment_id = ? ";

		return jdbcTemplate.query(query, new Object[] { appoinmentModel.getAppointmentId() }, rs -> {
			List<AppoinmentModel> appointmentList = new ArrayList<>();
			while (rs.next()) {
				AppoinmentModel appointmentModel = new AppoinmentModel();
				appointmentModel.setAppointmentId(rs.getLong("appointment_id"));
				appointmentModel.setPatientId(rs.getLong("patient_id"));
				appointmentModel.setDoctorId(rs.getLong("doctor_id"));
				appointmentModel.setAppointmentTime(rs.getTimestamp("appointment_time").toLocalDateTime());
				appointmentModel.setStatus(rs.getString("status"));
				appointmentList.add(appointmentModel);
			}
			return appointmentList;
		});

	}

}
