package com.santosh.beans;

import java.util.Date;

public class Reimbursement {
	
	private int reimbId;
	private double reimbAmount;
	private Date reimbSubmitted;
	private Date reimbResolved;
	private String reimbDescription;
	private int reimbAuthor;
	private int reimResolver;
	private int reimStatusId;
	private int reimTypeId;
	
	
	public Reimbursement() {
		super();
		
	}


	public Reimbursement(int reimbId, double reimbAmount, Date reimbSubmitted, Date reimbResolved,
			String reimbDescription, int reimbAuthor, int reimResolver, int reimStatusId, int reimTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimResolver = reimResolver;
		this.reimStatusId = reimStatusId;
		this.reimTypeId = reimTypeId;
	}


	public int getReimbId() {
		return reimbId;
	}


	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}


	public double getReimbAmount() {
		return reimbAmount;
	}


	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}


	public Date getReimbSubmitted() {
		return reimbSubmitted;
	}


	public void setReimbSubmitted(Date reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}


	public Date getReimbResolved() {
		return reimbResolved;
	}


	public void setReimbResolved(Date reimbResolved) {
		this.reimbResolved = reimbResolved;
	}


	public String getReimbDescription() {
		return reimbDescription;
	}


	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}


	public int getReimbAuthor() {
		return reimbAuthor;
	}


	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}


	public int getReimResolver() {
		return reimResolver;
	}


	public void setReimResolver(int reimResolver) {
		this.reimResolver = reimResolver;
	}


	public int getReimStatusId() {
		return reimStatusId;
	}


	public void setReimStatusId(int reimStatusId) {
		this.reimStatusId = reimStatusId;
	}


	public int getReimTypeId() {
		return reimTypeId;
	}


	public void setReimTypeId(int reimTypeId) {
		this.reimTypeId = reimTypeId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimResolver;
		result = prime * result + reimStatusId;
		result = prime * result + reimTypeId;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimbAuthor;
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (reimResolver != other.reimResolver)
			return false;
		if (reimStatusId != other.reimStatusId)
			return false;
		if (reimTypeId != other.reimTypeId)
			return false;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbAuthor != other.reimbAuthor)
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbAuthor=" + reimbAuthor + ", reimResolver=" + reimResolver + ", reimStatusId=" + reimStatusId
				+ ", reimTypeId=" + reimTypeId + "]";
	}


	
}
