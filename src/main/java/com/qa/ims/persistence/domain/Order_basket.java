package com.qa.ims.persistence.domain;

public class Order_basket {

	private Long fkorderID;
	private Long fkItemID;
	private Long quantity;

	public Order_basket(Long fkorderID) {
		this.setfkOrderID(fkorderID);

	}
	public Order_basket(Long id, Long fkItemID ) {
		this.setfkOrderID(id);
		this.setfkItemID(fkItemID);
		
	}

	public Order_basket(Long fkorderID, Long fkItemID, Long quantity) {
		this.setfkOrderID(fkorderID);
		this.setfkItemID(fkItemID);
		this.setQuantity(quantity);

	}
	public Long getQuantity(){
		return quantity;
		
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
		
	}
	public Long getfkOrderID() {
		return fkorderID;
	}

	public void setfkOrderID(Long fkorderID) {
		this.fkorderID = fkorderID;
	}

	public Long getfkItemID() {
		return fkItemID;
	}

	public void setfkItemID(long fkItemID) {
		this.fkItemID = fkItemID;
		
	}
	@Override public String toString() {
		return "id:" + fkorderID + " item ID" + fkItemID + "Quantity:" + quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkorderID == null) ? 0 : fkorderID.hashCode());
		result = prime * result + ((fkItemID == null) ? 0 : fkItemID.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		Order_basket other = (Order_basket) obj;
		if (fkorderID == null) {
			if (other.fkorderID != null)
				return false;
		} else if (!fkorderID.equals(other.fkorderID))
			return false;
		if (fkItemID == null) {
			if (other.fkItemID != null)
				return false;
		} else if (!fkItemID.equals(other.fkItemID))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

}
