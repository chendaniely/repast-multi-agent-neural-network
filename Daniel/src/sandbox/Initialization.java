package sandbox;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;

public class Initialization {

  TestAgent testAgent = null;

  public Initialization() {

  }

  public Initialization(TestAgent testAgent) {
    this.testAgent = testAgent;
  }

  /**
   * take in the csv for weights and then return the weights
   * 
   * @throws IOException
   */
  public double[][] initializeWeightsFromCSV(int agentNumber, String csvWeightFile)
      throws IOException {
    CSVReader reader = new CSVReader(new FileReader(csvWeightFile));
    String[] nextLine;
    double[] arrayOfWeightsDouble = null;
    int lineInCSV = 0;
    while ((nextLine = reader.readNext()) != null) {
      if (lineInCSV == 0) {
        lineInCSV++;
        continue;
      } else {
        // System.out.println(nextLine[0] + nextLine[1] + "etc...");
        // System.out.println(nextLine[0]);
        if (Integer.parseInt(nextLine[0]) == agentNumber) {
          String[] arrayOfWeights = Arrays.copyOfRange(nextLine, 1, nextLine.length);
          arrayOfWeightsDouble = new double[arrayOfWeights.length];
          for (int i = 0; i < arrayOfWeights.length; i++) {
            Double doubleVal = Double.parseDouble(arrayOfWeights[i]);
            arrayOfWeightsDouble[i] = doubleVal;
          }
          break;
        }
        lineInCSV++;
      }
    }
    Generation generate2dArray = new Generation();
    double[][] generated2dArray =
        generate2dArray.generateValenceBankWeightArrays(arrayOfWeightsDouble);
    return generated2dArray;
  }

  // TODO generate method that will dynamically create what the value and weight arrays will be
  /**
   * takes in an int (that corresponds to the array index) takes the list of weights, and generates
   * a new list with the corresponding weights
   */

  /**
   * This will take a line and return a double array of processing unit values positive processing
   * units for beliefs are odd indices in the csv and begin with 1 even processing units for beliefs
   * are even indices in the csv and begin with 2, 0 is the agent number row and is skipped
   * 
   * @return
   */
  public void initializeValenceBanks(String[] nextLine) {
    System.out.print("\nFrom TestAgent Class: ");
    System.out.print(Arrays.toString(nextLine));

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
    }
  }



}
