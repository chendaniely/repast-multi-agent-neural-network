package sandbox;

public class TestAgent {
  
  private static String processingUnitCSV = "";
  
  private int totalNumberOfProcessingUnits = -1;
  
  // GETTERS AND SETTERS

  public static String getProcessingUnitCSV() {
    return processingUnitCSV;
  }

  public static void setProcessingUnitCSV(String processingUnitCSV) {
    TestAgent.processingUnitCSV = processingUnitCSV;
  }

  public int getTotalNumberOfProcessingUnits() {
    return totalNumberOfProcessingUnits;
  }

  public void setTotalNumberOfProcessingUnits(int totalNumberOfProcessingUnits) {
    this.totalNumberOfProcessingUnits = totalNumberOfProcessingUnits;
  }

}
