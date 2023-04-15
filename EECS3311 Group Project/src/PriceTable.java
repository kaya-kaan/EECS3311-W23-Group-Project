package src;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PriceTable extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public PriceTable(List<String>names, List<Double>... prices) {
        // Set up the JFrame
        setTitle("Price Table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the table model
        String[] columnNames = {"Location", "Average Price", "Min Price", "Max Price", "Standard Deviation"};
        model = new DefaultTableModel(columnNames, 0);

        // Fill the table model with data for each location
        
            String locationName = "";

            int i=0;
            
            double minPrice = Double.MAX_VALUE;
            double maxPrice = Double.MIN_VALUE;
            double sumPrices = 0.0;

            // Calculate min, max, and sum of prices
            for (List<Double> priceList : prices) {
            	locationName = names.get(i);
            	for (double price : priceList) {
            		if(price<minPrice) {
            		minPrice = price;
	            	}
	            	if(price>maxPrice) {
	            		maxPrice = price;
	            	}
	                sumPrices += price;
	            
	
		            
            	}
            	double avgPrice = sumPrices / priceList.size();
		        // Calculate standard deviation
	            double sumSquares = 0.0;
	            for (double price : priceList) {
	                sumSquares += Math.pow(price - avgPrice, 2);
	            }
	            double stdDev = Math.sqrt(sumSquares / priceList.size());
            	
            	
            
            // Add a row to the table model for the location
            Object[] rowData = {locationName, avgPrice, minPrice, maxPrice, stdDev};
            model.addRow(rowData);
            i++;
            minPrice = Double.MAX_VALUE;
            maxPrice = Double.MIN_VALUE;
            }
        

        // Set up the JTable with the table model
        table = new JTable(model);

        // Add the JTable to a JScrollPane and add the scroll pane to the JFrame
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Make the JFrame visible
        setVisible(true);
    }

//    public static void main(String[] args) {
//        // Create some data maps for each location
//        LinkedHashMap<Integer, List<String>> dataMap1 = new LinkedHashMap<>();
//        List<Double> data = new ArrayList<Double>(); 
//        List<Double> data2 = new ArrayList<Double>(); 
//        List<String> names = new ArrayList<String>(); 
//        
//        names.add("New York");
//        names.add("London");
//        
//        data.add((double)12);
//        data.add((double)22);
//        data.add((double)42);
//        data.add((double)20);
//        data.add((double)10);
//        data.add((double)6);
//        
//        data2.add((double)10);
//        data2.add((double)22);
//        data2.add((double)30);
//        data2.add((double)20);
//        data2.add((double)10);
//        data2.add((double)3);
//
//        LinkedHashMap<String, Double> dataMap2 = new LinkedHashMap<>();
//        dataMap2.put("London", null);
//        dataMap2.put("London", 12.0);
//        dataMap2.put("London", 18.0);
//        dataMap2.put("London", 2.0);
//        dataMap2.put("London", 30.0);
//
//        // Create the price table with the data maps
//        new PriceTable(names, data,data2);
//    }
}