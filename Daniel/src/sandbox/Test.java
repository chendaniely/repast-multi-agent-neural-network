/**
 * 
 */
package sandbox;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;


/**
 * Think of this as the context builder in REPAST
 * 
 * @author dchen
 * 
 */
public class Test {
  public static double[] convertStringArrayToDouble(String[] stringArray) {
    double[] doubleArray = new double[stringArray.length];
    for (int i = 0; i < stringArray.length; i++) {
      doubleArray[i] = Double.parseDouble(stringArray[i]);
    }
    return doubleArray;
  }

  /**
   * Method will initialize x number of agents, depending on how many non empty rows are in the csv
   * file
   * 
   * @throws IOException
   */
  public static ArrayList<TestAgent> createAgentsFromCSV() throws IOException {
    // create container for agents to reside in
    ArrayList<TestAgent> agents = new ArrayList<TestAgent>();

    // read in csv, commas as delimiters between double-quoted strings, skips first line (headers),
    CSVReader value = new CSVReader(new FileReader(CFG.PROCESSING_UNIT_VALUES_CSV), ',', '\"', 1);

    // read in csv, commas as delimiters between double-quoted strings, skips first line (headers),
    CSVReader weight = new CSVReader(new FileReader(CFG.PROCESSING_UNIT_WEIGHTS_CSV), ',', '\"', 1);

    // nextLine[] is an array of values from the line
    String[] nextValueLine;
    String[] nextWeightLine;

    int agendID = 0;
    while ((nextValueLine = value.readNext()) != null) {
      agendID++;

      TestAgent testAgent = new TestAgent();

      // there is no agent 0, i has already been incremented so agents start with 1,
      // this will match the agent numbering in the CSV file
      testAgent.setAgentID(agendID);

      // TODO create method that will create network from edgelist
      // hard code agents into agent 1
      if (testAgent.getAgentID() == 1) {
        testAgent.agentsWhoInfluenceMe.add(2);
      }

      // make sure the agent # assigned == agent # from csv
      int agentNumber = Integer.parseInt(nextValueLine[0]);
      System.out.println(testAgent.getAgentID() + " " + Integer.parseInt(nextValueLine[0]));

      // why is this assert statement not doing its job?!?!?
      System.out.println(testAgent.getAgentID() == agentNumber);
      assert (testAgent.getAgentID() == agentNumber);

      // assign the value array into each agent
      String[] values = Arrays.copyOfRange(nextValueLine, 1, nextValueLine.length);

      double[] valuesD = new double[values.length];
      for (int i = 0; i < valuesD.length; i++) {
        valuesD[i] = Double.parseDouble(values[i]);
      }

      System.out.println("values read");
      System.out.println(Arrays.toString(valuesD));

      Initialization initializeAgents = new Initialization();
      testAgent.setProcessingUnitActivationValues(initializeAgents.initializeVBActivation(valuesD));

      while ((nextWeightLine = weight.readNext()) != null) {
        if (Double.parseDouble(nextWeightLine[0]) == agendID) {
          System.out.println("hello");
          System.out.println(Arrays.deepToString(initializeAgents.initializeVBWeights(agendID,
              convertStringArrayToDouble(nextWeightLine))));
        }
      }

      agents.add(testAgent);
    }
    value.close();
    weight.close();

    return agents;
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    @SuppressWarnings("unused")
    ArrayList<TestAgent> agents = new ArrayList<TestAgent>();
    agents = createAgentsFromCSV();
  }


}
