package sandbox;

import java.io.IOException;

public class UnitTest {

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

    assert (calculationUnitTest.calculateNumberOfWeightsOnEachValenceBank(5) == 10);
    assert (calculationUnitTest.convertToLogit(0.0) == .5);
    assert ((calculationUnitTest.convertToLogit(1.0) - 0.26894142137) < CFG.EPSILON);

    assert (calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5) == .5);
    assert (calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5) == .5);
    assert ((calculationUnitTest.calculateOutputFromInputs(-.5, 1, .5) - 0.26894142137) < CFG.EPSILON);
    assert ((calculationUnitTest.calculateOutputFromInputs(0, .5, .5) - 0.26894142137) < CFG.EPSILON);
    assert ((calculationUnitTest.calculateOutputFromInputs(0, 0, 1) - 0.26894142137) < CFG.EPSILON);

    double[] values1 = {0.1, 0.2, 0.3, 0.4, 0.5};
    double[] weights1 = {0, -1, -0.8, -0.6, -0.4};
    assert (calculationUnitTest.calculateValenceBankInput(values1, weights1) - (-0.88) < CFG.EPSILON);

    double[] values2 = {0.1, 0.3, 0.5, 0.7, 0.9};
    double[] weights2 = {0, -1, -0.8, -0.6, -0.4};
    assert (calculationUnitTest.calculateValenceBankInput(values2, weights2) - (-1.48) < CFG.EPSILON);


    assert (calculationUnitTest.calculateOppositeProcessingUnit(1)) == -.2;
    assert (calculationUnitTest.calculateOppositeProcessingUnit(0)) == 0;
    assert (calculationUnitTest.calculateOppositeProcessingUnit(.5)) == -.1;

    double[] input1 = {1.0, 0.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input1)) == .5;
    double[] input2 = {0.0, 0.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input2)) == 0;
    double[] input3 = {1.0, 1.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input3)) == 1;

    assert (calculationUnitTest.convertToLogit(0)) == .5;
    assert ((calculationUnitTest.convertToLogit(1)) - 0.26894142137) < CFG.EPSILON;
    assert ((calculationUnitTest.convertToLogit(.5)) - 0.37754066879) < CFG.EPSILON;

    // testing processing unit 1 positive bank new value
    double[] valuesPU1p = {0.1, 0.3, 0.5, 0.7, 0.9};
    double[] weightsPU1p = {0, -1, -0.8, -0.6, -0.4};
    double oppositePU1p = 0.2;
    double[] correspondingPU1p = {0.2};
    assert (calculationUnitTest.calculateOutputFromInputs(
        calculationUnitTest.calculateValenceBankInput(valuesPU1p, weightsPU1p),
        calculationUnitTest.calculateOppositeProcessingUnit(oppositePU1p),
        calculationUnitTest.calculateCorrespondingProcessingUnit(correspondingPU1p)) - 0.8053384164) < CFG.EPSILON;
  }

  public static void testInitializationClass() {
    // Unit test the Initialization class
    Initialization initializatoinUnitTest = new Initialization();
  }

  public static void main(String[] args) {
    System.out.println("current working directory: " + System.getProperty("user.dir"));
    testCalculationClass();
    testInitializationClass();
    System.out.println("done");
  }
}
