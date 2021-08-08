package com.ledger;

import com.ledger.database.LedgerData;
import com.ledger.executers.BalanceExecutor;
import com.ledger.executers.LoanExecutor;
import com.ledger.executers.PaymentExecutor;
import com.ledger.pojo.AccountDetails;
import com.ledger.pojo.RequestParameters;
import com.ledger.pojo.Result;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class ExecutorTest {

	static LoanExecutor loanExecutor;
	static PaymentExecutor paymentExecutor;
	static BalanceExecutor balanceExecutor;

	@BeforeClass
	public static void init(){
		loanExecutor = LoanExecutor.getInstance();
		paymentExecutor = PaymentExecutor.getInstance();
		balanceExecutor = BalanceExecutor.getInstance();
	}

	@Test
	public void testLoanExecutor(){

		Assert.assertNull(createLoanEntry());

		LedgerData ledger = LedgerData.getInstance();
		AccountDetails account = ledger.accessAccount("Raju", "Axis");

		Assert.assertNotNull(account);
		Assert.assertEquals("Raju", account.getName());
		Assert.assertEquals("Axis", account.getBankName());
		Assert.assertEquals(new Float(1000), account.getPrincipleAmount());
		Assert.assertEquals(new Integer(2), account.getYears());
		Assert.assertEquals(new Float(5), account.getRateOfInterest());
		Assert.assertEquals(new Float(46), account.getEmiAmount());
		Assert.assertEquals(0, account.getLumpSums().size());
	}

	@Test
	public void testPaymentExecutor(){
		Assert.assertNull(createLoanEntry());

		RequestParameters parameters = new RequestParameters();
		parameters.setName("Raju");
		parameters.setBankName("Axis");
		parameters.setLumpSumAmount(100F);
		parameters.setEmiNo(5);
		Assert.assertNull(paymentExecutor.execute(parameters));

		parameters.setLumpSumAmount(50F);
		parameters.setEmiNo(2);
		Assert.assertNull(paymentExecutor.execute(parameters));

		LedgerData ledger = LedgerData.getInstance();
		AccountDetails account = ledger.accessAccount("Raju", "Axis");

		TreeSet<AccountDetails.LumpSum> lumpSums = account.getLumpSums();
		Assert.assertEquals(2, lumpSums.size());

		Assert.assertEquals(new Integer(2), lumpSums.first().prevEmiNo);
		Assert.assertEquals(new Integer(5), lumpSums.last().prevEmiNo);
	}

	@Test
	public void testBalanceExecutor(){
		Assert.assertNull(createLoanEntry());

		RequestParameters parameters = new RequestParameters();
		parameters.setName("Raju");
		parameters.setBankName("Axis");
		parameters.setEmiNo(5);

		Result result = balanceExecutor.execute(parameters);
		Assert.assertNotNull(result);
		Assert.assertEquals(parameters.getName(), result.getName());
		Assert.assertEquals(parameters.getBankName(), result.getBankName());
		Assert.assertEquals(new Float(230), result.getAmountPaid());
		Assert.assertEquals(new Integer(19), result.getEmiLeft());

		RequestParameters paymentParameters = new RequestParameters();
		paymentParameters.setName("Raju");
		paymentParameters.setBankName("Axis");
		paymentParameters.setLumpSumAmount(100F);
		paymentParameters.setEmiNo(6);
		Assert.assertNull(paymentExecutor.execute(paymentParameters));

		result = balanceExecutor.execute(parameters);
		Assert.assertNotNull(result);
		Assert.assertEquals(new Float(230), result.getAmountPaid());
		Assert.assertEquals(new Integer(19), result.getEmiLeft());

		parameters.setEmiNo(7);
		result = balanceExecutor.execute(parameters);
		Assert.assertNotNull(result);
		Assert.assertEquals(new Float(422), result.getAmountPaid());
		Assert.assertEquals(new Integer(15), result.getEmiLeft());
	}

	private Result createLoanEntry(){
		RequestParameters parameters = new RequestParameters();
		parameters.setName("Raju");
		parameters.setBankName("Axis");
		parameters.setPrincipleAmount(1000F);
		parameters.setYears(2);
		parameters.setRateOfInterest(5F);
		return loanExecutor.execute(parameters);
	}
}
