/**
 * 
 */
package sandbox;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;


/**
 * Think of this as the context builder in REPAST
 * 
 * @author dchen
 * 
 */
public class Test {

  /**
   * Method will initialize x number of agents, depending on how many non empty rows are in the csv
   * file
   * 
   * @throws IOException
   */
  public static void createAgentsFromCSV() throws IOException {

    // read in csv, commas as delimiters between double-quoted strings, skips first line (headers),
    CSVReader reader = new CSVReader(new FileReader(CFG.PROCESSING_UNIT_VALUES_CSV), ',', '\"', 1);

    String[] nextLine;

    int i = 0;
    while ((nextLine = reader.readNext()) != null) {
      // Initialization initialization = new Initialization();

      i++;
      // nextLine[] is an array of values from the line

      TestAgent testAgent = new TestAgent();
      // initialization.initializeProcessingUnitsFromCSV();

      // there is no agent 0, i has already been incremented so agents start with 1,
      // this will match the agent numbering in the CSV file
      testAgent.setAgentID(i);

      // hard code agents into agent 1
      if (testAgent.getAgentID() == 1) {
        testAgent.agentsWhoInfluenceMe.add(2);
      }

      // make sure the agent # assigned == agent # from csv
      int agentNumber = Integer.parseInt(nextLine[0]);
      System.out.println(testAgent.getAgentID() + " " + Integer.parseInt(nextLine[0]));
      if (testAgent.getAgentID() == agentNumber) {
        System.out.println("pass");
      }
      // why is this assert statement not doing its job?!?!?
      assert (testAgent.getAgentID() == agentNumber);

      // assign the value array into each agent
      System.out.print("From Test class: ");
      for (String string : nextLine) {
        System.out.print(string + ", ");
      }
      System.out.println("");
      String[] values = Arrays.copyOfRange(nextLine, 1, nextLine.length); 
      for (String string : values){
        double value = Double.parseDouble(string);
        System.out.println(value);
      }

      // initialization.initializeValenceBanks(nextLine);

      System.out.print("Agents who influence me: ");
      for (Integer agentWhoInfluencesMe : testAgent.agentsWhoInfluenceMe) {
        System.out.print(agentWhoInfluencesMe + ", ");
      }
      System.out.println("\n");
    }
    reader.close();
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    // // setting up the log file
    // WriteToFile log = null;
    // TestAgent.setInitializationLog("./src/sandbox/testLog.log");
    // try {
    // log = new WriteToFile(TestAgent.getInitializationLog());
    //
    // // write test line to file
    // log.write("hello!");
    //
    // // write working directory
    // log.write("current working directory: " + System.getProperty("user.dir"));
    //
    // } catch (IOException e1) {
    // e1.printStackTrace();
    // }
    //
    // TestAgent initAgent = new TestAgent();
    // Calculation initCalc = new Calculation(initAgent);
    // Initialization initInit = new Initialization(initAgent);
    //
    // // TestAgent initializeClassVariables = new TestAgent();
    // // this sets where the csv file for the process unit values are
    // TestAgent.setProcessingUnitCSV("./src/sandbox/AgentProcessigUnitValues.csv");
    //
    // try {
    // // count number of processing units in file, this will later initialize the agent PU arrays
    // TestAgent.setTotalNumberOfProcessingUnits(initInit.countTotalNumberOfProcessingUnits());
    // log.write("Number of processing units: " + TestAgent.getTotalNumberOfProcessingUnits());
    //
    // // count number of weights on each valence bank: NUMBEROFPROCESSINGUNITS choose 2
    // TestAgent.setTotalNumberOfProcessingUnitWeights(initCalc
    // .calculateNumberOfWeightsOnEachValenceBank(TestAgent.getTotalNumberOfProcessingUnits()));
    // log.write("Number of weights on each valence bank: "
    // + TestAgent.getTotalNumberOfProcessingUnitWeights());
    //
    // // create and initialize agents from CSV
    // TestAgent.createAgentsFromCSV();
    // log.write("Agent loaded from CSV files");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    //
    //
    // double valenceInput =
    // initCalc.calculateValenceBankInput(initAgent.testPosValBank,
    // initAgent.testPosValBankWeights);
    //
    // double oppositeInput = initCalc.calculateOppositeProcessingUnit(.2);
    //
    // double[] allCorresponding = new double[] {.2};
    //
    // double correspondingInput = initCalc.calculateCorrespondingProcessingUnit(allCorresponding);
    //
    // // System.out.println(correspondingInput);
    // double totalInput = valenceInput + oppositeInput + correspondingInput;
    //
    // System.out.println(initCalc.convertToLogit(totalInput));
    //
    // try {
    // initInit.initializeWeightsFromCSV("AgentProcessigUnitWeights.csv");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    //
    // System.out.println("DONE");

    createAgentsFromCSV();
  }


}
