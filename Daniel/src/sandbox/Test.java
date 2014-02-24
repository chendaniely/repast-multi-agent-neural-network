/**
 * 
 */
package sandbox;

import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author dchen
 * 
 */
public class Test {

  private String logDir = null;

  void readProcessingUnitsCSV() {

  }

  static void initializeAgentsFromCSV() throws IOException {

    // read in csv, and skips first line (headers)
    CSVReader reader =
        new CSVReader(new FileReader(TestAgent.getProcessingUnitCSV()), ',', '\"', 1);

    String[] nextLine;

    while ((nextLine = reader.readNext()) != null) {
      // njareextLine[] is an array of values from the line

      for (int i = 0; i < nextLine.length; i++) {
        System.out.print(nextLine[i] + " ");
      }
      // System.out.println(nextLine[0] + " " + nextLine[1] + " etc...");
      System.out.println(nextLine.length);


    }
  }

  void countNumberOfProcessingUnits() {

  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    // setting up the log file
    WriteToFile log = null;
    try {
      log = new WriteToFile("./src/sandbox/testLog.log");
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try {
      log.write("hello");
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    // print working directory
    System.out.println(System.getProperty("user.dir"));

    // TestAgent initializeClassVariables = new TestAgent();
    TestAgent.setProcessingUnitCSV("./src/sandbox/AgentProcessigUnitValues.csv");

    try {
      initializeAgentsFromCSV();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }


}
