package com.santosh.beans;

public class ReimbursementStatus {
	
	private int reimStatusId;
	private String reimStatus;
	
	public ReimbursementStatus() {
		super();
		
	}

	public ReimbursementStatus(int reimStatusId, String reimStatus) {
		super();
		this.reimStatusId = reimStatusId;
		this.reimStatus = reimStatus;
	}

	public int getReimStatusId() {
		return reimStatusId;
	}

	public void setReimStatusId(int reimStatusId) {
		this.reimStatusId = reimStatusId;
	}

	public String getReimStatus() {
		return reimStatus;
	}

	public void setReimStatus(String reimStatus) {
		this.reimStatus = reimStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimStatus == null) ? 0 : reimStatus.hashCode());
		result = prime * result + reimStatusId;
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
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (reimStatus == null) {
			if (other.reimStatus != null)
				return false;
		} else if (!reimStatus.equals(other.reimStatus))
			return false;
		if (reimStatusId != other.reimStatusId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [reimStatusId=" + reimStatusId + ", reimStatus=" + reimStatus + "]";
	}
	
	

}
