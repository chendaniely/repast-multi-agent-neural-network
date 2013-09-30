/**
 * 
 */
package neuralNetworkABM;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import reader.ReadExcelPoi;
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
import repast.simphony.util.ContextUtils;
import repast.simphony.util.SimUtilities;

/**
 * @author Daniel
 * 
 */
public class AgentABM {
	// field variables to hold the space and grid in which the agent will be
	// located
	// agent will move about the ContinousSpce and this space will be rounded up
	// to determine the corresponding Grid location
	private ContinuousSpace<Object> space;
	private Grid<Object> grid;
	private int agentNumber = -1;
	
	// list of weights from excel files for each agent
	private ArrayList<Double> agentVariableList = new ArrayList<Double>();
	
	// list of all agents in the context
	private ArrayList<Object> agentList = new ArrayList<Object>();
	
	// list of agents this.agent connects to
	private ArrayList<Object> agentNetworkList = new ArrayList<Object>();

	// constructor which sets the values of the space and grid variables
	// space and grid variables have Objects as their template parameter
	// (allows use to put anything in them)
	public AgentABM(ContinuousSpace<Object> space, Grid<Object> grid,
			int agentNumber, String variableWeightFilesName) {
		this.space = space;
		this.grid = grid;
		this.agentNumber = agentNumber;
		
		ReadExcelPoi readInVariableWeights = new ReadExcelPoi();
		
		ArrayList<Double> temp2 = readInVariableWeights.ReadExcelFile(
				variableWeightFilesName, agentVariableList, agentNumber);
		for (Double number : temp2) {
			System.out.println("for loop in constructor for each double in list:" + number);
		}
		this.agentVariableList = temp2;
	}

	@ScheduledMethod(start = 1, interval = 1)
	// method to agent that will be called at every iteration of the simulation
	public void step() {
		// get the grid location of this agent
		GridPoint pt = grid.getLocation(this);

		// use the GridCellNgh class to create GridCells for the surrounding
		// neighborhood
		// GridCellNgh is used to retrieve a list of GridCells that represent
		// the contents and location of the 8 neighboring cells around a
		// GridPoint
		GridCellNgh<AgentABM> nghCreator = new GridCellNgh<AgentABM>(grid, pt,
				AgentABM.class, 1, 1);
		List<GridCell<AgentABM>> gridCells = nghCreator.getNeighborhood(true);
		SimUtilities.shuffle(gridCells, RandomHelper.getUniform());

		influenceVariableWeight();

	}

	public void moveTowards(GridPoint pt) {
		// only move if we are not already in this grid location
		if (!pt.equals(grid.getLocation(this))) {
			NdPoint myPoint = space.getLocation(this);
			NdPoint otherPoint = new NdPoint(pt.getX(), pt.getY());
			double angle = SpatialMath.calcAngleFor2DMovement(space, myPoint,
					otherPoint);
			space.moveByVector(this, 1, angle, 0);
			myPoint = space.getLocation(this);
			grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
		}
	}

	public void buildAgentList(AgentABM agentObject) {
		this.agentList.add(agentObject);
	}

	public void linkAgents(Context<Object> context, FileWriter agentNetworkCSV) {
		// GridPoint pt = grid.getLocation(this);
		// List<Object> agentList = new ArrayList<Object>();
		
		// adds all agents in context to agentList
		// TODO need to fix so agent list is a property of the context, not
		// every individual agent
		for (Object obj : grid.getObjects()) {
			System.out.println("Object in grid: " + obj);
			if (obj instanceof AgentABM) {
				System.out.println("Adding to agentList: " + this.agentNumber
						+ ": " + ((AgentABM) obj).getAgentNumber() + " " + obj);
				this.agentList.add(obj);
			}
		}
		
		if (this.agentList.size() > 0) {
			System.out.println("going through list and creating links");
			// picks a random agent in agent list
			int index = RandomHelper
					.nextIntFromTo(0, this.agentList.size() - 1);
			System.out.println(index);
			Object obj = this.agentList.get(index);
			System.out.println(this.agentList.get(index));
			// Context<Object> context = ContextUtils.getContext(obj);

			// adds the randomly select agent in context to network
			Network<Object> net = (Network<Object>) context
					.getProjection("agent network");
			if (obj == null) {
				System.out.println("null object");
			}
			this.agentNetworkList.add(obj);
			System.out.println(((AgentABM) obj).getAgentNumber()
					+ " added to agentNetworkList " + getAgentNumber());
			net.addEdge(this, obj);
			
			try {
				agentNetworkCSV.append(Integer.toString(getAgentNumber()));
				agentNetworkCSV.append(',');
				agentNetworkCSV.append(Integer.toString(((AgentABM) obj).getAgentNumber()));
				agentNetworkCSV.append('\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}
	
	public void writeAgentWeightsToCSV(double agent1, double agent2, double difference, double change, double newValue) throws IOException{
		FileWriter writer = new FileWriter("./src/files/agentWeightCSV.csv", true);
		
		// agent (oneWhoInfluences), --> agent (oneWhoGetsInfluenced), difference, change, new value
		
	    writer.append(Double.toString(agent1));
	    writer.append(',');
	    writer.append(Double.toString(agent2));
	    writer.append(',');
	    writer.append(Double.toString(difference));
	    writer.append(',');
	    writer.append(Double.toString(change));
	    writer.append(',');
	    writer.append(Double.toString(newValue));
	    writer.append('\n');
		
		writer.flush();
		writer.close();
	}

	public void influenceListsDownstream(ArrayList<Double> oneWhoInfluences,
			ArrayList<Double> oneWhoGetsInfluenced) {
		System.out.println("Influenceing variables");
		System.out.println(oneWhoInfluences.size() + " " + oneWhoGetsInfluenced.size());
		if (oneWhoInfluences.size() == oneWhoGetsInfluenced.size()) {
			System.out.println("List size match!");
			for (int i = 0; i < oneWhoInfluences.size(); i++) {
				System.out.println("==========");
				System.out.println("changing: " + oneWhoInfluences.get(i) + " and " + oneWhoGetsInfluenced.get(i));
				
				// move .3 of the difference in the direction of the influencer
				double differenceInWeights = oneWhoInfluences.get(i)-oneWhoGetsInfluenced.get(i);
				double changeInWeightToApply = .3 * differenceInWeights;
				double newWeight = oneWhoGetsInfluenced.get(i) + changeInWeightToApply;
				
				System.out.println("difference in weights = " + differenceInWeights);
				System.out.println("changeInWeightToApply = " + changeInWeightToApply);
				System.out.println("newWeight = " + newWeight);
				oneWhoGetsInfluenced.set(i, newWeight);
				
				System.out.println("new values: " + oneWhoInfluences.get(i) + " and " + oneWhoGetsInfluenced.get(i));
				
				try {
					writeAgentWeightsToCSV(oneWhoInfluences.get(i), oneWhoGetsInfluenced.get(i), differenceInWeights, changeInWeightToApply, newWeight);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} else {
			System.out
					.println("@@@@@@@@@@ LISTS DO NOT MATCH IN SIZE @@@@@@@@@@");
		}
	}

	public void influenceVariableWeight() {
		for (Object agent : agentNetworkList) {
			System.out.println("I am agent number: " + this.agentNumber
					+ ".  This agent is in my network: "
					+ ((AgentABM) agent).getAgentNumber());
			influenceListsDownstream(this.agentVariableList, ((AgentABM) agent).agentVariableList);
		}
	}
	

	/**
	 * @return the agentNumber
	 */
	public int getAgentNumber() {
		return agentNumber;
	}

	/**
	 * @param agentNumber
	 *            the agentNumber to set
	 */
	public void setAgentNumber(int agentNumber) {
		this.agentNumber = agentNumber;
	}

}
