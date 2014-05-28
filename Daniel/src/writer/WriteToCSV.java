/**
 * 
 */
package writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import sandbox.CFG;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author dchen
 * 
 */
public class WriteToCSV {

  public WriteToCSV() {}


  /**
   * @return
   * @throws IOException
   * 
   */
  public void writeArrayToCSV(String fileDirectory, String[] arrayOfValues, boolean append)
      throws IOException {
    System.out.println("Writing to CSV file");

    FileWriter writer = new FileWriter(fileDirectory, append);

    for (int i = 0; i < arrayOfValues.length - 1; i++) {
      writer.append(arrayOfValues[i]);
      writer.append(',');
    }
    // writes the last value and then puts in a newline
    writer.append(arrayOfValues[arrayOfValues.length - 1]);
    writer.append('\n');

    writer.flush();
    writer.close();

  }

  /**
   * used in my sandbox package takes the current time tick, current agent, and array of PU
   * activation values and writes to CSV file, in the array, 0 index are pos values, 1 index are
   * negative values
   * 
   * @throws IOException
   * 
   */
  public void writeAgentTimeTickToFile(int time, int agent,
      double[][] processingUnitActivationValues) throws IOException {

    File outputFile = new File(CFG.OUTPUT_FILE_CSV);
    if (!outputFile.exists()) {
      outputFile.createNewFile();
    }
    // FileOutputStream oFile = new FileOutputStream(outputFile, true);

    CSVWriter writer = new CSVWriter(new FileWriter(CFG.OUTPUT_FILE_CSV, true), ',');
    // // feed in array (or convert your data to an array)

    String stringOfArray = Arrays.deepToString(processingUnitActivationValues);
    String stringOfEntries =
        Integer.toString(time) + "," + Integer.toString(agent) + "," + stringOfArray;

    String[] entries = stringOfEntries.replace("[", "").replace("]", "").split(",");

    // System.out.println("writting to csv: " + Arrays.toString(entries));

    writer.writeNext(entries);
    writer.close();
  }

  public void writeRandomWeightsToFile(int agent, double[] randomWeights) throws IOException {

    File outputFile = new File(CFG.RANDOM_WEIGHTS);
    if (!outputFile.exists()) {
      outputFile.createNewFile();
    }
    // FileOutputStream oFile = new FileOutputStream(outputFile, true);

    CSVWriter writer = new CSVWriter(new FileWriter(CFG.RANDOM_WEIGHTS, true), ',');
    // // feed in array (or convert your data to an array)

    String stringOfArray = Arrays.toString(randomWeights);
    String stringOfEntries = Integer.toString(agent) + "," + stringOfArray;

    String[] entries = stringOfEntries.replace("[", "").replace("]", "").split(",");

    System.out.println("writting randomweights: " + Arrays.toString(entries));

    writer.writeNext(entries);
    writer.close();
  }


}
