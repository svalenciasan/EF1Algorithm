import Algorithm.EnvyFreeAlgorithm;
import Algorithm.MMSAlgorithm;
import com.google.gson.Gson;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) throws Exception {
    //First test for EnvyFreeAlgorithm.
    Gson gson = new Gson();
    Reader jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/json/EnvyFreeTest.json"));
    EnvyFreeAlgorithm envyFree = gson.fromJson(jsonReader, EnvyFreeAlgorithm.class);
    envyFree.distributor();
    System.out.println(envyFree.toString());

    //Second test for EnvyFreeAlgorithm.
    gson = new Gson();
    jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/json/EnvyFreeTest2.json"));
    envyFree = gson.fromJson(jsonReader, EnvyFreeAlgorithm.class);
    envyFree.distributor();
    System.out.println(envyFree.toString());

    //First test for MMSAlgorithm.
    gson = new Gson();
    jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/json/MMSAlgorithmTest.json"));
    MMSAlgorithm mmsAlgorithm = gson.fromJson(jsonReader, MMSAlgorithm.class);
    mmsAlgorithm.mmsDistribution();
    System.out.println(mmsAlgorithm.toString());

    //Second test for MMSAlgorithm
    gson = new Gson();
    jsonReader = Files.newBufferedReader(Paths.get("src/main/resources/json/EnvyFreeTest2.json"));
    mmsAlgorithm = gson.fromJson(jsonReader, MMSAlgorithm.class);
    mmsAlgorithm.mmsDistribution();
    System.out.println(mmsAlgorithm.toString());
  }
}
