package sandbox;

public class Calculation {
  
  /**
   * this will take doubles from each of the different forms of input, and calculate/return output
   * 
   * @param sameBank
   * @param oppositeProcessingUnit
   * @param correspondingAgent
   * @return
   */
  public double calculateOutputFromInputs(double sameBank, double oppositeProcessingUnit,
      double correspondingAgent) {
    double output = 0;

    return output;

  }
  
  /**
   * takes 2 lists, first are the values of the same valence bank
   * second is a list of weights between each valence bank
   * this method is also hard coded (for the time being)
   * @return
   */
  public double calculateValenceBankInput(double[] values, double[] weights) {
    double input = .1;

    for (int i = 0; i < totalNumberOfProcessingUnits / numberOfValenceBanks - 1; i++) {
      // minus one is becuase we do not need to count the processing unit's weight to itself
      System.out.println("index: " + i);
      System.out.println("input: " + input);
      System.out.println("value: " + values[i] + " weight: " + weights[i]);
      input += (values[i] * weights[i]);
      System.out.println(input);
      
    }
    return input;
  }
  
  public double calculateOppositeProcessingUnit(double input){
    System.out.println("opposite processing unit: " + input);
    input = input*-.2;
    
    System.out.println("final input from Opposite Processing unit: " + input);
    return input;
  }
  
  /**
   * takes in an array of all the corresponding inputs,
   * sums them, mult by 5
   * @param input
   * @return
   */
  public double calculateCorrespondingProcessingUnit(double[] input){
    double output = 0;
    double total = 0;
    for (int i = 0; i < input.length; i++){
      total += input[i];
    }
    output = total * .5;
    return output;
  }
  
  public double convertToLogit(double input){
    input =  (1.0 / (1 + Math.exp(input)));
    
    return input;
    
  }

}
