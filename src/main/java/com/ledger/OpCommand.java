package com.ledger;

import com.ledger.executers.BalanceExecutor;
import com.ledger.executers.Executor;
import com.ledger.executers.LoanExecutor;
import com.ledger.executers.PaymentExecutor;
import com.ledger.pojo.Result;
import com.ledger.pojo.RequestParameters;

/**
 *
 * @author rahul38888
 */
public enum OpCommand {
	LOAN("LOAN", LoanExecutor.getInstance()),
	PAYMENT("PAYMENT", PaymentExecutor.getInstance()),
	BALANCE("BALANCE", BalanceExecutor.getInstance());

	private final String commandString;
	private final Executor executor;

	OpCommand(String commandString, Executor executor){
		this.commandString = commandString;
		this.executor = executor;
	}

	public String getCommandString() {
		return commandString;
	}

	public Executor getExecutor() {
		return executor;
	}

	public Result execute(RequestParameters parameters){
		return executor.execute(parameters);
	}

	static public OpCommand getByCommandString(String commandString) {
		if(commandString == null) return null;

		try{
			return OpCommand.valueOf(commandString);
		}
		catch (IllegalArgumentException e){
			return null;
		}
	}


}
