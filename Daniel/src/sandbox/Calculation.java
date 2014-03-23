package sandbox;

import org.apache.commons.math3.util.ArithmeticUtils;

public class Calculation {

  TestAgent testAgent = null;

  public Calculation() {}

  public Calculation(TestAgent testAgent) {
    this.testAgent = testAgent;
  }

  /**
   * takes in an int value for total # of processing units in each bank this will be calculated via
   * nCr Example: for 5 PU's in each valence banks, it will calculate the combinations of 5C2 =
   * 4+3+2+1 = 10 10 choose 2 = 45
   * 
   * @return int that represents the number of weights
   */
  public int calculateNumberOfWeightsOnEachValenceBank(int numberProcessingUnitsEachBank) {
    long numberOfWeightsOnEachValenceBank =
        ArithmeticUtils.binomialCoefficient(numberProcessingUnitsEachBank, 2);
    return (int) numberOfWeightsOnEachValenceBank;

  }

  /**
   * this will take doubles from each of the different forms of input, and calculate/return output
   * it will sum up the inputs and do a logit transform
   * 
   * @param sameBank
   * @param oppositeProcessingUnit
   * @param correspondingAgent
   * @return
   */
  public double calculateOutputFromInputs(double sameBank, double oppositeProcessingUnit,
      double correspondingAgent) {
    double output = sameBank + oppositeProcessingUnit + correspondingAgent;
    return convertToLogit(output);

  }

  /**
   * takes 2 lists, first are the values of the same valence bank second is a list of weights
   * between each valence bank this method is also hard coded (for the time being)
   * 
   * @return
   */
  public double calculateValenceBankInput(double[] values, double[] weights) {
    double input = 0;
    /*
     * for (int i = 0; i < TestAgent.getTotalNumberOfProcessingUnits() /
     * TestAgent.getNumberOfValenceBanks() - 1; i++) {
     * 
     * // minus one is because we do not need to count the processing unit's weight to itself
     * System.out.println("index: " + i); System.out.println("input: " + input);
     * System.out.println("value: " + values[i] + " weight: " + weights[i]); input += (values[i] *
     * weights[i]); System.out.println(input);
     * 
     * }
     */
    return input;
  }

  /**
   * takes a double that represents the processing unit of the opposite valence bank multiplies the
   * input by the weight returns weight * oppositePUValue
   * 
   * @param input
   * @return
   */
  public double calculateOppositeProcessingUnit(double input) {
    input = input * CFG.OPPOSITE_PROCESSING_UNIT_WEIGHT;
    return input;
  }

  /**
   * takes in an array of all the corresponding inputs (PU from other agents that are influencing
   * current agent downstream --> me) , sums them, then multiply by corresponding PU weight
   * 
   * @param input
   * @return
   */
  public double calculateCorrespondingProcessingUnit(double[] input) {
    double output = 0;
    double total = 0;
    for (int i = 0; i < input.length; i++) {
      total += input[i];
    }
    output = total * CFG.CORRESPONDING_PROCESSING_UNIT_WEIGHT;
    return output;
  }

  public double convertToLogit(double input) {
    input = (1.0 / (1 + Math.exp(input)));
    return input;
  }

}
