import Algorithm.EnvyFreeAlgorithm;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) throws Exception {
    //First Test.
    Gson gson = new Gson();
    Reader jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/json/EnvyFreeTest.json"));
    EnvyFreeAlgorithm envyFree = gson.fromJson(jsonReader, EnvyFreeAlgorithm.class);
    envyFree.distributor();
    System.out.println(envyFree.toString());

    //Second Test.
    gson = new Gson();
    jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/json/EnvyFreeTest2.json"));
    envyFree = gson.fromJson(jsonReader, EnvyFreeAlgorithm.class);
    envyFree.distributor();
    System.out.println(envyFree.toString());
  }
}
