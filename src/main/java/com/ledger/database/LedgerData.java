package com.ledger.database;

import com.ledger.pojo.AccountDetails;

import java.util.HashMap;
import java.util.Map;

public class LedgerData {

	private static LedgerData ledgerData;
	private Map<String, AccountDetails> accountsData = new HashMap<>();

	private LedgerData(){}

	public static LedgerData getInstance() {
		if(ledgerData == null)
			ledgerData = new LedgerData();
		return ledgerData;
	}

	public void addNewAccount(AccountDetails account){
		String key = getKey(account.getName(), account.getBankName());
		accountsData.put(key, account);
	}

	public AccountDetails accessAccount(String name, String bankName){
		String key = getKey(name, bankName);

		return accountsData.get(key);
	}

	public String getKey(String name, String bankName){
		return name + "##" + bankName;
	}
}
