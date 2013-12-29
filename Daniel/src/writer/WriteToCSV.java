/**
 * 
 */
package writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author dchen
 * 
 */
public class WriteToCSV {

	/**
	 * @throws IOException
	 * 
	 */
	public WriteToCSV(String fileDirectory, String[] arrayOfValues, boolean append)
			throws IOException {
		System.out.println("Writing to CSV file");

		FileWriter writer = new FileWriter(fileDirectory, append);
		
		for (int i = 0; i < arrayOfValues.length - 1; i++) {
			writer.append(arrayOfValues[i]);
			writer.append(',');
		}
		// writes the last value and then puts in a newline
		writer.append(arrayOfValues[arrayOfValues.length-1]);
		writer.append('\n');
		
		writer.flush();
		writer.close();

	}

}
