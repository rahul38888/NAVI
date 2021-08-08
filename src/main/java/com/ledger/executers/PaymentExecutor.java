package com.ledger.executers;

import com.ledger.database.LedgerData;
import com.ledger.pojo.AccountDetails;
import com.ledger.pojo.Result;
import com.ledger.pojo.RequestParameters;

public class PaymentExecutor implements Executor{

	private static PaymentExecutor executor;
	private LedgerData ledger;

	private PaymentExecutor(){
		super();
		ledger = LedgerData.getInstance();
	}

	public static PaymentExecutor getInstance() {
		if(executor == null)
			executor = new PaymentExecutor();
		return executor;
	}

	@Override
	public Result execute(RequestParameters parameters) {
		AccountDetails account = ledger.accessAccount(parameters.getName(), parameters.getBankName());
		if(account==null)
			return null;

		account.getLumpSums().add(
				new AccountDetails.LumpSum(parameters.getEmiNo(), parameters.getLumpSumAmount()));

		return null;
	}
}
