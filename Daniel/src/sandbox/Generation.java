package sandbox;

public class Generation {

  public Generation() {

  }

  /**
   * @param puIndex index of the processing unit that needs a weight array generated.
   * @param numberOfPUInEachVB number of processing units in each valence bank
   * @param VBWeights is the array that contains all the weights (from the csv file)
   * @return arrayOfWeights returns the weights that are the inputs for the processing unit
   */
  public double[][] generateValenceBankWeightArrays(int puIndex, int numberOfPUInEachVB,
      double[] vbWeights) {
    double[][] arrayOfWeights = new double[numberOfPUInEachVB][numberOfPUInEachVB];
    int vbWeighti = 0;
    int nMinusIndex = 0;
    int totalSkip = 0;
    for (int processingUnit = 0; processingUnit < numberOfPUInEachVB; processingUnit++) {
      nMinusIndex = numberOfPUInEachVB - processingUnit;
      for (int i = 0; i < numberOfPUInEachVB; i++) {
        if (i == processingUnit) {
          arrayOfWeights[processingUnit][i] = 0;
          System.out.println("PU #" + processingUnit + "; Index #" + i + ": "
              + arrayOfWeights[processingUnit][i]);
        } else {
          if (i < processingUnit) {
            arrayOfWeights[processingUnit][i] = arrayOfWeights[i][processingUnit];
            System.out.println("PU #" + processingUnit + "; Index #" + i + ": "
                + arrayOfWeights[processingUnit][i]);
          } else {
            arrayOfWeights[processingUnit][i] = vbWeighti;
            System.out.println("PU #" + processingUnit + "; Index #" + i + ": "
                + arrayOfWeights[processingUnit][i]);
            vbWeighti++;
          }
        }
      }
      totalSkip = totalSkip + nMinusIndex;
    }
    return arrayOfWeights;
  }
}
