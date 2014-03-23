package sandbox;

import java.io.IOException;

public class UnitTest {

  public static void main(String[] args) {
    System.out.println("current working directory: " + System.getProperty("user.dir"));

    /*
     * Unit test the Calculation class
     */
    Calculation calculationUnitTest = new Calculation();
    assert (calculationUnitTest.calculateNumberOfWeightsOnEachValenceBank(5) == 10);
    assert (calculationUnitTest.convertToLogit(0.0) == .5);
    assert ((calculationUnitTest.convertToLogit(1.0) - 0.26894142137) < CFG.EPSILON);

    assert (calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5) == .5);
    assert (calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5) == .5);
    assert ((calculationUnitTest.calculateOutputFromInputs(-.5, 1, .5) - 0.26894142137) < CFG.EPSILON);
    assert ((calculationUnitTest.calculateOutputFromInputs(0, .5, .5) - 0.26894142137) < CFG.EPSILON);
    assert ((calculationUnitTest.calculateOutputFromInputs(0, 0, 1) - 0.26894142137) < CFG.EPSILON);

    /*
     * NEED TO ADD calculateValenceBankInput();
     */
    assert (calculationUnitTest.calculateOppositeProcessingUnit(1)) == -.2;
    assert (calculationUnitTest.calculateOppositeProcessingUnit(0)) == 0;
    assert (calculationUnitTest.calculateOppositeProcessingUnit(.5)) == -.1;

    double[] input = {1.0, 0.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input)) == .5;
    double[] input1 = {0.0, 0.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input1)) == 0;
    double[] input2 = {1.0, 1.0};
    assert (calculationUnitTest.calculateCorrespondingProcessingUnit(input2)) == 1;

    assert (calculationUnitTest.convertToLogit(0)) == .5;
    assert ((calculationUnitTest.convertToLogit(1)) - 0.26894142137) < CFG.EPSILON;
    assert ((calculationUnitTest.convertToLogit(.5)) - 0.37754066879) < CFG.EPSILON;

    /*
     * Unit test the Initialization class
     */
    Initialization initializatoinUnitTest = new Initialization();

    // testing countTotalNumberOfProcessingUnits()
    try {
      assert initializatoinUnitTest
          .countTotalNumberOfProcessingUnits(CFG.PROCESSING_UNIT_VALUES_CSV) == 10;
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    // testing initializeProcessingUnitsFromCSV()
    assert initializatoinUnitTest.initializeProcessingUnitsFromCSV(10, 2) == 5;


    System.out.println("done");
  }
}
