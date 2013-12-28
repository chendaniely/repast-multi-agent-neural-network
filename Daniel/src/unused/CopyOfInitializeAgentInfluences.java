package unused;

import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

public class CopyOfInitializeAgentInfluences {
	
	/*
	 * 
	 * We say agent A connects to agent B
	 * A --> B
	 * Agent A influences B
	 * Agent B is influenced by A
	 * 
	 * Agent A is the agentWhoInfluences
	 * Agent B is the agentWhoGetsInfluenced
	 * 
	 * */
	
	public static void main(String [] args) throws IOException{
		System.out.println("hello");
		CSVReader reader = new CSVReader(new FileReader("./src/files/agentNetworkCSV.csv"));
		String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	        System.out.println(nextLine[0] + "\t" + nextLine[1]);
	    }
	    reader.close();
	}

}
