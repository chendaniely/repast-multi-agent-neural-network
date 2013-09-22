/**
 * 
 */
package neuralNetworkABM;

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

/**
 * @author Daniel
 * 
 */
public class neuralNetworkABMBuilder implements ContextBuilder<Object> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * repast.simphony.dataLoader.ContextBuilder#build(repast.simphony.context
	 * .Context)
	 */
	@Override
	public Context build(Context<Object> context) {
		context.setId("neuralNetworkABM");

		NetworkBuilder<Object> netBuilder = new NetworkBuilder<Object>(
				"agent network", context, true);
		netBuilder.buildNetwork();

		ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder
				.createContinuousSpaceFactory(null);
		ContinuousSpace<Object> space = spaceFactory.createContinuousSpace(
				"space", context, new RandomCartesianAdder<Object>(),
				new repast.simphony.space.continuous.WrapAroundBorders(), 20,
				20);

		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context,
				new GridBuilderParameters<Object>(new WrapAroundBorders(),
						new SimpleGridAdder<Object>(), true, 20, 20));

		// read excel file
		// ReadExcelPoi excel = new
		// ReadExcelPoi("C:\\Users\\Daniel\\Dropbox\\code\\code\\Repast NeuralNetwork\\Daniel\\src\\reader\\agent variable weights.xls",
		// 0);
		
		// creating the agents
		int agentAbmCount = 10;//reader.ReadExcelPoi.getNumAgentRows();
		for (int agentNumber = 1; agentNumber < agentAbmCount; agentNumber++) {
			AgentABM agent = new AgentABM(
					space,
					grid,
					agentNumber,
					neuralNetworkABM.GlobalSpaceConstant.agentVariableWeightLocation);
			context.add(agent);
		}
		
		// move the agents to the Grid location that corresponds to their
		// ContinuousSpace location
		for (Object obj : context) {
			NdPoint pt = space.getLocation(obj);
			grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
			System.out.println("##### Agent " + ((AgentABM) obj).getAgentNumber() + " created.");
			((AgentABM) obj).linkAgents(context);
		}
			
		return context;
	}
}
