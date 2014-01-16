package initializeAgents;

import neuralNetworkABM.AgentABM;
import neuralNetworkABM.GlobalSettings;
import repast.simphony.context.Context;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.graph.Network;
import au.com.bytecode.opencsv.CSVWriter;

public class InitializeAgentNetwork {

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

      // try {
      String[] agentNetwork =
          {Integer.toString(getAgentNumber()), Integer.toString(((AgentABM) obj).getAgentNumber())};
      agentNetworkCSV.writeNext(agentNetwork);
      // agentNetworkCSV.append(Integer.toString(getAgentNumber()) );
      // agentNetworkCSV.append(',');
      // agentNetworkCSV.append(Integer.toString(((AgentABM) obj).getAgentNumber()));
      // agentNetworkCSV.append('\n');
      // } catch (IOException e) {
      // e.printStackTrace();
      // }

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

}
