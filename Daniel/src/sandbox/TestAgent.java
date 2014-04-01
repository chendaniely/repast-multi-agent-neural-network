package sandbox;

import java.util.ArrayList;
import java.util.Arrays;

public class TestAgent {

  // Static class variables
  @SuppressWarnings("unused")
  private static int    numAgentsCreated                   = 0;

  // file where the agent log is
  private static String initializationLog                  = null;

  // this is the number of processing units read from the CSV file, it includes the positive +
  // negative processing units for each belief
  private static int    totalNumberOfProcessingUnits       = -1;

  // this represents the combination of processing units in each valence bank CHOOSE 2 (since each
  // processing unit will be connected to the other processing units in each valence bank)
  private static int    totalNumberOfProcessingUnitWeights = -1;

  // number of valence banks in agent
  private static int    numberOfValenceBanks               = 2;

  // Processing Unit activation values
  double[][]            processingUnitActivationValues     = null;

  // weights between processing units
  // [0][]positiveWeightProcessingUnit
  // [1][]negativeWeightProcessingUnit
  double[][]            processingUnitWeights              = null;

  double                weightBetweenValenceBanks          = 0;
  double                weightCorespondingModule           = 0;

  // Variables unique to each agent
  private int           agentID                            = 0;
  ArrayList<Integer>    agentsWhoInfluenceMe               = new ArrayList<Integer>();

  // Constructor(s)
  public TestAgent() {
    numAgentsCreated++;
  }

  // Class methods
  /**
   * Takes a 2d array, the 0 index containing the activation level of the positive valence bank
   * processing units, the 1 index containing the activation level of the negative valence bank
   * processing units
   * 
   * @param posnegValues is a 2d array of the positive and negative activation levels
   */
  public void setProcessingUnitActivationValues(double[][] posnegValues) {
    System.out.println("setAgentValues");
    processingUnitActivationValues = new double[2][posnegValues[0].length];
    for (int i = 0; i < 2; i++) {
      System.arraycopy(posnegValues[i], 0, this.processingUnitActivationValues[i], 0,
          posnegValues[0].length);
    }
    System.out.println(Arrays.deepToString(processingUnitActivationValues));
  }

  // GETTERS AND SETTERS

  public int getAgentID() {
    return agentID;
  }

  public void setAgentID(int agentID) {
    this.agentID = agentID;
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

  public static int getNumberOfValenceBanks() {
    return numberOfValenceBanks;
  }

  public static void setNumberOfValenceBanks(int numberOfValenceBanks) {
    TestAgent.numberOfValenceBanks = numberOfValenceBanks;
  }

  public static int getTotalNumberOfProcessingUnitWeights() {
    return totalNumberOfProcessingUnitWeights;
  }

  public static void setTotalNumberOfProcessingUnitWeights(int totalNumberOfProcessingUnitWeights) {
    TestAgent.totalNumberOfProcessingUnitWeights = totalNumberOfProcessingUnitWeights;
  }

}
