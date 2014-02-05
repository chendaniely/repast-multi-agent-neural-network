/**
 * 
 */
package neuralNetworkABM;

import initializeAgents.InitializeAgentState;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.SpatialMath;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.graph.Network;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;
import writer.WriteToCSV;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author Daniel
 * 
 */
public class AgentABM {
  // field variables to hold the space and grid in which the agent will be located agent will move
  // about the ContinousSpce and this space will be rounded up to determine the corresponding Grid
  // location
  private ContinuousSpace<Object> space;
  private Grid<Object> grid;
  private int agentNumber = -1;
  private int timeTick = 0;
  private static int agentCount = 0;

  // list of weights from excel files for each agent
  private ArrayList<Double> agentVariableList = new ArrayList<Double>();

  // list of all agents in the context
  private ArrayList<Object> agentList = new ArrayList<Object>();

  // list of agents this.agent connects to
  private ArrayList<Object> agentNetworkList = new ArrayList<Object>();

  // list of agents that this.agent gets influenced by (the agents upstream)
  private ArrayList<Object> agentInfluencedBy = new ArrayList<Object>();

  // constructor which sets the values of the space and grid variables
  // space and grid variables have Objects as their template parameter
  // (allows use to put anything in them)
  public AgentABM(ContinuousSpace<Object> space, Grid<Object> grid, String variableWeightFilesName) {
    this.space = space;
    this.grid = grid;
    this.agentNumber = agentNumber;

    InitializeAgentState initializeWeightsXLS = new InitializeAgentState();

    ArrayList<Double> agentVariableWeights =
        initializeWeightsXLS.initializeFromXLS(variableWeightFilesName, agentVariableList,
            agentNumber);

    // print statements to see what was read in
    if (GlobalSettings.DEBUG) {
      for (Double number : agentVariableWeights) {
        System.out.println("Stored in agentVariableWeights Array:" + number);
      }
    }

    this.agentVariableList = agentVariableWeights;

    String[] arrayOfHeaderNames =
        {"time", "agent 1", "agent 1 value", "--> agent 2", "agent 2 value", "difference",
            "value change", "new value"};
    try {
      WriteToCSV writeCSV =
          new WriteToCSV(neuralNetworkABM.GlobalSettings.OUTPUT_AGENT_VARIABLE_CSV,
              arrayOfHeaderNames, false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ScheduledMethod(start = 1, interval = 1)
  // method to agent that will be called at every iteration of the simulation
  public void step() {
    timeTick += 1;

    // get the grid location of this agent
    GridPoint pt = grid.getLocation(this);

    // use the GridCellNgh class to create GridCells for the surrounding neighborhood GridCellNgh is
    // used to retrieve a list of GridCells that represent the contents and location of the 8
    // neighboring cells around a GridPoint
    GridCellNgh<AgentABM> nghCreator = new GridCellNgh<AgentABM>(grid, pt, AgentABM.class, 1, 1);
    List<GridCell<AgentABM>> gridCells = nghCreator.getNeighborhood(true);
    SimUtilities.shuffle(gridCells, RandomHelper.getUniform());

    influenceVariableWeight(timeTick);

  }

  public void moveTowards(GridPoint pt) {
    // only move if we are not already in this grid location
    if (!pt.equals(grid.getLocation(this))) {
      NdPoint myPoint = space.getLocation(this);
      NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
      double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint, otherPoint);
      space.moveByVector(this, 1, angle, 0);
      myPoint = space.getLocation(this);
      grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
    }
  }

  public void linkAgents(Context<Object> context, CSVWriter agentNetworkCSV) {
    // GridPoint pt = grid.getLocation(this);
    // List<Object> agentList = new ArrayList<Object>();

    // adds all agents in context to agentList
    // TODO need to fix so agent list is a property of the context, not
    // every individual agent
    for (Object obj : grid.getObjects()) {
      if (GlobalSettings.DEBUG) System.out.println("Object in grid: " + obj);
      if (obj instanceof AgentABM) {
        if (GlobalSettings.DEBUG)
          System.out.println("Adding to agentList: " + this.agentNumber + ": "
              + ((AgentABM) obj).getAgentNumber() + " " + obj);
        this.agentList.add(obj);
      }
    }

    if (this.agentList.size() > 0) {
      if (GlobalSettings.DEBUG) System.out.println("going through list and creating links");

      // picks a random agent in agent list
      int index = RandomHelper.nextIntFromTo(0, this.agentList.size() - 1);
      if (GlobalSettings.DEBUG) System.out.println(index);
      Object obj = this.agentList.get(index);
      if (GlobalSettings.DEBUG) System.out.println(this.agentList.get(index));
      // Context<Object> context = ContextUtils.getContext(obj);

      // adds the randomly select agent in context to network
      Network<Object> net = (Network<Object>) context.getProjection("agent network");
      if (obj == null) {
        if (GlobalSettings.DEBUG) System.out.println("null object");
      }
      this.agentNetworkList.add(obj);
      if (GlobalSettings.DEBUG)
        System.out.println(((AgentABM) obj).getAgentNumber() + " added to agentNetworkList "
            + getAgentNumber());
      net.addEdge(this, obj);

      String[] agentNetwork =
          {Integer.toString(getAgentNumber()), Integer.toString(((AgentABM) obj).getAgentNumber())};

    }

    // going to fill out 'agentInfluencedBy' (agents that link to this.agent)
    // by iterating through agentNetworkList
    // agentNetworkList = list of agents this.agent connects to
    // agentInfluencedBy = list of agents that this.agent gets influenced by
    int i = 0;
    for (Object agent : agentNetworkList) {
      if (GlobalSettings.DEBUG)
        System.out.println(this.getAgentNumber() + "agentNetworkList (I connect to) "
            + ((AgentABM) agent).getAgentNumber());

      ((AgentABM) agent).agentInfluencedBy.add(this.getAgentNumber());
      if (GlobalSettings.DEBUG)
        System.out.println(((AgentABM) agent).agentInfluencedBy.get(i) + " <-- ");
      i++;
    }
  }

  /**
   * Takes a timeTick iterates through all the agents in the agentNetworkList (list of agents
   * this.agent connects to) and 'influencesListsDownstream'
   */
  public void influenceVariableWeight(int timeTick) {
    for (Object agent : agentNetworkList) {
      if (GlobalSettings.DEBUG)
        System.out.println("I am agent number: " + this.agentNumber
            + ".  This agent is in my network: " + ((AgentABM) agent).getAgentNumber());
      influenceListsDownstream(timeTick, this.getAgentNumber(), this.agentVariableList,
          ((AgentABM) agent).getAgentNumber(), ((AgentABM) agent).agentVariableList);
    }
  }

  /**
	 *
	 */
  public void influenceListsDownstream(int timeTick, int agentWhoInfluences,
      ArrayList<Double> oneWhoInfluences, int agentWhoGetsInfluenced,
      ArrayList<Double> oneWhoGetsInfluenced) {

    if (GlobalSettings.DEBUG) System.out.println("Influenceing variables");
    if (GlobalSettings.DEBUG)
      System.out.println(oneWhoInfluences.size() + " " + oneWhoGetsInfluenced.size());
    if (oneWhoInfluences.size() == oneWhoGetsInfluenced.size()) {
      if (GlobalSettings.DEBUG) System.out.println("List size match!");

      for (int i = 0; i < oneWhoInfluences.size(); i++) {
        if (GlobalSettings.DEBUG) System.out.println("==========");
        if (GlobalSettings.DEBUG)
          System.out.println("changing: " + oneWhoInfluences.get(i) + " and "
              + oneWhoGetsInfluenced.get(i));

        // move .3 of the difference in the direction of the influencer
        double differenceInWeights = oneWhoInfluences.get(i) - oneWhoGetsInfluenced.get(i);
        double changeInWeightToApply = .3 * differenceInWeights;
        double newWeight = oneWhoGetsInfluenced.get(i) + changeInWeightToApply;

        if (GlobalSettings.DEBUG) System.out.println("Time = " + timeTick);
        if (GlobalSettings.DEBUG)
          System.out.println("oneWhoInfluences: " + agentWhoInfluences
              + ", agentWhoGetsInfluenced: " + agentWhoGetsInfluenced);
        if (GlobalSettings.DEBUG)
          System.out.println("difference in weights = " + differenceInWeights);
        if (GlobalSettings.DEBUG)
          System.out.println("changeInWeightToApply = " + changeInWeightToApply);
        if (GlobalSettings.DEBUG) System.out.println("newWeight = " + newWeight);
        oneWhoGetsInfluenced.set(i, newWeight);

        if (GlobalSettings.DEBUG)
          System.out.println("new values: " + oneWhoInfluences.get(i) + " and "
              + oneWhoGetsInfluenced.get(i));

        String[] arrayOfValues =
            {Integer.toString(timeTick), Integer.toString(agentWhoInfluences),
                Double.toString(oneWhoInfluences.get(i)), Integer.toString(agentWhoGetsInfluenced),
                Double.toString(oneWhoGetsInfluenced.get(i)), Double.toString(differenceInWeights),
                Double.toString(changeInWeightToApply), Double.toString(newWeight)};

        try {
          FileWriter file = new FileWriter(GlobalSettings.OUTPUT_AGENT_VARIABLE_CSV, true);
          CSVWriter write = new CSVWriter(file);
          write.writeNext(arrayOfValues);
        } catch (IOException e) {
          e.printStackTrace();
        }


        // try {
        // WriteToCSV writeCSV =
        // new WriteToCSV(GlobalSettings.OUTPUT_AGENT_VARIABLE_CSV, arrayOfValues, true);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
      }
    } else {
      System.err.println("@@@@@@@@@@ LISTS DO NOT MATCH IN SIZE @@@@@@@@@@");
    }
  }

  // //////// @@@@@@@@@@ GETTERS AND SETTERS @@@@@@@@@ //////////

  /**
   * @return the agentNumber
   */
  public int getAgentNumber() {
    return agentNumber;
  }

  /**
   * @param agentNumber the agentNumber to set
   */
  public void setAgentNumber(int agentNumber) {
    this.agentNumber = agentNumber;
  }

  public ArrayList<Object> getAgentNetworkList() {
    return agentNetworkList;
  }

  public void setAgentNetworkList(ArrayList<Object> agentNetworkList) {
    this.agentNetworkList = agentNetworkList;
  }

  public ArrayList<Object> getAgentInfluencedBy() {
    return agentInfluencedBy;
  }

  public void setAgentInfluencedBy(ArrayList<Object> agentInfluencedBy) {
    this.agentInfluencedBy = agentInfluencedBy;
  }



}
