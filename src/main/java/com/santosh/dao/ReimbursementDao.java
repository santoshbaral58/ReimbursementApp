package com.santosh.dao;

import java.util.List;

import com.santosh.beans.Reimbursement;

public interface ReimbursementDao {

	// list all the reimbursement of a particular user by their userId
	List<Reimbursement> findbyUserId(int userId);

	// list all the reimbursment of a particular user by the status Id of the
	// reimbursement
	List<Reimbursement> findByStatus(int userId, int statusId);

	// adding new reimbursement
	int addNewReimbursement(Reimbursement newReimbursement);

}