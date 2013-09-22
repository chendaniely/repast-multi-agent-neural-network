/**
 * 
 */
package neuralNetworkABM;

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
    private ArrayList<Double> agentVariableList = new ArrayList<Double>();
    private ArrayList<Object> agentList = new ArrayList<Object>();
    private ArrayList<Object> agentNetworkList = new ArrayList<Object>();

    // constructor which sets the values of the space and grid variables
    // space and grid variables have Objects as their template parameter
    // (allows use to put anything in them)
    public AgentABM(ContinuousSpace<Object> space, Grid<Object> grid,
			int agentNumber, String variableWeightFilesName) {
		this.space = space;
		this.grid = grid;
		this.agentNumber = agentNumber;
		// so the constructor for readExcelPoi will run and set the agent variables]
		//readinweights();
		//ReadExcelPoi readInVariableWeights = new ReadExcelPoi(variableWeightFilesName, agentVariableList, agentNumber);
		ReadExcelPoi readInVariableWeights = new ReadExcelPoi();
		ArrayList<Double> temp2 = readInVariableWeights.ReadExcelFile(variableWeightFilesName, agentVariableList, agentNumber);
		for (Double number: temp2){
		    System.out.println(number);	
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

	// taken from zombie model where it was looking for point with most
	// humans
	// do not need this part of the code per conversation with Mark
	// 2013/07/31
	// GridPoint pointWithMostAgents = null;
	// int maxCount = -1;
	// for (GridCell<AgentABM> cell : gridCells) {
	// // print the list
	// System.out.println("Printing AgentABM in gridCell Location: "
	// + cell + " point: " + cell.getPoint());
	//
	// if (cell.size() > maxCount) {
	// pointWithMostAgents = cell.getPoint();
	// maxCount = cell.size();
	// }
	// }
	// // adding moveTowards area with most Agents
	// //moveTowards(pointWithMostAgents);
	//
	// //just move the agent around in a random direction
	// GridPoint randomPoint = null;

	//linkAgents();

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

    /*
     * public void changeVariableWeights(Object agentObject){ for(int i = 0; i <
     * AgentABM.agentVariableList.size(); i ++ ) {
     * AgentABM.agentVariableList.set(i, .9 *
     * AgentABM.agentVariableList.get(i)); } }
     */
    public void buildAgentList(AgentABM agentObject) {
	this.agentList.add(agentObject);
    }

    public void linkAgents(Context<Object> context) {
	// GridPoint pt = grid.getLocation(this);
	// List<Object> agentList = new ArrayList<Object>();

	// adds all agents in context to agentList
	// TODO need to fix so agent list is a property of the context, not
	// every individual agent
	//int i = 0;
	for (Object obj : grid.getObjects()) {
	    System.out.println("Object in grid: " + obj);
	    if (obj instanceof AgentABM) {
		System.out.println("Adding to agentList: " + this.agentNumber
			+ ": " + agentNumber + " " + obj);
		//i = this.agentNumber;
		this.agentList.add(obj);

	    }
	}
	if (this.agentList.size() > 0) {
	    System.out.println("going through list and creating links");
	    // picks a random agent in agent list
	    int index = RandomHelper.nextIntFromTo(0, this.agentList.size() - 1);
	    System.out.println(index);
	    Object obj = this.agentList.get(index);
	    System.out.println(this.agentList.get(index));
	    //Context<Object> context = ContextUtils.getContext(obj);

	    // adds the randomly select agent in context to network
	    Network<Object> net = (Network<Object>) context.getProjection("agent network");
	    if (obj == null){
		System.out.println("null object");
	    }
	    this.agentNetworkList.add(obj);
	    System.out.println(((AgentABM) obj).getAgentNumber() + "added to agentNetworkList");
	    net.addEdge(this, obj);
	}
    }

    /*
     * public void readLinkedAgentVariable() {
     * 
     * }
     * 
     * public ArrayList<Double> getAgentVariableList() { return
     * agentVariableList; }
     * 
     * public void setAgentVariableList(ArrayList<Double> agentVariableList) {
     * AgentABM.agentVariableList = agentVariableList; }
     */
    public void appendDoubleToVariableList(double variableToAppend) {
	agentVariableList.add(variableToAppend);
    }

}
