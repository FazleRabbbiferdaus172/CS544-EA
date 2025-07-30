package bankClinet.domain;

import java.util.Date;

public record AccountEntry (Long id, Date date, double amount, String description, String fromAccountNumber, String fromPersonName){

//	public double getAmount() {
//		return amount;
//	}
//
//	public void setAmount(double amount) {
//		this.amount = amount;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public String getFromAccountNumber() {
//		return fromAccountNumber;
//	}
//
//	public void setFromAccountNumber(String fromAccountNumber) {
//		this.fromAccountNumber = fromAccountNumber;
//	}
//
//	public String getFromPersonName() {
//		return fromPersonName;
//	}
//
//	public void setFromPersonName(String fromPersonName) {
//		this.fromPersonName = fromPersonName;
//	}
	
}
