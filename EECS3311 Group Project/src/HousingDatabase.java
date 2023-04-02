import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleCSVToHashMap {

    public static void main(String[] args) {
        String csvFileName = "ProjectDB.csv";

        try {
            String csvFilePath = Paths.get("").toAbsolutePath().resolve(csvFileName).toString();
            Map<String, String[]> dataMap = readCSVDataToHashMap(csvFilePath);
            
            for (Map.Entry<String, String[]> entry : dataMap.entrySet()) {
            	//if (entry.getValue()[1].contains("Ontario")) {
            	if (valueContainsString(dataMap, entry.getKey(), "Ontario")) { // we have to change the Ontario to user's input
            		System.out.println("Key: " + entry.getKey() + ", Value: " + Arrays.toString(entry.getValue()));
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean valueContainsString(Map<String, String[]> dataMap, String key, String searchString) {
        if (!dataMap.containsKey(key)) {
            return false;
        }

        String[] values = dataMap.get(key);
        for (String value : values) {
            if (value.contains("Quebec part")) {
            	return false;
            } else if (value.contains(searchString)) {
            	return true;
            }
        }
        return false;
    }

    private static Map<String, String[]> readCSVDataToHashMap(String csvFilePath) throws IOException {
        Map<String, String[]> dataMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String key = row[0];
                String[] value = Arrays.copyOfRange(row, 1, row.length);
                dataMap.put(key, value);
            }
        }

        return dataMap;
    }
}
