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

  static void initializeAgentsFromCSV() throws IOException {

    // read in csv, and skips first line (headers)
    CSVReader reader =
        new CSVReader(new FileReader("./src/sandbox/AgentProcessigUnitValues.csv"), ',', '\"', 1);

    String[] nextLine;
    while ((nextLine = reader.readNext()) != null) {
      // nextLine[] is an array of values from the line
      System.out.println(nextLine[0] + nextLine[1] + "etc...");
    }
  }

  void countNumberOfProcessingUnits() {

  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // print working directory
    System.out.println(System.getProperty("user.dir"));


    try {
      initializeAgentsFromCSV();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
