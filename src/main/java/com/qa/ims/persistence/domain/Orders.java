package com.qa.ims.persistence.domain;

public class Orders {

	private Long id;
	private Long fkCustID;
	

	public Orders(Long id) {
		this.setId(id);
		
	}

	public Orders(Long id, Long fkCustID) {
		this.setId(id);
		this.setfkCustID(fkCustID);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getfkCustID() {
		return fkCustID;
	}

	public void setfkCustID(Long fkCustID) {
		this.fkCustID = fkCustID;
	}

	

	@Override
	public String toString() {
		return "id:" + id + " Customer ID" + fkCustID ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((fkCustID == null) ? 0 : fkCustID.hashCode());
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
		Orders other = (Orders) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (fkCustID == null) {
			if (other.fkCustID != null)
				return false;
		} else if (!fkCustID.equals(other.fkCustID))
			return false;
		return true;
	}

}
