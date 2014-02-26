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
   * This will take a line and return a double array of processing unit values
   * positive processing units for beliefs are even in the csv and 
   * 
   * @return
   */
  public double[] readProcessingUnitsCSV(String positiveOrNegativeBank) {
    //
    double[] valenceBankValues = new double[(TestAgent.getTotalNumberOfProcessingUnits())/2];

    if (positiveOrNegativeBank == "positive") {
      for (int i = 1; i < valenceBankValues.length; i = i + 2) {
        
      }
    }
    return null;

  }

  /**
   * This will see how many lines are in the csv initialization file this will determine how many
   * agents to make we can do this by looping through the csv while there is a next entry to create
   * agents
   * 
   * @throws IOException
   */

  /**
   * each unit determines its net input based on external input to the unit and activations of all
   * the units at the end of the preceding tick modulated by the weight coefficients
   * 
   * @throws IOException
   */
  public double calculateInputs() {


    return 0;

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

    while ((nextLine = reader.readNext()) != null) {
      // njareextLine[] is an array of values from the line

      TestAgent agent = new TestAgent();
      agent.initializeFromCSV(TestAgent.getTotalNumberOfProcessingUnits());

      for (String string : nextLine) {
        System.out.print(string + ", ");
      }
      System.out.print('\n');

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
