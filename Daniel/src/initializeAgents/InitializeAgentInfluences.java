package initializeAgents;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import neuralNetworkABM.AgentABM;
import repast.simphony.context.Context;
import au.com.bytecode.opencsv.CSVReader;


public class InitializeAgentInfluences {
  String csvFile = "";

  /*
   * 
   * We say agent A connects to agent B A --> B Agent A influences B Agent B is influenced by A
   * 
   * Agent A is the agentWhoInfluences Agent B is the agentWhoGetsInfluenced
   */


  // Constructors
  public InitializeAgentInfluences() {}

  public InitializeAgentInfluences(String csvFile) {
    this.csvFile = csvFile;
  }

  public void assignAgentInfluences(Context<Object> context, FileReader agentNetworkCSV)
      throws IOException {
    System.out.println("hello");
    // CSVReader reader = new CSVReader(new FileReader("./src/files/agentNetworkCSV.csv"));
    CSVReader reader = new CSVReader(new FileReader("./src/files/agentNetworkCSV.csv"));
    List myEntries = reader.readAll();
    System.out.println(myEntries);

    System.out.println("hello");
    // List<String[]> nextLine;
    System.out.println("hello");
    for (Object agent : context) {
      if (agent instanceof AgentABM) {
        ((AgentABM) agent).getAgentNumber();
        System.out.println(((AgentABM) agent).getAgentNumber());
      }
    }

    System.out.println(nextLine = reader.readAll());
    boolean temp = ((nextLine = reader.readAll()) != null);
    System.out.println(temp);
    // while ((nextLine = reader.readAll()) != null) {
    // System.out.println("hello");
    // // nextLine[] is an array of values from the line
    // System.out.println(nextLine);
    // }
    reader.close();
  }

  public String getCsvFile() {
    return csvFile;
  }

  public void setCsvFile(String csvFile) {
    this.csvFile = csvFile;
  }

}
