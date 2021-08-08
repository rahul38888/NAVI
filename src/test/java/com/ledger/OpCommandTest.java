package com.ledger;

import org.junit.Test;
import org.junit.Assert;

public class OpCommandTest {

	@Test
	public void testGetByCommandString(){
		Assert.assertEquals(OpCommand.LOAN, OpCommand.getByCommandString("LOAN"));
		Assert.assertEquals(OpCommand.BALANCE, OpCommand.getByCommandString("BALANCE"));
		Assert.assertEquals(OpCommand.PAYMENT, OpCommand.getByCommandString("PAYMENT"));
		Assert.assertNull(OpCommand.getByCommandString("Other Strings"));
		Assert.assertNull(OpCommand.getByCommandString(""));
		Assert.assertNull(OpCommand.getByCommandString(null));
	}

}
