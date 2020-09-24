package Algorithm;

import java.util.List;
import java.util.Map;

public class Agent {
  private String name;
  //Key is the name of the item, the value it points to is the evaluation of that item
  private Map<String, Double> itemEvaluations;

  public Agent(String name, Map<String, Double> itemEvaluations) {
    this.name = name;
    this.itemEvaluations = itemEvaluations;
  }

  public Map<String, Double> getItemEvaluations() {
    return itemEvaluations;
  }

  public String getName() {
    return name;
  }
}
