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

public class ReimbursementDaoJDBC implements ReimbursementDao {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private Logger log = Logger.getRootLogger();

	private Reimbursement extractReimbursement(ResultSet rs) throws SQLException {

		Reimbursement r = new Reimbursement();
		r.setReimbId(rs.getInt("REIMB_ID"));
		r.setReimbAmount(rs.getDouble("REIMB_AMOUNT"));
		r.setReimbSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
		r.setReimbResolved(rs.getTimestamp("REIMB_RESOLVED"));
		r.setReimbDescription(rs.getString("REIMB_DESCRIPTION"));
		r.setReimbAuthor(rs.getInt("REIMB_AUTHOR"));
		r.setReimResolver(rs.getInt("REIMB_AUTHOR"));
		r.setReimStatusId(rs.getInt("REIMB_STATUS_ID"));
		r.setReimTypeId(rs.getInt("REIMB_TYPE_ID"));

		return r;
	}

	@Override
	public List<Reimbursement> findbyUserId(int userId) {

		log.debug("attempting to retreive all reimbursements from the database");
		List<Reimbursement> reimbursements = new ArrayList<>();
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			log.trace("extracting reimbursement from the result set");
			while (rs.next()) {

				Reimbursement r = extractReimbursement(rs);
				/*r.setReimbursements(rs.findByReimbursementId(r.getUserId()));*/
				reimbursements.add(r);
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
			log.warn("failed to retreive all users from the database");
			return null;
		}

	
	}

}
