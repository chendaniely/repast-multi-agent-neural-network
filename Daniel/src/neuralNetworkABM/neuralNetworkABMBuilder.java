/**
 * 
 */
package neuralNetworkABM;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import repast.simphony.context.Context;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author Daniel
 * 
 */
public class neuralNetworkABMBuilder implements ContextBuilder<Object> {

  /*
   * (non-Javadoc)
   * 
   * @see repast.simphony.dataLoader.ContextBuilder#build(repast.simphony.context .Context)
   */
  @Override
  public Context build(Context<Object> context) {
    context.setId("neuralNetworkABM");

    NetworkBuilder<Object> netBuilder = new NetworkBuilder<Object>("agent network", context, true);
    netBuilder.buildNetwork();

    ContinuousSpaceFactory spaceFactory =
        ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
    ContinuousSpace<Object> space =
        spaceFactory.createContinuousSpace("space", context, new RandomCartesianAdder<Object>(),
            new repast.simphony.space.continuous.WrapAroundBorders(), 20, 20);

    GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
    Grid<Object> grid =
        gridFactory.createGrid("grid", context, new GridBuilderParameters<Object>(
            new WrapAroundBorders(), new SimpleGridAdder<Object>(), true, 20, 20));

    // creating the agents
    int agentAbmCount = GlobalSettings.NUMBEROFAGENTS;

    if (GlobalSettings.DEBUG) System.out.println("Creating " + agentAbmCount + " agents");
    for (int agentNumber = 1; agentNumber < agentAbmCount; agentNumber++) {
      AgentABM agent =
          new AgentABM(space, grid, agentNumber,
              neuralNetworkABM.GlobalSettings.INPUT_AGENT_VARIABLE_XLS);
      context.add(agent);
      if (GlobalSettings.DEBUG)
        System.out.println("##### Agent " + agent.getAgentNumber() + " created.");

    }

    // creates csv file to document agent linking

//    FileWriter agentNetworkCSV = null;
    CSVWriter agentNetworkCSV = null;

    try {
      agentNetworkCSV = new CSVWriter(new FileWriter(GlobalSettings.OUTPUT_AGENT_NETWORK_CSV));
      String[] agentNetworkCSVHeaders = {"Agent", "-> Agent"};
      agentNetworkCSV.writeNext(agentNetworkCSVHeaders);
      // TODO check if closing here will affect appending the agent links
      agentNetworkCSV.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    }


    // try {
    // agentNetworkCSV = new FileWriter("./src/files/agentNetworkCSV.csv");
    // agentNetworkCSV.append("Agentasdf");
    // agentNetworkCSV.append(',');
    // agentNetworkCSV.append("--> Agent");
    // agentNetworkCSV.append('\n');
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }


    // move the agents to the Grid location that corresponds to their
    // ContinuousSpace location
    for (Object obj : context) {
      NdPoint pt = space.getLocation(obj);
      grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
      ((AgentABM) obj).linkAgents(context, agentNetworkCSV);
    }

    /*
     * Since the agent links are already written to a csv file we can write a function that iterates
     * row by row in the csv file get the agent in the second row (the one under -->; lets call this
     * agent B) and append the agent in the first row (lets call this agent A) to the arrayList
     * agentInfluencedBy B.agentInfluencedBy.add(A.getAgentNumber)
     */

    try {
      agentNetworkCSV.flush();
      agentNetworkCSV.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // adding the influencing agent to a list in the agent that is getting influenced
    if (GlobalSettings.DEBUG) System.out.println("hello");
    CSVReader reader = null;
    try {
      reader = new CSVReader(new FileReader("./src/files/agentNetworkCSV.csv"));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String[] nextLine;
    try {
      if (GlobalSettings.DEBUG) System.out.println("enter while");
      while ((nextLine = reader.readNext()) != null) {
        // nextLine[] is an array of values from the line
        if (GlobalSettings.DEBUG) System.out.println(nextLine[0] + "\t" + nextLine[1]);
      }
      if (GlobalSettings.DEBUG) System.out.println("enter for");
      for (Object agent : context) {
        if (agent instanceof AgentABM) {
          if (GlobalSettings.DEBUG) System.out.println(((AgentABM) agent).getAgentNumber());

          // the do should skip the first row of col names
          boolean flag = true;
          while ((nextLine = reader.readNext()) != null);
          {
            if (GlobalSettings.DEBUG) System.out.println("in while again");
            if (flag == true) {
              // bool is used to skip the first column, which is the column name
              flag = false;
              continue;
            } else {
              if (((AgentABM) agent).getAgentNumber() == Integer.parseInt(nextLine[1])) {
                if (GlobalSettings.DEBUG) System.out.println("in the else part of while");
                if (GlobalSettings.DEBUG) System.out.println(nextLine[0] + "\t" + nextLine[1]);
              }
            }
          }
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      reader.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return context;
  }
}
