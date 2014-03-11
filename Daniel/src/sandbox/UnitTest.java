package sandbox;

public class UnitTest {

  public static void main(String[] args) {
    Calculation calculationUnitTest = new Calculation();
    assert (calculationUnitTest.calculateNumberOfWeightsOnEachValenceBank(5) == 10);
    assert (calculationUnitTest.convertToLogit(0.0) == .5);
    assert ((calculationUnitTest.convertToLogit(1.0) - 0.26894142137) < CFG.EPSILON);

    assert (calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5) == .5);
    assert (calculationUnitTest.calculateOutputFromInputs(-.5, 0, .5) == .5);
    assert ((calculationUnitTest.calculateOutputFromInputs(-.5, 1, .5) - 0.26894142137) < CFG.EPSILON);
    assert ((calculationUnitTest.calculateOutputFromInputs(0, .5, .5) - 0.26894142137) < CFG.EPSILON);
    assert ((calculationUnitTest.calculateOutputFromInputs(0, 0, 1) - 0.26894142137) < CFG.EPSILON);
  }
}
