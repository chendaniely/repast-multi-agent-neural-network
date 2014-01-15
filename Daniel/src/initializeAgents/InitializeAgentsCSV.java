/**
 * 
 */
package initializeAgents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import reader.ReadExcelPoi;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.grid.Grid;
import writer.WriteToCSV;

/**
 * @author dchen
 * 
 */
public class InitializeAgentsCSV extends InitializeAgents {

  // constructor based on CSV input
  public InitializeAgentsCSV(ContinuousSpace<Object> space, Grid<Object> grid, int agentNumber,
      String variableWeightFilesName) {
    ReadExcelPoi readInVariableWeights = new ReadExcelPoi();
    Hashtable hashtableFromXls = new Hashtable();

    ArrayList<Double> agentVariableWeights =
        readInVariableWeights
            .ReadExcelFile(variableWeightFilesName, agentVariableList, agentNumber);

    for (Double number : agentVariableWeights) {
      if (neuralNetworkABM.GlobalSettings.DEBUG)
        System.out.println("for loop in constructor for each double in list:" + number);
    }
    this.agentVariableList = agentVariableWeights;

    String[] arrayOfHeaderNames =
        {"time", "agent 1", "agent 1 value", "--> agent 2", "agent 2 value", "difference",
            "value change", "new value"};
    try {
      @SuppressWarnings("unused")
      WriteToCSV writeCSV =
          new WriteToCSV(neuralNetworkABM.GlobalSettings.agentWeightValues, arrayOfHeaderNames,
              false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
