package com.santosh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.santosh.beans.Employee;
import com.santosh.beans.Reimbursement;
import com.santosh.util.ConnectionUtil;

public class EmployeeDaoJDBC implements EmployeeDao {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private Logger log = Logger.getRootLogger();
	private ReimbursementDao rd = new ReimbursementDaoJDBC();

	private Employee extractEmployee(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		e.setUserId(rs.getInt("ERS_USERS_ID"));
		e.setUsername(rs.getString("ERS_USERNAME"));
		e.setPassword(rs.getString("ERS_PASSWORD"));
		e.setFirstName(rs.getString("USER_FIRST_NAME"));
		e.setLastName(rs.getString("USER_LAST_NAME"));
		e.setEmail(rs.getString("USER_EMAIL"));
		e.setUserRole(rs.getInt("USER_ROLE_ID"));

		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		reimbursements = rd.findbyUserId(e.getUserId());
		e.setReimbursements(reimbursements);

		return e;
	}

	@Override
	public List<Employee> findAll() {

		log.debug("attempting to retreive all employee from the database");
		List<Employee> employees = new ArrayList<>();
		try (Connection conn = cu.getConnection()) {
			ResultSet rs = conn.prepareStatement("SELECT * FROM ERS_USERS").executeQuery();

			log.trace("extracting employees from the result set");
			while (rs.next()) {
				Employee e = extractEmployee(rs);
				/* e.setReimbursements(rd.findbyUserId(e.getUserId())); */
				employees.add(e);
			}
			return employees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.warn("failed to retreive all users from the database");
			return null;
		}

	}

	@Override
	public Employee findUser(String username, String password) {
		log.debug("Finding the employee with username and password");
		Employee emp = new Employee();

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND ERS_PASSWORD= ?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			log.trace("Extracting employee");
			while (rs.next()) {
				Employee e = extractEmployee(rs);
				emp = e;
			}
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
