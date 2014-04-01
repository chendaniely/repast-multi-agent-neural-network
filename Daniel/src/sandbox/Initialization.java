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
  public double[][] initializeVBWeightsFromCSV(int agentNumber, String csvWeightFile)
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

  /**
   * This will take a line and return a double array of processing unit values positive processing
   * units for beliefs are odd indices in the csv and begin with 1 even processing units for beliefs
   * are even indices in the csv and begin with 2, 0 is the agent number row and is skipped
   * 
   * @return valencebanksArr where the 0 index are the positive VB values, and the 1 index are the
   *         negative VB values
   */
  public double[][] initializeVBActivation(double[] values) {
    double[][] valencebanksArr = new double[2][values.length / 2];
    System.out.println("Initialization initializeValenceBanks");
    System.out.println(Arrays.toString(values));
    int j = 0;
    for (int i = 0; i < values.length; i++) {
      // the index is even (positive valence bank)
      if ((i % 2) == 0) {
        valencebanksArr[0][j] = values[i];
      }
      // the index is odd (negative valence bank)
      if ((i % 2) == 1) {
        valencebanksArr[1][j] = values[i];
        j++;
      }
    }
    System.out.println(Arrays.deepToString(valencebanksArr));
    return valencebanksArr;
  }
}
