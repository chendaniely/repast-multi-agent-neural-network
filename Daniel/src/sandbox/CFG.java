package sandbox;

public class CFG {

  final static double EPSILON = 0.00001;

  final static int LENGTH_X = 10;
  final static int LENGTH_Y = 10;
  
  // it's important that the agent numbering is sequential and the same in both files

  // simple numbers used for testing
  //static final String PROCESSING_UNIT_VALUES_CSV = "./src/sandbox/AgentProcessigUnitValues.csv";
  //static final String PROCESSING_UNIT_WEIGHTS_CSV = "./src/sandbox/AgentProcessigUnitWeights.csv";
  
  // randomly generated numbers used to model
  static final String PROCESSING_UNIT_VALUES_CSV = "./src/sandbox/agentActivationValues.csv";
  static final String PROCESSING_UNIT_WEIGHTS_CSV = "./src/sandbox/agentWeights.csv";
  static final String EDGE_LIST_CSV = "./src/sandbox/agentEdgeList.csv";

  static final double OPPOSITE_PROCESSING_UNIT_WEIGHT = (-.2);
  static final double CORRESPONDING_PROCESSING_UNIT_WEIGHT = .5;
  
  static final String DEL = ", ";

}
