package com.ledger.pojo;

import java.util.Comparator;
import java.util.TreeSet;

public class AccountDetails extends LoanDetails {

	TreeSet<LumpSum> lumpSums;
	Float emiAmount;
	Float totalAmount;

	public AccountDetails(RequestParameters parameters) {
		super(parameters.name, parameters.bankName, parameters.principleAmount, parameters.years, parameters.rateOfInterest);

		totalAmount = principleAmount + principleAmount * years * rateOfInterest / 100;
		emiAmount = (float) Math.ceil(totalAmount / (years*12));
		lumpSums = new TreeSet<>(
				new Comparator<LumpSum>() {
					@Override
					public int compare(LumpSum lumpSum, LumpSum t1) {
						return lumpSum.prevEmiNo.compareTo(t1.prevEmiNo);
					}
				}
		);
	}

	public TreeSet<LumpSum> getLumpSums() {
		return lumpSums;
	}

	public void setLumpSums(TreeSet<LumpSum> lumpSums) {
		this.lumpSums = lumpSums;
	}

	public Float getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Float emiAmount) {
		this.emiAmount = emiAmount;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LoanDetails that = (LoanDetails) o;

		if (!name.equals(that.name)) return false;
		return bankName.equals(that.bankName);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + bankName.hashCode();
		return result;
	}

	static public class LumpSum{
		public Integer prevEmiNo;
		public Float amount;

		public LumpSum(Integer prevEmiNo, Float amount) {
			this.prevEmiNo = prevEmiNo;
			this.amount = amount;
		}
	}
}
