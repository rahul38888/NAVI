package com.ledger.executers;

import com.ledger.database.LedgerData;
import com.ledger.pojo.AccountDetails;
import com.ledger.pojo.Result;
import com.ledger.pojo.RequestParameters;
import org.apache.commons.lang3.StringUtils;

public class LoanExecutor implements Executor {

	private static LoanExecutor executor;
	private static LedgerData ledger;

	private LoanExecutor(){
		super();
		ledger = LedgerData.getInstance();
	}

	public static LoanExecutor getInstance() {
		if(executor == null)
			executor = new LoanExecutor();

		return executor;
	}

	@Override
	public Result execute(RequestParameters parameters) {
		if(StringUtils.isEmpty(parameters.getName()) || StringUtils.isEmpty(parameters.getBankName()))
			return null;

		AccountDetails account = new AccountDetails(parameters);
		ledger.addNewAccount(account);

		return null;
	}

}
