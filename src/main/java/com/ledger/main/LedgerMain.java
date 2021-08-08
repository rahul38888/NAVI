package com.ledger.main;

import com.ledger.OpCommand;
import com.ledger.pojo.RequestParameters;
import com.ledger.pojo.Result;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author rahul38888
 */
public class LedgerMain {

	public static void main(String[] args) throws IOException {
		String inputFilePath = args[0];

		List<String> inputs = FileUtils.readLines(new File(inputFilePath));

		for (String input : inputs) {
			String[] inputValues = input.split(" ");
			if(inputValues.length < 4)
//				No command found
				continue;

			OpCommand command = OpCommand.getByCommandString(inputValues[0]);

			RequestParameters parameters = getParameters(inputValues, command);

			Result result = command.execute(parameters);

			printResult(result);
		}
	}

	private static void printResult(Result result){
		if(result==null)
			return;

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(result.getBankName()).append(" ").
				append(result.getName()).append(" ").
				append(result.getAmountPaid().intValue()).append(" ").
				append(result.getEmiLeft().intValue());

		System.out.println(stringBuffer.toString());
	}

	private static RequestParameters getParameters(String[] inputs, OpCommand command){
		RequestParameters parameters = new RequestParameters();
		parameters.setBankName(inputs[1]);
		parameters.setName(inputs[2]);
		switch (command){
			case LOAN:
				parameters.setPrincipleAmount(Float.parseFloat(inputs[3]));
				parameters.setYears(Integer.parseInt(inputs[4]));
				parameters.setRateOfInterest(Float.parseFloat(inputs[5]));
				break;
			case PAYMENT:
				parameters.setLumpSumAmount(Float.parseFloat(inputs[3]));
				parameters.setEmiNo(Integer.parseInt(inputs[4]));
				break;
			case BALANCE:
				parameters.setEmiNo(Integer.parseInt(inputs[3]));
				break;
			default:
				return null;
		}

		return parameters;
	}
}
