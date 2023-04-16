
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PriceTable extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JComboBox chooseTableType(int Type) {
        final JComboBox chooseTable = new JComboBox();
        final String[] tableTypes = {"Choose Table Type","Detailed", "Raw Data"};
        chooseTable.setModel(new DefaultComboBoxModel(tableTypes));
        chooseTable.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableTypes[1].equals(chooseTable.getSelectedItem())) {
                	if(Type == 1) {
                		setVisible(false);
                	}
                } else if (tableTypes[2].equals(chooseTable.getSelectedItem())){
                	if(Type == 0) {
                		setVisible(false);
                	}
                }
            }
        });
        return chooseTable;
    }
    
    public PriceTable(String name,String nhpi,LinkedHashMap<String, Double> data) {
    	JFrame frame = new JFrame();
    	setTitle("Raw Data Table");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set up the table model
        String[] columnNames = {"Date", "Location", "Data Type", "Price Value"};
        model = new DefaultTableModel(columnNames, 0);

        // Fill the table model with data for each location
        
            String locationName = name;
            List<String> DateList = new ArrayList<String>(data.keySet());
            List<Double> ValueList = new ArrayList<Double>(data.values());
            
            int i=0;
            double minPrice = Double.MAX_VALUE;
            double maxPrice = Double.MIN_VALUE;
            double sumPrices = 0.0;

            // Calculate min, max, and sum of prices
            for (String date : DateList) {
            	
             	
	            // Add a row to the table model for the location
	            Object[] rowData = {date, locationName, nhpi, ValueList.get(i)};
	            model.addRow(rowData);
	            i++;
            }
        

        // Set up the JTable with the table model
        table = new JTable(model);

        // Add the JTable to a JScrollPane and add the scroll pane to the JFrame
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        
        JPanel panel = new JPanel (new FlowLayout(FlowLayout.RIGHT));
        panel.add(chooseTableType(1));
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
		frame.setLocationRelativeTo(null);

        // Make the JFrame visible
        setVisible(true);
    }
    
    
    public PriceTable(List<String>names, List<Double>... prices) {
    	JFrame frame = new JFrame();
        // Set up the JFrame
        setTitle("Price Table");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        
        JPanel panel = new JPanel (new FlowLayout(FlowLayout.RIGHT));
        panel.add(chooseTableType(0));
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
		frame.setLocationRelativeTo(null);
        setVisible(true);
    }

}
