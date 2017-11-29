package com.santosh.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.santosh.beans.Reimbursement;
import com.santosh.dao.ReimbursementDao;
import com.santosh.dao.ReimbursementDaoJDBC;

public class ReimbursementService {

	private Logger log = Logger.getRootLogger();
	private ReimbursementDao rd = new ReimbursementDaoJDBC();

	public void saveReimbursement(Reimbursement reimbursement) {
		log.debug("Sending to ReimbusementDaoJdbc to add the reimbursement");
		rd.addNewReimbursement(reimbursement);

	}

	public List<Reimbursement> getReimbursement(int userId) {
		List<Reimbursement> rl = new ArrayList<>();
		rl = rd.findbyUserId(userId);
		return rl;

	}

}
