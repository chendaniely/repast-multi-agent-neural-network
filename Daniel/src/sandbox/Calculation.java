package sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.ArithmeticUtils;

public class Calculation {

  TestAgent testAgent = null;

  public Calculation() {}

  public Calculation(TestAgent testAgent) {
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
  public int calculateTotalNumberOfProcessingUnits(String csv) throws IOException {

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
  public int calculateNumberOfProcessingUnitsForEachArray(int totalNumberOfProcessingUnits,
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
