/**
 * 
 */
package sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.ArithmeticUtils;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Think of this as the context builder in REPAST
 * 
 * @author dchen
 * 
 */
public class Test {

  // private String logDir = null;

  /**
   * read in the first line of the file to determine how many processing units each module (agent)
   * will have, this includes both the positive and negative processing units for each belief
   * 
   * @throws IOException
   */
  public static int countTotalNumberOfProcessingUnits() throws IOException {

    BufferedReader br = null;

    br = new BufferedReader(new FileReader(TestAgent.getProcessingUnitCSV()));

    String fLine = br.readLine();

    br.close();

    // System.out.println("first lines: " + fLine);

    // counts number of commas, this will return the number of columns that is not the first row
    // in our case, this should represent the number of processing units in our csv file
    int totalNumberOfProcessingUnits = StringUtils.countMatches(fLine, ",");

    // System.out.println(numberOfProcessingUnits);

    // log.write(numberOfProcessingUnits);

    return totalNumberOfProcessingUnits;

  }

  /**
   * Example: for 5 valence banks, it will calculate the combinations of 5C2 = 4+3+2+1 = 10 10
   * choose 2 = 45
   * 
   * @return
   */
  public static int calculateNumberOfWeightsOnEachValenceBank(int totalProcessingUnits) {

    long numberOfWeightsOnEachValenceBank =
        ArithmeticUtils.binomialCoefficient(totalProcessingUnits, 2);

    // System.out.println(numberOfWeightsOnEachValenceBank);

    return (int) numberOfWeightsOnEachValenceBank;

  }


  /**
   * Method will initialize x number of agents, depending on how many non empty rows are in the csv
   * file
   * 
   * @throws IOException
   */
  public static void initializeAgentsFromCSV() throws IOException {

    // read in csv, skips first line (headers), commas as delimiters between double-quoted strings
    CSVReader reader =
        new CSVReader(new FileReader(TestAgent.getProcessingUnitCSV()), ',', '\"', 1);

    String[] nextLine;

    int i = 0;
    while ((nextLine = reader.readNext()) != null) {
      i++;
      // nextLine[] is an array of values from the line

      TestAgent agent = new TestAgent();
      agent.initializeFromCSV();
      agent.setAgentID(i);

      // hard code agents into agent 1
      if (agent.getAgentID() == 1) {
        agent.agentsWhoInfluenceMe.add(2);
      }


      /* @formatter:off */
      System.out.print("From Test class:      ");                       //
      for (String string : nextLine) {                                  // Print Statements
        System.out.print(string + ", ");                                //
      }                                                                 //
      /* @formatter:on */

      agent.initializeValenceBanks(nextLine);

      System.out.print("Agents who influence me: ");
      for (Integer agentWhoInfluencesMe : agent.agentsWhoInfluenceMe) {
        System.out.print(agentWhoInfluencesMe + ", ");
      }
      System.out.println("\n");
    }
    reader.close();
  }



  /**
   * @param args
   */
  public static void main(String[] args) {

    // setting up the log file
    WriteToFile log = null;
    TestAgent.setInitializationLog("./src/sandbox/testLog.log");
    try {
      log = new WriteToFile(TestAgent.getInitializationLog());
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // write test line to file
    try {
      log.write("hello!");
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // print working directory
    try {
      log.write("current working directory: " + System.getProperty("user.dir"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // TestAgent initializeClassVariables = new TestAgent();
    // this sets where the csv file for the process unit values are
    TestAgent.setProcessingUnitCSV("./src/sandbox/AgentProcessigUnitValues.csv");

    // count number of processing units in file, this will later initialize the agent arrays
    try {
      TestAgent.setTotalNumberOfProcessingUnits(countTotalNumberOfProcessingUnits());
      log.write("Number of processing units: " + TestAgent.getTotalNumberOfProcessingUnits());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // count number of weights on each valence bank: NUMBEROFPROCESSINGUNITS choose 2
    try {
      TestAgent
          .setTotalNumberOfProcessingUnitWeights(calculateNumberOfWeightsOnEachValenceBank(TestAgent
              .getTotalNumberOfProcessingUnits()));
      log.write("Number of weights on each valence bank: "
          + TestAgent.getTotalNumberOfProcessingUnitWeights());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // create and initialize agents from CSV
    try {
      initializeAgentsFromCSV();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.out.println("DONE");
  }


}
