package sandbox;

public class CFG {
  static final int           NUMBER_OF_TIME_TICKS                 = 10;

  static final double        EPSILON                              = 0.00001;

  static final int           LENGTH_X                             = 10;
  static final int           LENGTH_Y                             = 10;

  // output file
  public static final String OUTPUT_FILE_CSV                      = "./src/sandbox/output/test.csv";
  static final String        DEL                                  = ",";
  static final String        NEW_LINE                             = "\n";
  
  static final int           GENERATE_RANDOM_WEIGHTS              = 1;
  public static final String RANDOM_WEIGHTS                       = "./src/sandbox/output/exp1b-randomWeights.csv";

  // it's important that the agent numbering is sequential and the same in both files
  static final String        PROCESSING_UNIT_VALUES_CSV           
    = "./src/sandbox/input/model/exp1b-PU-ActivationValues.csv";
  
  static final String        PROCESSING_UNIT_WEIGHTS_CSV          
    = "./src/sandbox/input/model/exp1b-VB-Weights-100-agents-blank.csv";
  
  static final String        EDGE_LIST_CSV                       
    = "./src/sandbox/input/model/exp1b-dan_edge.csv";
  
  static final int           EDGE_LIST_CSV_READ_FROM_LINE         = 0;
  
  static final double        LOGIT_TUNE                           = 1;
  
  // calculation weights
  static final double        OPPOSITE_PROCESSING_UNIT_WEIGHT      = (-5);
  static final double        CORRESPONDING_PROCESSING_UNIT_WEIGHT = 0.5;
  static final double        CARRY_OVER                           = 0.7;
  static final double        BIAS                                 = (0); // leave as 0
  static final double        DECAY                                = (0);

}
