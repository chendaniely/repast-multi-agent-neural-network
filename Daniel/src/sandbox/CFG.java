package sandbox;

public class CFG {
  static final int           NUMBER_OF_TIME_TICKS                 = 5;

  static final double        EPSILON                              = 0.00001;

  static final int           LENGTH_X                             = 10;
  static final int           LENGTH_Y                             = 10;

  // output file

  public static final String OUTPUT_FILE_CSV                      = "./src/sandbox/output/sanity7-nodecay.csv";
  static final String        DEL                                  = ",";
  static final String        NEW_LINE                             = "\n";

  // it's important that the agent numbering is sequential and the same in both files
  static final String        PROCESSING_UNIT_VALUES_CSV           
    = "./src/sandbox/input/sanity/sanity-3-4,6-singleSeedActivationValues.csv";
  
  static final String        PROCESSING_UNIT_WEIGHTS_CSV          
    = "./src/sandbox/input/sanity/sanity-1-6-agentWeights.csv";
  
  static final String        EDGE_LIST_CSV                       
    = "./src/sandbox/input/sanity/sanity-6-agentEdgeList-10-multiple.csv";
  
  static final int           EDGE_LIST_CSV_READ_FROM_LINE         = 1;
  
  static final double        LOGIT_TUNE                           = 1;
  
  // calculation weights
  static final double        OPPOSITE_PROCESSING_UNIT_WEIGHT      = (-0.7);
  static final double        CORRESPONDING_PROCESSING_UNIT_WEIGHT = 0.5;
  static final double        CARRY_OVER                           = 0.2;
  static final double        BIAS                                 = (0); // leave as 0
  static final double        DECAY                                = (0);

}
