package com.example.demo.model;

public class LoanInfo {
	
	private String loanNumber;
	private String loanType;
	private int loanAmount;
	
	
	
	public LoanInfo(String loanNumber, String loanType, int loanAmount) {
		super();
		this.loanNumber = loanNumber;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
	}
	
	
	public LoanInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}


	@Override
	public String toString() {
		return "LoanInfo [loanNumber=" + loanNumber + ", loanType=" + loanType + ", loanAmount=" + loanAmount + "]";
	}
	
	

}
