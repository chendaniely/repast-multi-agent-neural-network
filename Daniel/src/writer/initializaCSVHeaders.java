package writer;

import java.io.FileWriter;
import java.io.IOException;

public class initializaCSVHeaders {
	
	public initializaCSVHeaders(String filedirectory, String[] arrayOfHeaderNames) throws IOException {
		FileWriter writer = new FileWriter(filedirectory);
		
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
