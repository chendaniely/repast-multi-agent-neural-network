package sandbox;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;

public class TestAgent {

  // Static class variables
  // file where the processing unit csv is
  private static String processingUnitCSV = null;

  // file where the agent log is
  private static String initializationLog = null;

  // this is the number of processing units read from the CSV file, it includes the positive +
  // negative processing units for each belief
  private static int totalNumberOfProcessingUnits = -1;

  // this represents the combination of processing units in each valence bank CHOOSE 2 (since each
  // processing unit will be connected to the other processing units in each valence bank)
  private static int totalNumberOfProcessingUnitWeights = -1;

  // number of valence banks in agent
  private static int numberOfValenceBanks = 2;

  // Member variables

  /* @formatter:off */
  // input
  double[] positiveInputProcessingUnitsT0   = null;
  double[] negativeInputProcessingUnitsT0   = null;
  double[] positiveInputProcessingUnitsT_1  = null;
  double[] negativeInputProcessingUnitsT_1  = null;

  // output
  double[] positiveOutputProcessingUnitsT0  = null;
  double[] negativeOutputProcessingUnitsT0  = null;
  double[] positiveOutputProcessingUnitsT_1 = null;
  double[] negativeOutputProcessingUnitsT_1 = null;

  // weights
  double[] positiveWeightProcessingUnit = null;
  double[] negativeWeightProcessingUnit = null;

  double weightBetweenValenceBanks = 0;

  double weightCorespondingModule = 0;

  private int agentID = 0;
  ArrayList<Integer> agentsWhoInfluenceMe = new ArrayList<Integer>();
  /* @formatter:on */
  
  // test cases
  double testPosProssUnit1 = .1;
  double[] testPosValBank = new double[] {.3, .5, .7, .9};
  double[] testPosValBankWeights = new double[] {-1.0, -.8, -.6, -.4};


  // Constructor(s)
  public TestAgent() {
    /* @formatter:off */
    System.out.println("totalNumberOfProcessingUnits: " + totalNumberOfProcessingUnits);                             // Print
    System.out.println("Processing units per valence bank: " + totalNumberOfProcessingUnits / numberOfValenceBanks); // 
    /* @formatter:on */
  }

  /**
   * This method will take in the totalNumberOfProcessingUnits and initialize an agent with 8 arrays
   * of length = totalNumberOfProcessingUnits
   * 
   * @param processingUnitsPerBank
   */
  public void initializeValuesFromCSV() {
    /* @formatter:off */
    this.positiveInputProcessingUnitsT0   = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];
    this.negativeInputProcessingUnitsT0   = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];

    this.positiveInputProcessingUnitsT_1  = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];
    this.negativeInputProcessingUnitsT_1  = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];

    this.positiveOutputProcessingUnitsT0  = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];
    this.negativeOutputProcessingUnitsT0  = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];

    this.positiveOutputProcessingUnitsT_1 = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];
    this.negativeOutputProcessingUnitsT_1 = new double[totalNumberOfProcessingUnits / numberOfValenceBanks];
    /* @formatter:on */
  }

  /**
   * this method is hardcoded
   * @throws IOException 
   */
  public void initializeWeightsFromCSV(String csvWeightFile) throws IOException {
    // int bankConnections = 10;
//    positiveWeightProcessingUnit = new double[] {-1.0, -0.8, -0.6, -0.4,    // 0
//                                                 -0.2, 0.3, 0.5,            // 1
//                                                 0.7, 0.9,                  // 2
//                                                 1.0};                      // 3
    CSVReader reader = new CSVReader(new FileReader(csvWeightFile));
    String [] nextLine;
    int i = 0;
    // find the line of interest that represents the current agent's weight values
    while ((nextLine = reader.readNext()) != null) {
      System.out.println(nextLine[0] + nextLine[1] + "etc...");
    }
    
  }
  // TODO generate method that will dynamically create what the value and weight arrays will be
  /**
   * takes in an int (that corresponds to the array index)
   * takes the list of weights, and generates a new list with the corresponding weights
   */
  
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

  // Instance Methods
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
        positiveInputProcessingUnitsT_1[j] = doubleOfStringValue;
      }

      // the index is even (negative valence bank)
      if ((i % 2) == 0) {
        negativeInputProcessingUnitsT_1[j] = doubleOfStringValue;
        j++;
      }

    }
    /* @formatter:off */
    for (int i = 0; i < (totalNumberOfProcessingUnits / numberOfValenceBanks); i++) {   // Print
      System.out.println("+ " + positiveInputProcessingUnitsT_1[i] + " | - "            //
          + negativeInputProcessingUnitsT_1[i]);                                        //
    }                                                                                   //
    /* @formatter:on */
  }

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


  // GETTERS AND SETTERS

  public int getAgentID() {
    return agentID;
  }

  public void setAgentID(int agentID) {
    this.agentID = agentID;
  }

  public static String getProcessingUnitCSV() {
    return processingUnitCSV;
  }

  public static void setProcessingUnitCSV(String processingUnitCSV) {
    TestAgent.processingUnitCSV = processingUnitCSV;
  }

  public static String getInitializationLog() {
    return initializationLog;
  }

  public static void setInitializationLog(String initializationLog) {
    TestAgent.initializationLog = initializationLog;
  }

  public static int getTotalNumberOfProcessingUnits() {
    return totalNumberOfProcessingUnits;
  }

  public static void setTotalNumberOfProcessingUnits(int totalNumberOfProcessingUnits) {
    TestAgent.totalNumberOfProcessingUnits = totalNumberOfProcessingUnits;
  }

  public static int getTotalNumberOfProcessingUnitWeights() {
    return totalNumberOfProcessingUnitWeights;
  }

  public static void setTotalNumberOfProcessingUnitWeights(int totalNumberOfProcessingUnitWeights) {
    TestAgent.totalNumberOfProcessingUnitWeights = totalNumberOfProcessingUnitWeights;
  }

}
