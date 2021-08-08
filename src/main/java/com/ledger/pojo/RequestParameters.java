package com.ledger.pojo;

public class RequestParameters {
	String name;
	String bankName;
	Float principleAmount;
	Integer years;
	Float rateOfInterest;
	Float lumpSumAmount;
	Integer emiNo;

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

	public Float getLumpSumAmount() {
		return lumpSumAmount;
	}

	public void setLumpSumAmount(Float lumpSumAmount) {
		this.lumpSumAmount = lumpSumAmount;
	}

	public Integer getEmiNo() {
		return emiNo;
	}

	public void setEmiNo(Integer emiNo) {
		this.emiNo = emiNo;
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
