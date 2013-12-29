package unused;

import java.io.FileWriter;
import java.io.IOException;

public class InitializaCSVHeaders {
	
	public InitializaCSVHeaders(String fileDirectory, String[] arrayOfHeaderNames, Boolean append) throws IOException {
		System.out.println("Writing CSV file");
		FileWriter writer = new FileWriter(fileDirectory, append);
		
		for (int i = 0; i < arrayOfHeaderNames.length - 1; i++) {
			writer.append(arrayOfHeaderNames[i]);
			writer.append(',');
		}
		// writes the last value and then puts in a newline
		writer.append(arrayOfHeaderNames[arrayOfHeaderNames.length-1]);
		writer.append('\n');
		
		writer.flush();
		writer.close();
	}

}
