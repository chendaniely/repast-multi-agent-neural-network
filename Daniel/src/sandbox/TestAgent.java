package sandbox;

public class TestAgent {
  
  private static String processingUnitCSV = null;
  private static String initializationLog = null; 
  private static int totalNumberOfProcessingUnits = -1;
  
  // GETTERS AND SETTERS

  public static String getProcessingUnitCSV() {
    return processingUnitCSV;
  }

  public static void setProcessingUnitCSV(String processingUnitCSV) {
    TestAgent.processingUnitCSV = processingUnitCSV;
  }

  public static String getInitializationLog() {
    return initializationLog;
  }

  public static void setInitializationLog(String initializationLog) {
    TestAgent.initializationLog = initializationLog;
  }

  public static int getTotalNumberOfProcessingUnits() {
    return totalNumberOfProcessingUnits;
  }

  public static void setTotalNumberOfProcessingUnits(int totalNumberOfProcessingUnits) {
    TestAgent.totalNumberOfProcessingUnits = totalNumberOfProcessingUnits;
  }

}
