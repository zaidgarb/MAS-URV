import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Example of ManagerAgent
 */
public class ManagerAgent {
    boolean initialized;
    String title;
    String application;
    String aggregation;
    int fuzzyagents;
    HashMap<String, FuzzyAgent> agents;

    public ManagerAgent() {
        initialized = false;
        agents = new HashMap<>();
    }

    public void evaluate(String file) {
        if (initialized) {
        } else {
            System.out.println("Agents not initialized");
        }
    }

    /**
     * properties-based notation is used
     *
     * @param file
     */
    public void initialize(String file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] res = line.split("=");
                switch (res[0]) {
                    case "urv.imas.title":
                        if (initialized) {
                            title = title + " - " + res[1];
                        } else {
                            title = res[1];
                        }
                        break;
                    case "urv.imas.fuzzyagents":
                        if (initialized) {
                            fuzzyagents = fuzzyagents + Integer.parseInt(res[1]);
                        } else {
                            fuzzyagents = Integer.parseInt(res[1]);
                        }
                        break;
                    case "urv.imas.fuzzySettings":
                        String[] ag = res[1].split(",");
                        for (String s : ag) {
                            agents.put(s, new FuzzyAgent());
                        }
                        break;
                    case "urv.imas.application":
                        if (initialized) {
                            application = application + " - " + res[1];
                        } else {
                            application = res[1];
                        }
                        break;
                    case "urv.imas.aggregation":
                        if (initialized) {
                            aggregation = aggregation + " - " + res[1];
                        } else {
                            aggregation = res[1];
                        }
                        break;
                    default:
                        break;
                }
                System.out.println(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialized = true;
    }
}
