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
