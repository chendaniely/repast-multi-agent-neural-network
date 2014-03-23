package sandbox;

import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public class Initialization {

  TestAgent testAgent = null;

  public Initialization() {

  }

  public Initialization(TestAgent testAgent) {
    this.testAgent = testAgent;
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
