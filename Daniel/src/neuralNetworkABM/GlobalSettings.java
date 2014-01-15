/**
 * 
 */
package neuralNetworkABM;

/**
 * Treat this as a configuration file
 * @author dyc2112
 * 
 */

public final class GlobalSettings {
  // grid size
  public final static int LENGTH_X = 10;
  public final static int LENGTH_Y = 10;

  // file directories
  public final static String AGENTVARIABLEXLSLOCATION = "../files/agent variable weights.xls";
  public final static String agentWeightValues = "./src/files/agentWeightCSV.csv";

  public final static boolean DEBUG = true;

  // number of agents
  public final static int NUMBEROFAGENTS = 10;
  public final static boolean NUMBEROFAGENTSFROMFILE = true;
}
