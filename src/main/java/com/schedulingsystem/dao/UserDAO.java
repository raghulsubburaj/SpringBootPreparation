package com.schedulingsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.schedulingsystem.model.User;

@Repository
public class UserDAO {

	private final JdbcTemplate jdbcTemplate;

	public UserDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	public void saveUser(User user) {

		String query = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";
		jdbcTemplate.update(query, user.getEmail(), user.getPassword(), user.getRole());

	}

	public User getUserDetails(String email) {

		String sql = "SELECT * FROM users WHERE email = ?";

		return jdbcTemplate.query(sql, new Object[] { email }, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					User user = new User();
					user.setUserId(rs.getLong("user_id"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					return user;
				}
				return null;
			}
		});

	}

}
