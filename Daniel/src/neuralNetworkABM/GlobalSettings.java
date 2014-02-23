/**
 * 
 */
package neuralNetworkABM;

/**
 * @author dyc2112
 * 
 */

public final class GlobalSettings {

  public final static boolean DEBUG = true;

  // grid size
  public final static int LENGTH_X = 10;
  public final static int LENGTH_Y = 10;

  // number of agents
  public final static int NUMBER_OF_AGENTS = 10;
  public final static boolean NUMBER_OF_AGENTS_FROM_FILE = true;

  // file directories
  public final static String INPUT_AGENT_VARIABLE_XLS = "../files/agent variable weights.xls";
  public final static String INPUT_AGENT_VARIABLE_CSV = "../files/InitializeAgentVariableWeights.csv";
  
  public final static String OUTPUT_AGENT_NETWORK_CSV = "./src/files/agentNetworkCSV.csv";
  public final static String OUTPUT_AGENT_VARIABLE_CSV = "./src/files/agentWeightCSV.csv";
}
