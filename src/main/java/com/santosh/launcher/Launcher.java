package com.santosh.launcher;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.santosh.beans.Employee;
import com.santosh.beans.Reimbursement;
import com.santosh.dao.EmployeeDao;
import com.santosh.dao.EmployeeDaoJDBC;
import com.santosh.dao.ReimbursementDao;
import com.santosh.dao.ReimbursementDaoJDBC;
import com.santosh.services.ReimbursementService;
import com.santosh.util.ConnectionUtil;

import oracle.jdbc.oracore.Util;

public class Launcher {

	static EmployeeDao ed = new EmployeeDaoJDBC();
	static ReimbursementDao rd = new ReimbursementDaoJDBC();
	static ReimbursementService rs = new ReimbursementService();

	public static void main(String[] args) {

		ConnectionUtil conn = new ConnectionUtil();

		try {
			conn.getConnection();

			Employee emp1 = ed.findUser("santoshbaral31", "tomato");

			System.out.println(emp1.getFirstName() + " " + emp1.getLastName());

			System.out.println(emp1.getReimbursements());

			Reimbursement newReimbursement = new Reimbursement();
			newReimbursement.setReimbAmount(31.31);
			newReimbursement.setReimbSubmitted(null);
			newReimbursement.setReimbDescription("Added reimbursement from java launcher");
			newReimbursement.setReimbAuthor(emp1.getUserId());
			newReimbursement.setReimTypeId(1);

			int rem = rd.addNewReimbursement(newReimbursement);
			
			System.out.println("All reimbursement of a user");
			
			List<Reimbursement> allReimlist = rs.getReimbursement(10000);
			
			for(int i =0; i<allReimlist.size(); i++) {
				System.out.println(allReimlist);
			}
			
			System.out.println("Filter by status");
			List<Reimbursement> reimByStatus = rd.findByStatus(10000, 1);
			
			for(int i =0; i<reimByStatus.size(); i++) {
				System.out.println(reimByStatus);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
