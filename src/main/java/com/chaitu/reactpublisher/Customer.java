package com.chaitu.reactpublisher;

public class Customer {
	private String customerNumber;
	
	private String customerName;
	
	public Customer(String customerNumber, String customerName) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
