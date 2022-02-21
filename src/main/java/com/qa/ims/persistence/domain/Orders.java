package com.qa.ims.persistence.domain;

public class Orders {

	private Long id;
	private Long fkCustID;
	private Long fkItemID;
	private Long orderSum;

	public Orders(Long fkCustID) {
		this.setfkCustID(fkCustID);
		
	}
	public Orders(Long id, Long fkCustID) {
		this.id = id;
		this.fkCustID= fkCustID;
		
	}
	

	public Orders(Long id, Long fkCustID, Long fkItemID) {
		this.setId(id);
		this.setfkCustID(fkCustID);
		this.setfkItemID(fkItemID);
		
		
		
	}
	public void setfkItemID(Long fkItemID) {
		this.fkItemID = fkItemID;
		
	}
	public Long getfkItemID() {
		return fkItemID;
		
		
	}
	
	
	public Long getOrderSum(){
		
		return orderSum;
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
		return "id:" + id + " Customer ID" + fkCustID + "Item ID" + fkItemID ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((fkCustID == null) ? 0 : fkCustID.hashCode());
		result = prime * result + ((fkItemID == null) ? 0 : fkItemID.hashCode());
//		result = prime * result + ((orderSum == null) ? 0 : orderSum.hashCode());
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
		if (fkItemID == null) {
			if(other.fkItemID !=null)
				return false;
		} else if (!fkItemID.equals(other.fkItemID))
			return false;
//		if (orderSum == null) {
//			if (other.orderSum != null)
//				return false;
//		} else if (!orderSum.equals(other.orderSum))
//			return false;
		return true;
	}

}
