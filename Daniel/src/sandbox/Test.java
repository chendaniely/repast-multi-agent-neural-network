/**
 * 
 */
package sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author dchen
 * 
 */
public class Test {

  // private String logDir = null;

  /**
   * read in the first line of the file to determine how many processing units each module (agent)
   * will have
   * 
   * @throws IOException
   */
  public static void countNumberOfProcessingUnits() throws IOException {

    BufferedReader br = null;

    br = new BufferedReader(new FileReader(TestAgent.getProcessingUnitCSV()));

    String fLine = br.readLine();

    br.close();

    // System.out.println("first lines: " + fLine);

    // counts number of commas, this will return the number of columns that is not the first row
    // in our case, this should represent the number of processing units in our csv file
    int numberOfProcessingUnits = StringUtils.countMatches(fLine, ",");

    // System.out.println(numberOfProcessingUnits);

    // log.write(numberOfProcessingUnits);

    TestAgent.setTotalNumberOfProcessingUnits(numberOfProcessingUnits);

  }

  /**
   * This will take a line and return a double array of processing unit values
   * 
   * @return
   */
  public double[] readProcessingUnitsCSV() {
    double[] processingUnitValues = new double[TestAgent.getTotalNumberOfProcessingUnits()];

    return null;

  }
  
  /**
   * This will see how many lines where
   * @throws IOException
   */

  public static void initializeAgentsFromCSV() throws IOException {

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



  /**
   * @param args
   */
  public static void main(String[] args) {

    // setting up the log file
    WriteToFile log = null;
    TestAgent.setInitializationLog("./src/sandbox/testLog.log");
    try {
      log = new WriteToFile(TestAgent.getInitializationLog());
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // write test line to file
    try {
      log.write("hello!");
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // print working directory
    try {
      log.write("current working directory: " + System.getProperty("user.dir"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    // TestAgent initializeClassVariables = new TestAgent();
    TestAgent.setProcessingUnitCSV("./src/sandbox/AgentProcessigUnitValues.csv");

    // count number of processing units needed, this will later initialize the agent arrays
    try {
      countNumberOfProcessingUnits();
      log.write("Number of processing units: " + TestAgent.getTotalNumberOfProcessingUnits());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


    System.out.println("DONE");
  }


}
