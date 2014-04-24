package sandbox;

public class CFG {
  static final int           NUMBER_OF_TIME_TICKS                 = 10000;

  static final double        EPSILON                              = 0.00001;

  static final int           LENGTH_X                             = 10;
  static final int           LENGTH_Y                             = 10;

  // output file

  public static final String OUTPUT_FILE_CSV                      = "./src/sandbox/output/output.csv";
  static final String        DEL                                  = ",";
  static final String        NEW_LINE                             = "\n";

  // it's important that the agent numbering is sequential and the same in both files

  // simple numbers used for testing
  // static final String PROCESSING_UNIT_VALUES_CSV =
  // "./src/sandbox/input/AgentProcessigUnitValues.csv";
  // static final String PROCESSING_UNIT_WEIGHTS_CSV =
  // "./src/sandbox/input/AgentProcessigUnitWeights.csv";
  // static final String EDGE_LIST_CSV = "./src/sandbox/input/testAgentEdgeList.csv";

  // randomly generated numbers used to model
  // static final String PROCESSING_UNIT_VALUES_CSV =
  // "./src/sandbox/input/agentActivationValues.csv";
  // static final String PROCESSING_UNIT_WEIGHTS_CSV = "./src/sandbox/input/agentWeights.csv";
  // static final String EDGE_LIST_CSV = "./src/sandbox/input/agentEdgeList.csv";

  // randomly generated numbers used to model, single seed

  // singleSeedPosOnlyOnesActivationValues
  // singleSeedPosOnlyActivationValues
  // singleSeedActivationValues
  static final String        PROCESSING_UNIT_VALUES_CSV           = "./src/sandbox/input/singleSeedActivationValues.csv";
  static final String        PROCESSING_UNIT_WEIGHTS_CSV          = "./src/sandbox/input/agentWeights.csv";
  static final String        EDGE_LIST_CSV                        = "./src/sandbox/input/agentEdgeList.csv";

  // calculation weights
  static final double        OPPOSITE_PROCESSING_UNIT_WEIGHT      = (-.02);
  static final double        CORRESPONDING_PROCESSING_UNIT_WEIGHT = .5;
  static final double        CARRY_OVER                           = 0.2;
  static final double        BIAS                                 = (0); // leave as 0
  static final double        DECAY                                = (-.5);


}
