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

  public static int[] convertStringArrayToInt(String[] stringArray) {
    int[] intArray = new int[stringArray.length];
    for (int i = 0; i < stringArray.length; i++) {
      intArray[i] = Integer.parseInt(stringArray[i]);
    }
    return intArray;
  }

  /**
   * Method will initialize x number of agents, depending on how many non empty rows are in the csv
   * file
   * 
   * @throws IOException
   * @return agents arrayList of agents that can be used to iterate later
   */
  public static ArrayList<TestAgent> createAgentsFromCSV() throws IOException {
    // create container for agents to reside in
    ArrayList<TestAgent> agents = new ArrayList<TestAgent>();

    // read in csv, commas as delimiters between double-quoted strings, skips first line (headers),
    CSVReader value = new CSVReader(new FileReader(CFG.PROCESSING_UNIT_VALUES_CSV), ',', '\"', 1);
    CSVReader weight = new CSVReader(new FileReader(CFG.PROCESSING_UNIT_WEIGHTS_CSV), ',', '\"', 1);
    CSVReader edge = new CSVReader(new FileReader(CFG.EDGE_LIST_CSV), ',', '\"', 1);

    // nextLine[] is an array of values from the line
    String[] nextValueLine;
    String[] nextWeightLine;
    String[] nextEdgeLine;

    int agendID = 0;
    while ((nextValueLine = value.readNext()) != null) {
      agendID++;

      TestAgent testAgent = new TestAgent();

      // there is no agent 0, i has already been incremented so agents start with 1,
      // this will match the agent numbering in the CSV file
      testAgent.setAgentID(agendID);

      // make sure the agent # assigned == agent # from csv
      int agentNumber = Integer.parseInt(nextValueLine[0]);
      System.out.println(testAgent.getAgentID() + " " + Integer.parseInt(nextValueLine[0]));

      // why is this assert statement not doing its job?!?!?
      System.out.println(testAgent.getAgentID() == agentNumber);
      assert (testAgent.getAgentID() == agentNumber);

      // assign the value array into each agent, note i only pass in the values here, no agentID
      String[] values = Arrays.copyOfRange(nextValueLine, 1, nextValueLine.length);

      System.out.println("values read");
      System.out.println(Arrays.toString(values));

      Initialization initializeAgents = new Initialization();

      // set the activation values
      testAgent.setProcessingUnitActivationValues(initializeAgents
          .initializeVBActivation(convertStringArrayToDouble(values)));

      while ((nextEdgeLine = edge.readNext()) != null) {
        boolean testEdge = Integer.parseInt(nextEdgeLine[0]) == agendID;
        System.out.println(nextEdgeLine[0] + " == " + Integer.toString(agendID) + " "
            + String.valueOf(testEdge));
        if (Integer.parseInt(nextEdgeLine[0]) == agendID) {
          // set agents who are connected to this.agent (referenced by id)
          testAgent.setAgentsWhoInfluenceMe(initializeAgents.initializeAgentsWhoInfluenceMe(
              agendID, convertStringArrayToInt(nextEdgeLine)));
          break;
        }
      }

      while ((nextWeightLine = weight.readNext()) != null) {
        boolean testWeight = Double.parseDouble(nextWeightLine[0]) == agendID;
        System.out.println(nextWeightLine[0] + " == " + Integer.toString(agendID) + " "
            + String.valueOf(testWeight));

        if (Double.parseDouble(nextWeightLine[0]) == agendID) {
          // set the processing unit weights
          testAgent.setProcessingUnitWeights(initializeAgents.initializeVBWeights(agendID,
              convertStringArrayToDouble(nextWeightLine)));
          break;
        }
      }
      for (int i = 0; i < testAgent.processingUnitActivationValues.length; i++) {
        for (int j = 0; j < testAgent.processingUnitActivationValues[i].length; j++) {
          testAgent.tempProcessingUnitActivationValues[i][j] =
              testAgent.processingUnitActivationValues[i][j];
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
    ArrayList<TestAgent> agents = createAgentsFromCSV();
    for (TestAgent agent : agents) {
      System.out.println(agent.getAgentID());
    }
    for (int i = 0; i < CFG.NUMBER_OF_TIME_TICKS; i++) {
      for (TestAgent agent : agents) {
        System.out.print(i + CFG.DEL + agent.getAgentID() + CFG.DEL);
        agent.step(i, agent.getAgentID(), agents);
      }
    }


  }
}
