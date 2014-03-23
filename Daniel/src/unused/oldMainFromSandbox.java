package unused;

public class oldMainFromSandbox {
  

  // // setting up the log file
  // WriteToFile log = null;
  // TestAgent.setInitializationLog("./src/sandbox/testLog.log");
  // try {
  // log = new WriteToFile(TestAgent.getInitializationLog());
  //
  // // write test line to file
  // log.write("hello!");
  //
  // // write working directory
  // log.write("current working directory: " + System.getProperty("user.dir"));
  //
  // } catch (IOException e1) {
  // e1.printStackTrace();
  // }
  //
  // TestAgent initAgent = new TestAgent();
  // Calculation initCalc = new Calculation(initAgent);
  // Initialization initInit = new Initialization(initAgent);
  //
  // // TestAgent initializeClassVariables = new TestAgent();
  // // this sets where the csv file for the process unit values are
  // TestAgent.setProcessingUnitCSV("./src/sandbox/AgentProcessigUnitValues.csv");
  //
  // try {
  // // count number of processing units in file, this will later initialize the agent PU arrays
  // TestAgent.setTotalNumberOfProcessingUnits(initInit.countTotalNumberOfProcessingUnits());
  // log.write("Number of processing units: " + TestAgent.getTotalNumberOfProcessingUnits());
  //
  // // count number of weights on each valence bank: NUMBEROFPROCESSINGUNITS choose 2
  // TestAgent.setTotalNumberOfProcessingUnitWeights(initCalc
  // .calculateNumberOfWeightsOnEachValenceBank(TestAgent.getTotalNumberOfProcessingUnits()));
  // log.write("Number of weights on each valence bank: "
  // + TestAgent.getTotalNumberOfProcessingUnitWeights());
  //
  // // create and initialize agents from CSV
  // TestAgent.createAgentsFromCSV();
  // log.write("Agent loaded from CSV files");
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  //
  //
  // double valenceInput =
  // initCalc.calculateValenceBankInput(initAgent.testPosValBank,
  // initAgent.testPosValBankWeights);
  //
  // double oppositeInput = initCalc.calculateOppositeProcessingUnit(.2);
  //
  // double[] allCorresponding = new double[] {.2};
  //
  // double correspondingInput = initCalc.calculateCorrespondingProcessingUnit(allCorresponding);
  //
  // // System.out.println(correspondingInput);
  // double totalInput = valenceInput + oppositeInput + correspondingInput;
  //
  // System.out.println(initCalc.convertToLogit(totalInput));
  //
  // try {
  // initInit.initializeWeightsFromCSV("AgentProcessigUnitWeights.csv");
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  //
  // System.out.println("DONE");

}
