import Algorithm.Agent;
import Algorithm.EnvyFreeAlgorithm;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;


public class AlgorithmTest {
  private EnvyFreeAlgorithm envyFreeTest;
  @Before
  public void setUp() throws IOException {
    Gson gson = new Gson();
    Reader jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/json/EnvyFreeTest.json"));
    envyFreeTest = gson.fromJson(jsonReader, EnvyFreeAlgorithm.class);
  }

//  @Test (expected = Exception.class)
//  public void checkValidityInvalidEndingRoom() throws Exception {
//    Gson gson = new Gson();
//    Reader jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/invalidEndingRoom.json"));
//    AdventureGame invalidEnding = gson.fromJson(jsonReader, AdventureGame.class);
//    invalidEnding.setUp();
//  }

  @Test
  public void checkFindBestItem() {
    Map<String, Double> itemEvaluations = new HashMap<>();
    itemEvaluations.put("gold", 1.0);
    itemEvaluations.put("diamond", 2.0);
    itemEvaluations.put("phone", 3.0);
    itemEvaluations.put("house", 4.0);

    Agent agent = new Agent("aron", itemEvaluations);
    assertEquals("house", envyFreeTest.findBestItem(agent));
  }
}
