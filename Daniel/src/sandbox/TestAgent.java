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

  // Processing Unit activation values, 0 index are positive valence banks, 1 index are negative
  // valence banks
  double[][]            processingUnitActivationValues     = null;
  // TODO hard-coded the temp array size, should be totalnumber/num valence
  double[][]            tempProcessingUnitActivationValues = new double[2][5];

  // weights between processing units [0] are the pos neg valence banks [1] are the pu# [2] are the
  // weight links 0 index are positive valence banks, 1 index are negative
  // valence banks
  double[][][]          processingUnitWeights              = null;

  double                weightOppositeValenceBanks         = CFG.OPPOSITE_PROCESSING_UNIT_WEIGHT;
  double                weightCorespondingModule           =
                                                               CFG.CORRESPONDING_PROCESSING_UNIT_WEIGHT;

  // Variables unique to each agent
  private int           agentID                            = 0;
  int[]                 agentsWhoInfluenceMe               = null;

  // Constructor(s)
  public TestAgent() {
    numAgentsCreated++;
  }

  // Class methods

  /**
   * method that takes in 2 2d arrays, uses the second array to fill the first
   * 
   * @param fillMe array to be filled
   * @param fillUsing array used to fill
   */
  private void set2dArray(double[][] fillMe, double[][] fillUsing) {
    for (int i = 0; i < 2; i++) {
      System.arraycopy(fillUsing[i], 0, fillMe[i], 0, fillMe[0].length);
    }
  }

  /**
   * takes in 2 2d arrays, where the first array represents the positive values, the second array
   * represents the negative values. this method then creates a 3d array where the 0 index of the 3d
   * are the positive 2d array values, and the 1 index are the negative 2d values
   * 
   * @param pos
   * @param neg
   * @return
   */
  public double[][][] make3darrayfrom2d(double[][] pos, double[][] neg) {
    double[][][] d3array = new double[2][pos[0].length][pos[1].length];
    System.arraycopy(pos, 0, d3array[0], 0, pos[0].length);
    System.arraycopy(neg, 0, d3array[1], 0, neg[0].length);
    return d3array;
  }

  /**
   * method that takes in 2 3d arrays, uses the second array to fill the first
   * 
   * @param fillMe
   * @param fillUsing
   */
  private void set3dArray(double[][][] fillMe, double[][][] fillUsing) {
    for (int i = 0; i < 2; i++) {
      System.arraycopy(fillUsing[i], 0, fillMe[i], 0, fillMe[0].length);
    }
  }

  /**
   * returns the opposite index, assumes there are 2 valence banks where 0 represents positive 1
   * represents negative valence banks used to calculate opposite valence activation
   * 
   * @param i
   * @return
   */
  public int returnOpposite(int i) {
    if (i == 0) return 1;
    if (i == 1) return 0;
    return -1;
  }

  public void step(ArrayList<TestAgent> agents) {
    processingUnitActivationValues = tempProcessingUnitActivationValues;
    // this is the print statement that writes out the outputs
    System.out.println(Arrays.deepToString(processingUnitActivationValues));

    // write the printed console values to csv file

    Calculation calculation = new Calculation();

    // TODO the 2 is hard coded, it represents the number of valence banks
    // for each valence bank
    for (int i = 0; i < 2; i++) {
      // TODO the 5 is hard coded, it represents how many PU in each bank
      // for each processing unit in each valence bank
      for (int j = 0; j < 5; j++) {
        double[] values = processingUnitActivationValues[i];
        double[] weights = processingUnitWeights[i][j];
        double opposite = processingUnitActivationValues[returnOpposite(i)][j];
        double[] corresponding = new double[agentsWhoInfluenceMe.length];

        int k = 0;
        for (int agentIdWhoInfluenceMe : agentsWhoInfluenceMe) {
          for (TestAgent agent : agents) {
            if (agentIdWhoInfluenceMe == agent.getAgentID()) {
              corresponding[k] = agent.processingUnitActivationValues[i][j];
              k++;
            }
          }
        }

        double newOutput =
            calculation.calculateOutputFromInputs(
                calculation.calculateValenceBankInput(values, weights),
                calculation.calculateOppositeProcessingUnit(opposite),
                calculation.calculateCorrespondingProcessingUnit(corresponding));
        
        // ((final - initial) * step%) + initial
        double newOutputStep =
            ((newOutput - tempProcessingUnitActivationValues[i][j]) * CFG.UPDATE_PROPORATION)
                + tempProcessingUnitActivationValues[i][j];
        
        tempProcessingUnitActivationValues[i][j] = newOutputStep;
      }
    }
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

  /**
   * Takes a 2d array, the 0 index containing the activation level of the positive valence bank
   * processing units, the 1 index containing the activation level of the negative valence bank
   * processing units
   * 
   * @param posnegValues is a 2d array of the positive and negative activation levels
   */
  public void setProcessingUnitActivationValues(double[][] posnegValues) {
    System.out.println("setProcessingUnitActivationValues");
    processingUnitActivationValues = new double[2][posnegValues[0].length];
    set2dArray(this.processingUnitActivationValues, posnegValues);

    System.out.println("\t activation values set:\n\t"
        + Arrays.deepToString(this.processingUnitActivationValues));
  }

  /**
   * Takes a 2d array, the 0 index containing the activation level of the positive valence bank
   * processing units, the 1 index containing the activation level of the negative valence bank
   * processing units
   * 
   * @param weightArray
   */
  public void setProcessingUnitWeights(double[][] weightArray) {
    System.out.println("setProcessingUnitWeights");
    processingUnitWeights = new double[2][weightArray[0].length][weightArray[1].length];
    set3dArray(this.processingUnitWeights, make3darrayfrom2d(weightArray, weightArray));

    System.out
        .println("\t agent weight set:\n\t" + Arrays.deepToString(this.processingUnitWeights));
  }

  public void setAgentsWhoInfluenceMe(int[] edgeArray) {
    System.out.println("setEdges");
    agentsWhoInfluenceMe = Arrays.copyOf(edgeArray, edgeArray.length);
    System.out.println("\t agent edge set:\n\t" + Arrays.toString(this.agentsWhoInfluenceMe));
  }
}
