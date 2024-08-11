package com.example.demo.model;

public class Borrower {
	
	private String borrowerName;
	private String borrowerEmail;
	private double borrowerPhone;
	
	public Borrower(String borrowerName, String borrowerEmail, double borrowerPhone) {
		super();
		this.borrowerName = borrowerName;
		this.borrowerEmail = borrowerEmail;
		this.borrowerPhone = borrowerPhone;
	}

	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getBorrowerEmail() {
		return borrowerEmail;
	}

	public void setBorrowerEmail(String borrowerEmail) {
		this.borrowerEmail = borrowerEmail;
	}

	public double getBorrowerPhone() {
		return borrowerPhone;
	}

	public void setBorrowerPhone(double borrowerPhone) {
		this.borrowerPhone = borrowerPhone;
	}
	
	

}
