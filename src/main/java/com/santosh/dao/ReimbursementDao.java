package com.santosh.dao;

import java.util.List;

import com.santosh.beans.Reimbursement;

public interface ReimbursementDao {

	List<Reimbursement> findbyUserId(int userId);
	
	
}