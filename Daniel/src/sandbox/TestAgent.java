package sandbox;

import java.util.ArrayList;

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

  double[] positiveInputProcessingUnitsT0 = null;
  double[] negativeInputProcessingUnitsT0 = null;
  double[] positiveInputProcessingUnitsT_1 = null;
  double[] negativeInputProcessingUnitsT_1 = null;

  double[] positiveOutputProcessingUnitsT0 = null;
  double[] negativeOutputProcessingUnitsT0 = null;
  double[] positiveOutputProcessingUnitsT_1 = null;
  double[] negativeOutputProcessingUnitsT_1 = null;

  private int agentID = 0;
  ArrayList<Integer> agentsWhoInfluenceMe = new ArrayList<Integer>();

  // Constructor(s)
  public TestAgent() {
    /* @formatter:off */
    System.out.println("totalNumberOfProcessingUnits: " + totalNumberOfProcessingUnits);                      // Print
    System.out.println("Processing units per valence bank: " + totalNumberOfProcessingUnits / numberOfValenceBanks); // 
    /* @formatter:on */
  }

  /**
   * This method will take in the totalNumberOfProcessingUnits and initialize an agent with 8 arrays
   * of length = totalNumberOfProcessingUnits
   * 
   * @param processingUnitsPerBank
   */
  public void initializeFromCSV() {
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
