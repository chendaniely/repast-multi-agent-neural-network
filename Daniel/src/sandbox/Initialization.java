package sandbox;

import java.util.Arrays;

public class Initialization {

  public Initialization() {

  }

  /**
   * This will take a line and return a double array of processing unit values positive processing
   * units for beliefs are odd indices in the csv and begin with 1 even processing units for beliefs
   * are even indices in the csv and begin with 2, 0 is the agent number row and is skipped
   * 
   * @param values double array of activation values
   * @return valencebanksArr where the 0 index are the positive VB values, and the 1 index are the
   *         negative VB values
   */
  public double[][] initializeVBActivation(double[] values) {
    double[][] valencebanksArr = new double[2][values.length / 2];
    System.out.println("Initialization initializeValenceBanks");
    System.out.println("\tdouble values read in:\n\t" + Arrays.toString(values));
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
    System.out.println("\t2d array returned:\n\t" + Arrays.deepToString(valencebanksArr));
    return valencebanksArr;
  }

  /**
   * 
   * @param agentNumber int representing the agentID
   * @param vbWeights double array of weights (includes the agentID in the 0 index)
   * @return generated2dArray 2d array of the links between processing units
   */
  public double[][] initializeVBWeights(int agentNumber, double[] vbWeights) {
    System.out.println("Initialization initializeVBWeights");
    assert (vbWeights[0] == agentNumber);
    Generation generate2dArray = new Generation();
    double[] subArray = Arrays.copyOfRange(vbWeights, 1, vbWeights.length);
    double[][] generated2dArray = generate2dArray.generateValenceBankWeightArrays(subArray);

    System.out.println("\t2d weight returned:\n\t" + Arrays.deepToString(generated2dArray));
    return generated2dArray;
  }
}
