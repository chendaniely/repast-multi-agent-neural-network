package initializeAgents;

import neuralNetworkABM.AgentABM;

public class InitializeAgents {
  private int numberOfAgents = 0;


  public InitializeAgents() {

  }

  public void generateAgentsToSpaceGrid(Context contex) {
    System.out.println("Creating " + numberOfAgents + " agents");
    for (int agentNumber = 1; agentNumber < numberOfAgents; numberOfAgents++) {
      AgentABM agent =
          new AgentABM(space, grid, agentNumber,
              neuralNetworkABM.GlobalSettings.AGENTVARIABLEXLSLOCATION);
      context.add(agent);
      System.out.println("##### Agent " + agent.getAgentNumber() + " created.");
    }
  }



  // /////////////////////////////////////////////////////////////////////////
  // GETTERS AND SETTERS
  // /////////////////////////////////////////////////////////////////////////

  public int getNumberOfAgents() {
    return numberOfAgents;
  }

  public void setNumberOfAgents(int numberOfAgents) {
    this.numberOfAgents = numberOfAgents;
  }



}
