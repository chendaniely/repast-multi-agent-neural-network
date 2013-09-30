/**
 * 
 */
package writer;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dchen
 *
 */
public class ExampleWriteToTxt {
	
/////////////////////////////////////

/** The below Java Code writes 
* some data to a file in CSV
* (Comma Separated Value) File
*/
	public static void main(String[] args) throws IOException {
		//Note the "\\" used in the path of the file instead of "\",
		//this is required to read the path in the String format.
		FileWriter fw = new FileWriter("./src/files/TestTxtOutput.txt");
		PrintWriter pw = new PrintWriter(fw);

		//Write to file line by line
		pw.println("Hello guys");
		pw.println("Java Code Online is testing");
		pw.println("writing to a file operation");

		//Flush the output to the file
		pw.flush();

		//Close the Print Writer
		pw.close();

		//Close the File Writer
		fw.close(); 
		
		System.out.println(System.getProperty("user.dir"));
		}
}
