package com.ledger.pojo;

public class Result {
	String name;
	String bankName;
	Float amountPaid;
	Integer emiLeft;

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

	public Float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Integer getEmiLeft() {
		return emiLeft;
	}

	public void setEmiLeft(Integer emiLeft) {
		this.emiLeft = emiLeft;
	}
}
