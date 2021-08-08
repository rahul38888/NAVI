package com.ledger.pojo;

public class LoanDetails {
	String name;
	String bankName;
	Float principleAmount;
	Integer years;
	Float rateOfInterest;

	public LoanDetails(String name, String bankName, Float principleAmount, Integer years, Float rateOfInterest) {
		this.name = name;
		this.bankName = bankName;
		this.principleAmount = principleAmount;
		this.years = years;
		this.rateOfInterest = rateOfInterest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Float getPrincipleAmount() {
		return principleAmount;
	}

	public void setPrincipleAmount(Float principleAmount) {
		this.principleAmount = principleAmount;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Float getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Float rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
}
