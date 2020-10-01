package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MMSAlgorithm {
  private static double MMS_ALLOCATION_TARGET = 0.5;
  //Holds a list of the items, they will be removed from the list as they are allocated.
  ArrayList<String> items;
  //The order of distribution will just be the order of Agents in the array. Agents will be removed as the algorithm finds their allocation.
  ArrayList<Agent> agents;
  //The key is the Algorithm.Agent and the value is a list of the items assigned to that Algorithm.Agent.
  Map<Agent, ArrayList<String>> itemDistribution = new HashMap<>();

  public void setUp() {
    itemDistribution = new HashMap<>();
    //Initializes the Map of agents and assigns them empty arraylists.
    for (Agent agent : agents) {
      itemDistribution.put(agent, new ArrayList<>());
    }
    //Scaling of the agent's distributions.
    for (Agent agent : agents) {
      Map<String, Double> itemEvaluations = agent.getItemEvaluations();
      //Find the total of the evaluations
      double evaluationsTotal = getTotalValues(itemEvaluations);
      //Divide the number of agents by this total to find a scale.
      double scale = agents.size() / evaluationsTotal;
      //Use the scale to update all the evaluations of the current agent.
      for (String item : itemEvaluations.keySet()) {
        itemEvaluations.put(item, itemEvaluations.get(item) * scale);
      }
    }
  }

  public Map<Agent, ArrayList<String>> mmsDistribution() {
    setUp();
    //Looks through unallocated item list to allocate the items to the agents which value them as >= MMS_ALLOCATION_TARGET.
    ArrayList<String> toRemove = new ArrayList<>();
    for (String item : items) {
      //Looks through every agent's evaluation of the item.
      for (Agent currentAgent : agents) {
        Map<String, Double> itemEvaluations = currentAgent.getItemEvaluations();
        //If the value for the item is >= MMS_ALLOCATION_TARGET, then add the item to the agent's bundle
        //and remove the item and agent from the distribution.
        if (itemEvaluations.get(item) >= MMS_ALLOCATION_TARGET) {
          itemDistribution.get(currentAgent).add(item);
          toRemove.add(item);
          agents.remove(currentAgent);
          break;
        }
      }
    }
    //Removes the items here to avoid ConcurrentModificationException.
    for (String remove : toRemove) {
      items.remove(remove);
    }
    //Second part just does EF1 allocation for the rest of the items and agents.
    EnvyFreeAlgorithm envyFreeAlgorithm = new EnvyFreeAlgorithm(items, agents);
    Map<Agent, ArrayList<String>> envyFreeDistribution = envyFreeAlgorithm.distributor();
    //Updating remaining agent's bundles
    for (Agent currentAgent : envyFreeDistribution.keySet()) {
      itemDistribution.put(currentAgent, envyFreeDistribution.get(currentAgent));
    }
    return itemDistribution;
  }

  public String toString() {
    String toString = "";
    for (Agent agent : itemDistribution.keySet()) {
      String agentName = agent.getName();
      ArrayList<String> agentItems = itemDistribution.get(agent);

      toString += agentName + ": " + agentItems + "\n";
    }
    return toString;
  }

  private double getTotalValues(Map<String, Double> itemEvaluations) {
    double evaluationsTotal = 0;
    //Find the total of the evaluations
    for (double value : itemEvaluations.values()) {
      evaluationsTotal += value;
    }
    return evaluationsTotal;
  }
}
