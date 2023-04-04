package project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;

public class Backend {

	private String province;
	private String city;
	private String startDate;
	private String endDate;
	private String nhpi;
	private HashMap<String, Double> dataToDraw = new HashMap<String, Double>();
	
	public Map<String, Double> getDataToDraw() {
		return dataToDraw;
	}

	public Backend (String newProvince, String newCity, String newStartDate, String newEndDate, String newNhpi) {
		this.province = newProvince;
		this.city = newCity;
		this.startDate = newStartDate;
		this.endDate = newEndDate;
		this.nhpi = newNhpi;
	}
	
    public void run() {
        String csvFileName = "ProjectDB.csv";
        boolean startReached = false;
        boolean endReached = false;
        
        
        try {
            String csvFilePath = Paths.get("").toAbsolutePath().resolve(csvFileName).toString();
            Map<String, String[]> dataMap = readCSVDataToHashMap(csvFilePath);
            
            TreeMap<String,String[]> sortedMap = sortByNumericKeys(dataMap);
            
            for (Map.Entry<String, String[]> entry : sortedMap.entrySet()) {
            	
            	if (valueContainsString(sortedMap, entry.getKey(), startDate))
            	{
            		startReached = true;
            	}
            	if (startReached) {
            		if (valueContainsString(sortedMap, entry.getKey(), this.province) && valueContainsString(sortedMap, entry.getKey(), this.city)
                			&& valueContainsString(sortedMap, entry.getKey(), this.nhpi)) { 
                		if (this.city.equals("") && (!valueContainsString(sortedMap, entry.getKey(), ":"))) {

                			dataToDraw.put(entry.getValue()[0], Double.parseDouble(entry.getValue()[entry.getValue().length-1]));
                			//dataToDraw.put(entry.getKey(), Double.parseDouble(entry.getValue()[entry.getValue().length-1]));
                			System.out.println(entry.getValue()[0] + " , " + Double.parseDouble(entry.getValue()[entry.getValue().length-1]));
                		}
                		
                		if (!this.city.equals("")) {
                			
                			dataToDraw.put(entry.getValue()[0], Double.parseDouble(entry.getValue()[entry.getValue().length-1]));
                			//dataToDraw.put(entry.getKey(), Double.parseDouble(entry.getValue()[entry.getValue().length-1]));
                			System.out.println(entry.getKey() + " , " + entry.getValue()[0] + " , " + Double.parseDouble(entry.getValue()[entry.getValue().length-1]));
                		}
                	}
            	}
            	
            	if (valueContainsString(sortedMap, entry.getKey(), endDate))
            	{
            		endReached = true;
            		startReached = false;
            	}
            	if (endReached) {
            		break;
            	}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        ChartDraw draw = new ChartDraw("bar", dataToDraw);
        draw.pack();
        draw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        draw.setVisible(true);
        
        
        
    }
    
    
    public static TreeMap<String, String[]> sortByNumericKeys(Map<String, String[]> hashMap) {
        Comparator<String> keyComparator = new Comparator<String>() {
            @Override
            public int compare(String key1, String key2) {
                return Integer.compare(Integer.parseInt(key1), Integer.parseInt(key2));
            }
        };

        TreeMap<String, String[]> sortedMap = new TreeMap<>(keyComparator);
        sortedMap.putAll(hashMap);
        return sortedMap;
    }
    
    
    
    
    public static boolean valueContainsString(TreeMap<String, String[]> dataMap, String key, String searchString) {
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
