package sandbox;

import java.io.IOException;
import java.util.Arrays;

public class UnitTest {
  
  public static boolean almostEqual(double a, double b){
    return Math.abs(a-b)< CFG.EPSILON;
}
  

  public static void testCalculationClass() {
    // Unit test the Calculation class
    Calculation calculationUnitTest = new Calculation();

    // testing calculateTotalNumberOfProcessingUnits()
    try {
      assert calculationUnitTest
          .calculateTotalNumberOfProcessingUnits(CFG.PROCESSING_UNIT_VALUES_CSV) == 10;
    } catch (IOException e) {
      e.printStackTrace();
    }

    // testing initializeProcessingUnitsFromCSV()
    assert calculationUnitTest.calculateNumberOfProcessingUnitsForEachArray(10, 2) == 5;

    // testing calculateNumberOfWeightsOnEachValenceBank()
    assert (calculationUnitTest.calculateNumberOfWeightsOnEachValenceBank(5) == 10);

    // testing convertToLogit()
    assert (calculationUnitTest.convertToLogit(0.0) == .5);
    assert ((calculationUnitTest.convertToLogit(1.0) - 0.73105857863) < CFG.EPSILON);
    assert ((calculationUnitTest.convertToLogit(.5)) - 0.6224593312) < CFG.EPSILON;

    // testing calculateOppositeProcessingUnit()
    assert (calculationUnitTest.calculateOppositeProcessingUnit(1)) == -.2;
    assert (calculationUnitTest.calculateOppositeProcessingUnit(0)) == 0;
    assert (calculationUnitTest.calculateOppositeProcessingUnit(.5)) == -.1;

    // testing calculateCorrespondingProcessingUnit()
    double[] input1 = {1.0, 0.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input1)) == .5;
    double[] input2 = {0.0, 0.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input2)) == 0;
    double[] input3 = {1.0, 1.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input3)) == 1;

    // testing calculateValenceBankInput()
    double[] values1 = {0.1, 0.2, 0.3, 0.4, 0.5};
    double[] weights1 = {0, -1, -0.8, -0.6, -0.4};
    assert (calculationUnitTest.calculateValenceBankInput(values1, weights1) - (-0.88) < CFG.EPSILON);
    double[] values2 = {0.1, 0.3, 0.5, 0.7, 0.9};
    double[] weights2 = {0, -1, -0.8, -0.6, -0.4};
    assert (calculationUnitTest.calculateValenceBankInput(values2, weights2) - (-1.48) < CFG.EPSILON);

    // testing calculateOutputFromInputs()
//    assert (calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5, 0) == 0);
    assert (almostEqual(calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5, 0), 0));
    
    assert ((calculationUnitTest.calculateOutputFromInputs(-.5, 1, .5, 0) - 0.23105857863) < CFG.EPSILON);
    assert (almostEqual(calculationUnitTest.calculateOutputFromInputs(-.5, 1, .5, 0), 0.23105857863));
    
//    assert ((calculationUnitTest.calculateOutputFromInputs(0, .5, .5, 0) - 0.73105857863) < CFG.EPSILON);
    assert (almostEqual(calculationUnitTest.calculateOutputFromInputs(0, .5, .5, 0), 0.23105857863));
    
//    assert ((calculationUnitTest.calculateOutputFromInputs(0, 0, 1, 0) - 0.73105857863) < CFG.EPSILON);
    assert (almostEqual(calculationUnitTest.calculateOutputFromInputs(0, 0, 1, 0), 0.23105857863));
    
//    assert ((calculationUnitTest.calculateOutputFromInputs(-1.48, -0.04, 0.1, 0) - 0.19466158359) < CFG.EPSILON);
    assert (almostEqual(calculationUnitTest.calculateOutputFromInputs(-1.48, -0.04, 0.1, 0), -0.30533841641));

    // testing processing unit 1 positive bank new value
    double[] valuesPU1p = {0.1, 0.3, 0.5, 0.7, 0.9};
    double[] weightsPU1p = {0, -1, -0.8, -0.6, -0.4};
    double oppositePU1p = 0.2;
    double[] correspondingPU1p = {0.2};
    assert (almostEqual(calculationUnitTest.calculateOutputFromInputs(
        calculationUnitTest.calculateValenceBankInput(valuesPU1p, weightsPU1p),
        calculationUnitTest.calculateOppositeProcessingUnit(oppositePU1p),
        calculationUnitTest.calculateCorrespondingProcessingUnit(correspondingPU1p),
        0), -0.30533841641));
  
  // testing processing unit 1 positive bank new value for sanity 6
  double[] valuesPU1p1 = {0.1, 0.3, 0.5, 0.7, 0.9};
  double[] weightsPU1p1 = {0, -1, -0.8, -0.6, -0.4};
  double oppositePU1p1 = 0.2;
  double[] correspondingPU1p1 = {0.2};
  assert (almostEqual(calculationUnitTest.calculateOutputFromInputs(
      calculationUnitTest.calculateValenceBankInput(valuesPU1p1, weightsPU1p1),
      calculationUnitTest.calculateOppositeProcessingUnit(oppositePU1p1),
      calculationUnitTest.calculateCorrespondingProcessingUnit(correspondingPU1p1),
      0), -0.30533841641));
}
  
  

  public static void testInitializationClass() {
    // Unit test the Initialization class
    Initialization initializatoinUnitTest = new Initialization();

    double[] initArray = {0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 0.1, 0.2};
    double[][] initArrayTest = { {0.3, 0.5, 0.7, 0.9, 0.1}, {0.4, 0.6, 0.8, 1.0, 0.2}};
    double[][] init1 = initializatoinUnitTest.initializeVBActivation(initArray);
    assert Arrays.deepEquals(init1, initArrayTest);

    double[] initArray2 = {1, -1, -0.8, -0.6, -0.4, -0.2, 0.3, 0.5, 0.7, 0.9, 1};
    double[][] initArrayTest2 =
        { {0.0, -1.0, -0.8, -0.6, -0.4}, {-1.0, 0.0, -0.2, 0.3, 0.5}, {-0.8, -0.2, 0.0, 0.7, 0.9},
            {-0.6, 0.3, 0.7, 0.0, 1.0}, {-0.4, 0.5, 0.9, 1.0, 0.0}};
    double[][] init2 = initializatoinUnitTest.initializeVBWeights(1, initArray2);
    assert Arrays.deepEquals(init2, initArrayTest2);
  }

  public static void testGenerationClass() {
    // Unit test the Initialization class
    Generation generationUnitTest = new Generation();

    double[] vbWeights = {-1, -0.8, -0.6, -0.4, -0.2, 0.3, 0.5, 0.7, 0.9, 1};
    double[][] test1 =
        { {0.0, -1.0, -0.8, -0.6, -0.4}, {-1.0, 0.0, -0.2, 0.3, 0.5}, {-0.8, -0.2, 0.0, 0.7, 0.9},
            {-0.6, 0.3, 0.7, 0.0, 1.0}, {-0.4, 0.5, 0.9, 1.0, 0.0}};
    double[][] generate1 = generationUnitTest.generateValenceBankWeightArrays(vbWeights);
    assert Arrays.deepEquals(test1, generate1);
  }

  public static void testTestAgent() {
    Generation generationUnitTest = new Generation();
    TestAgent testAgentUnitTest = new TestAgent();
    double[] pos = {-1, -0.8, -0.6, -0.4, -0.2, 0.3, 0.5, 0.7, 0.9, 1};
    double[] neg = {-1, -0.8, -0.6, -0.4, -0.2, 0.3, 0.5, 0.7, 0.9, 1};
    double[][] posw = generationUnitTest.generateValenceBankWeightArrays(pos);
    double[][] negw = generationUnitTest.generateValenceBankWeightArrays(neg);

    double[][][] test3darray =
        {
            { {0.0, -1.0, -0.8, -0.6, -0.4}, {-1.0, 0.0, -0.2, 0.3, 0.5},
                {-0.8, -0.2, 0.0, 0.7, 0.9}, {-0.6, 0.3, 0.7, 0.0, 1.0}, {-0.4, 0.5, 0.9, 1.0, 0.0}},
            { {0.0, -1.0, -0.8, -0.6, -0.4}, {-1.0, 0.0, -0.2, 0.3, 0.5},
                {-0.8, -0.2, 0.0, 0.7, 0.9}, {-0.6, 0.3, 0.7, 0.0, 1.0}, {-0.4, 0.5, 0.9, 1.0, 0.0}}};

    System.out.println(Arrays.deepToString(testAgentUnitTest.make3darrayfrom2d(posw, negw)));
    assert Arrays.deepEquals(testAgentUnitTest.make3darrayfrom2d(posw, negw), test3darray);
  }

  public static void main(String[] args) {
    System.out.println("current working directory: " + System.getProperty("user.dir"));
    testCalculationClass();
    testInitializationClass();
    testGenerationClass();
    testTestAgent();
    System.out.println("done");
  }
}
