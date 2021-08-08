package com.ledger;

import com.ledger.main.LedgerMain;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;

public class LedgerMainTest {

	@Test
	public void testInputsOne() throws IOException {
		String path = getClass().getClassLoader().getResource("input1.txt").getPath();
		testInputs(path, "output1.txt");
	}

	@Test
	public void testInputsTwo() throws IOException {
		String path = getClass().getClassLoader().getResource("input2.txt").getPath();
		testInputs(path, "output2.txt");
	}

	private void testInputs(String inputFilePath, String expectedOutputFilePath) throws IOException {
		String[] args = {inputFilePath};

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(stream);

		LedgerMain.main(args);

		System.setOut(old);

		String[] actualOutput = baos.toString().split("\n");

		List<String> expectedOutput = FileUtils.readLines(
				new File(getClass().getClassLoader().getResource(expectedOutputFilePath).getPath()));

		for(int i =0;i<actualOutput.length;i++)
			Assert.assertEquals(expectedOutput.get(i), actualOutput[i]);

	}
}
