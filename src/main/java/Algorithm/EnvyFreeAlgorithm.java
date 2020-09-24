package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnvyFreeAlgorithm {
  //Holds a list of the items, they will be removed from the list as they are allocated.
  ArrayList<String> items;
  //The order of distribution will just be the order of Agents in the array.
  Agent[] agents;
  //The key is the Algorithm.Agent and the value is a list of the items assigned to that Algorithm.Agent.
  Map<Agent, ArrayList<String>> itemDistribution = new HashMap<>();

  public void setUp() {
    for (Agent agent : agents) {
      itemDistribution.put(agent, new ArrayList<>());
    }
  }

  public Map<Agent, ArrayList<String>> distributor() {
    setUp();
    while (items.size() > 0) {
      for (Agent agent : agents) {
        if (items.size() <= 0) {
          break;
        }
        Agent currentAgent = agent;
        String bestItem = findBestItem(currentAgent);
        //Calls the ArrayList tied to the current agent and adds bestItem to it.
        itemDistribution.get(currentAgent).add(bestItem);
        //Removes item from ArrayList.
        items.remove(bestItem);
      }
    }
    return itemDistribution;
  }

  public String findBestItem (Agent agent) {
    Map<String, Double> itemEvaluations = agent.getItemEvaluations();

    String bestItem = items.get(0);
    double bestItemWorth = itemEvaluations.get(bestItem);
    //If only 1 item left then return that item as the best item.
    if (items.size() < 2) {
      return bestItem;
    }
    //Iterates through the items left and returns the best item for the current agent.
    for (int itemIndex = 1; itemIndex < items.size(); itemIndex++) {
      String nextItemName = items.get(itemIndex);
      double nextItemWorth = itemEvaluations.get(nextItemName);

      if (bestItemWorth < nextItemWorth) {
        bestItem = nextItemName;
        bestItemWorth = nextItemWorth;
      }
    }
    return bestItem;
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
}
