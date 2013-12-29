package writer;

import java.io.FileWriter;
import java.io.IOException;

public class InitializaCSVHeaders {
	
	public InitializaCSVHeaders(String fileDirectory, String[] arrayOfHeaderNames) throws IOException {
		System.out.println("Initializing CSV file");
		FileWriter writer = new FileWriter(fileDirectory);
		
		for (int i = 0; i < arrayOfHeaderNames.length - 1; i++) {
			writer.append(arrayOfHeaderNames[i]);
			writer.append(',');
		}
		writer.append(arrayOfHeaderNames[arrayOfHeaderNames.length]);
		writer.append('\n');
		
		writer.flush();
		writer.close();
	}

}
