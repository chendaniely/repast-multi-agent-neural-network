package sandbox;

import java.util.Arrays;



public class Generation {

  public Generation() {

  }

  /**
   * This method will take an array of valence bank weights (usually from a csv file) and return a
   * 2D array that represents the weights between any 2 processing units on the same valence bank
   * 
   * @param VBWeights is the array that contains all the weights (from the csv file)
   * @return arrayOfWeights returns a 2D array that is the weight between any 2 processing units
   */
  public double[][] generateValenceBankWeightArrays(double[] vbWeights) {
    int numberOfPUInEachVB = vbWeights.length / 2;
    double[][] arrayOfWeights = new double[numberOfPUInEachVB][numberOfPUInEachVB];
    int vbWeighti = 0;
    int nMinusIndex = 0;
    int totalSkip = 0;
    for (int processingUnit = 0; processingUnit < numberOfPUInEachVB; processingUnit++) {
      nMinusIndex = numberOfPUInEachVB - processingUnit;
      for (int i = 0; i < numberOfPUInEachVB; i++) {
        if (i == processingUnit) {
          arrayOfWeights[processingUnit][i] = 0;
        } else {
          if (i < processingUnit) {
            arrayOfWeights[processingUnit][i] = arrayOfWeights[i][processingUnit];
          } else {
            arrayOfWeights[processingUnit][i] = vbWeights[vbWeighti];
            vbWeighti++;
          }
        }
      }
      totalSkip = totalSkip + nMinusIndex;
    }
    System.out.println(Arrays.deepToString(arrayOfWeights));
    return arrayOfWeights;
  }
}
