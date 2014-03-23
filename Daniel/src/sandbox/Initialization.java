package sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import au.com.bytecode.opencsv.CSVReader;

public class Initialization {

  TestAgent testAgent = null;

  public Initialization() {

  }

  public Initialization(TestAgent testAgent) {
    this.testAgent = testAgent;
  }

  // TODO might move this to calculate
  /**
   * read in the first line of the file to determine how many processing units each module (agent)
   * will have, this includes both the positive and negative processing units for each belief the
   * method determines the total number of processing units by counting the number of commas in the
   * first line (aka the line of variables)
   * 
   * @throws IOException
   */
  public int countTotalNumberOfProcessingUnits(String csv) throws IOException {

    BufferedReader br = null;

    br = new BufferedReader(new FileReader(csv));

    String fLine = br.readLine();

    br.close();

    // System.out.println("first lines: " + fLine);

    // counts number of commas, this will return the number of columns - 1, this should represent
    // the number of processing units in our csv file
    int totalNumberOfProcessingUnits = StringUtils.countMatches(fLine, ",");

    // System.out.println(totalNumberOfProcessingUnits);

    // log.write(numberOfProcessingUnits);

    return totalNumberOfProcessingUnits;

  }

  // TODO might move this to calculate
  /**
   * This method will take in the totalNumberOfProcessingUnits and number of valenceOfValenceBanks
   * and return an int that will represent the number of elements for the 8 arrays used in the
   * agents
   * 
   * @return numberOfProcessingUnitsForEachArray
   * @param totalNumberOfProcessingUnits
   * @param numberOfValenceBanks
   */
  public int initializeProcessingUnitsFromCSV(int totalNumberOfProcessingUnits,
      int numberOfValenceBanks) {
    int numberOfProcessingUnitsForEachArray = totalNumberOfProcessingUnits / numberOfValenceBanks;
    // System.out.println(calculation);
    return numberOfProcessingUnitsForEachArray;

    // testAgent.positiveInputProcessingUnitsT0 = new double[calculation];
    // testAgent.negativeInputProcessingUnitsT0 = new double[calculation];
    //
    // testAgent.positiveInputProcessingUnitsT_1 = new double[calculation];
    // testAgent.negativeInputProcessingUnitsT_1 = new double[calculation];
    //
    // testAgent.positiveOutputProcessingUnitsT0 = new double[calculation];
    // testAgent.negativeOutputProcessingUnitsT0 = new double[calculation];
    //
    // testAgent.positiveOutputProcessingUnitsT_1 = new double[calculation];
    // testAgent.negativeOutputProcessingUnitsT_1 = new double[calculation];

    // System.out.println("hello");
  }

  /**
   * take in the csv for weights and then return the weights this method is hardcoded
   * 
   * @throws IOException
   */
  public void initializeWeightsFromCSV(String csvWeightFile) throws IOException {
    // int bankConnections = 10;
    // positiveWeightProcessingUnit = new double[] {-1.0, -0.8, -0.6, -0.4, // 0
    // -0.2, 0.3, 0.5, // 1
    // 0.7, 0.9, // 2
    // 1.0}; // 3
    CSVReader reader = new CSVReader(new FileReader(csvWeightFile));
    String[] nextLine;
    int i = 0;
    // find the line of interest that represents the current agent's weight values
    while ((nextLine = reader.readNext()) != null) {
      System.out.println(nextLine[0] + nextLine[1] + "etc...");
    }

  }

  // TODO generate method that will dynamically create what the value and weight arrays will be
  /**
   * takes in an int (that corresponds to the array index) takes the list of weights, and generates
   * a new list with the corresponding weights
   */

  /**
   * This will take a line and return a double array of processing unit values positive processing
   * units for beliefs are odd indicies in the csv and begin with 1 even processing units for
   * beliefs are even indicies in the csv and begin with 2, 0 is the agent number row and is skipped
   * 
   * @return
   */
  public void initializeValenceBanks(String[] nextLine) {

    /* @formatter:off */
    System.out.print("\nFrom TestAgent Class: ");   //
    for (String string : nextLine) {                // Print statements
      System.out.print(string + ", ");              //
    }                                               //
    System.out.print('\n');                         //
    /* @formatter:on */

    int j = 0;
    // skip the first index
    for (int i = 1; i < nextLine.length; i++) {
      Double doubleOfStringValue = Double.valueOf(nextLine[i]);

      // the index is odd (positive valence bank)
      if ((i % 2) == 1) {
        testAgent.positiveInputProcessingUnitsT_1[j] = doubleOfStringValue;
      }

      // the index is even (negative valence bank)
      if ((i % 2) == 0) {
        testAgent.negativeInputProcessingUnitsT_1[j] = doubleOfStringValue;
        j++;
      }

    }
    for (int i = 0; i < (TestAgent.getTotalNumberOfProcessingUnits() / TestAgent
        .getNumberOfValenceBanks()); i++) {
      System.out.println("+ " + testAgent.positiveInputProcessingUnitsT_1[i] + " | - "
          + testAgent.negativeInputProcessingUnitsT_1[i]);
    } //
  }



}
