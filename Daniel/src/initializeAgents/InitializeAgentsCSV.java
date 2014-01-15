/**
 * 
 */
package initializeAgents;

import java.io.IOException;
import java.util.ArrayList;

import reader.ReadExcelPoi;
import writer.WriteToCSV;

/**
 * @author dchen
 *
 */
public class InitializeAgentsCSV extends InitializeAgents {
	
	ReadExcelPoi readInVariableWeights = new ReadExcelPoi();

	ArrayList<Double> agentVariableWeights = readInVariableWeights.ReadExcelFile(
			variableWeightFilesName, agentVariableList, agentNumber);
	
	for (Double number : agentVariableWeights) {
		System.out.println("for loop in constructor for each double in list:" + number);
	}
	this.agentVariableList = agentVariableWeights;

	String[] arrayOfHeaderNames = { "time", "agent 1", "agent 1 value",
			"--> agent 2", "agent 2 value", "difference", "value change",
			"new value" };
	try {
		@SuppressWarnings("unused")
		WriteToCSV writeCSV = new WriteToCSV(
				neuralNetworkABM.GlobalSpaceConstant.agentWeightValues,
				arrayOfHeaderNames, false);
	} catch (IOException e) {
		e.printStackTrace();
	}

}
