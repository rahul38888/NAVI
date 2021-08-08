package com.ledger.executers;

import com.ledger.database.LedgerData;
import com.ledger.pojo.AccountDetails;
import com.ledger.pojo.Result;
import com.ledger.pojo.RequestParameters;

import java.util.TreeSet;

public class BalanceExecutor implements Executor{

	private static BalanceExecutor executor;
	private LedgerData ledger;

	private BalanceExecutor(){
		super();
		ledger = LedgerData.getInstance();
	}

	public static BalanceExecutor getInstance() {
		if(executor == null)
			executor = new BalanceExecutor();

		return executor;
	}

	@Override
	public Result execute(RequestParameters parameters) {
		AccountDetails account = ledger.accessAccount(parameters.getName(), parameters.getBankName());
		if(account==null)
			return null;

		TreeSet<AccountDetails.LumpSum> lumpSums = account.getLumpSums();

		float amountPaid = 0;
		for(AccountDetails.LumpSum lumpSum: lumpSums){
			if(lumpSum.prevEmiNo > parameters.getEmiNo())
				break;
			amountPaid += lumpSum.amount;
		}
		amountPaid += parameters.getEmiNo()*account.getEmiAmount();
		float amountLeft = account.getTotalAmount() - amountPaid;
		Integer emiLeft = (int)Math.ceil(amountLeft/account.getEmiAmount());

		Result result = new Result();
		result.setName(account.getName());
		result.setBankName(account.getBankName());
		result.setAmountPaid(amountPaid>account.getTotalAmount()?account.getTotalAmount():amountPaid);
		result.setEmiLeft(emiLeft);

		return result;
	}
}
