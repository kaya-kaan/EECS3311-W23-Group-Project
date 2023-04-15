
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class HouseHunterCanada extends JFrame implements ItemListener, ActionListener{
	
    private JComboBox<String> provinceComboBox;
    private JComboBox<String> cityComboBox;
    private JComboBox<Integer> startYearComboBox;
    private JComboBox<String> startMonthComboBox;
    private JComboBox<Integer> endYearComboBox;
    private JComboBox<String> endMonthComboBox;
    private JComboBox<String> graphType;
    
    private Map<String, String[]> citiesByProvince;
    
    JFrame frame;
    JLabel titleLabel;
    JRadioButton houseOnlyButton;
    JRadioButton landOnlyButton;
    JRadioButton totalButton;
    ButtonGroup radioButtonGroup = new ButtonGroup();
    JLabel provinceLabel;
    JLabel cityLabel;
    JLabel fromLabel;
    JLabel toLabel;
    JButton runButton;

    static String nhpi; 
    
    public HouseHunterCanada() {
    	initialize();
    }
    private void initialize() {
        //setTitle("HouseHunter Canada");
        frame = new JFrame ("HouseHunter Canada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(500, 500);
       
        setLayout(new BorderLayout());

        initializeCitiesByProvince();

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        // Title label
        
        titleLabel = new JLabel("HouseHunter Canada");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints titleLabelConstraints = new GridBagConstraints();
        titleLabelConstraints.gridx = 0;
        titleLabelConstraints.gridy = 0;
        titleLabelConstraints.gridwidth = 3;
        titleLabelConstraints.insets = new Insets(20, 0, 20, 0);
        mainPanel.add(titleLabel, titleLabelConstraints);

        // Radio buttons
        JPanel radioButtonPanel = new JPanel();
        totalButton = new JRadioButton("Total"); 
        houseOnlyButton = new JRadioButton("House Only");
        landOnlyButton = new JRadioButton("Land Only");
      
        radioButtonGroup.add(totalButton);
        radioButtonGroup.add(houseOnlyButton);
        radioButtonGroup.add(landOnlyButton);
        
        totalButton.addItemListener(this);
        houseOnlyButton.addItemListener(this);
        landOnlyButton.addItemListener(this);
        
        radioButtonPanel.add(totalButton);
        radioButtonPanel.add(houseOnlyButton);
        radioButtonPanel.add(landOnlyButton);
        GridBagConstraints radioButtonConstraints = new GridBagConstraints();
        radioButtonConstraints.gridx = 0;
        radioButtonConstraints.gridy = 1;
        radioButtonConstraints.gridwidth = 3;
        radioButtonConstraints.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(radioButtonPanel, radioButtonConstraints);

        // Province label and combo box
        provinceLabel = new JLabel("Province:");
        GridBagConstraints provinceLabelConstraints = new GridBagConstraints();
        provinceLabelConstraints.gridx = 0;
        provinceLabelConstraints.gridy = 2;
        provinceLabelConstraints.anchor = GridBagConstraints.LINE_END;      
        mainPanel.add(provinceLabel, provinceLabelConstraints);

        provinceComboBox = new JComboBox<>(citiesByProvince.keySet().toArray(new String[0]));
        provinceComboBox.addActionListener(new ProvinceSelectionListener());
        GridBagConstraints provinceComboBoxConstraints = new GridBagConstraints();
        provinceComboBoxConstraints.gridx = 1;
        provinceComboBoxConstraints.gridy = 2;
        provinceComboBoxConstraints.anchor = GridBagConstraints.CENTER;
        provinceComboBox.setPrototypeDisplayValue("XXXXXXXXXXX");
        mainPanel.add(provinceComboBox, provinceComboBoxConstraints);

        // City label and combo box
        cityLabel = new JLabel("City:");
        GridBagConstraints cityLabelConstraints = new GridBagConstraints();
        cityLabelConstraints.gridx = 0;
        cityLabelConstraints.gridy = 3;
        cityLabelConstraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(cityLabel, cityLabelConstraints);

        cityComboBox = new JComboBox<>();
        updateCityComboBox();
        GridBagConstraints cityComboBoxConstraints = new GridBagConstraints();
        cityComboBoxConstraints.gridx = 1;
        cityComboBoxConstraints.gridy = 3;
        cityComboBoxConstraints.anchor = GridBagConstraints.CENTER;
        cityComboBox.setPrototypeDisplayValue("XXXXXXXXXXX");
        mainPanel.add(cityComboBox, cityComboBoxConstraints);

        // Starting Year and Month
        fromLabel = new JLabel("From:");
        GridBagConstraints startYearLabelConstraints = new GridBagConstraints();
        startYearLabelConstraints.gridx = 0;
        startYearLabelConstraints.gridy = 4;
        startYearLabelConstraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(fromLabel, startYearLabelConstraints);

        startYearComboBox = new JComboBox<>(getYears());
        GridBagConstraints startYearComboBoxConstraints = new GridBagConstraints();
        startYearComboBoxConstraints.gridx = 1;
        startYearComboBoxConstraints.gridy = 4;
        startYearComboBoxConstraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(startYearComboBox, startYearComboBoxConstraints);

        toLabel = new JLabel("To:");
        GridBagConstraints startMonthLabelConstraints = new GridBagConstraints();
        startMonthLabelConstraints.gridx = 0;
        startMonthLabelConstraints.gridy = 5;
        startMonthLabelConstraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(toLabel, startMonthLabelConstraints);

        startMonthComboBox = new JComboBox<>(getMonths());
        GridBagConstraints startMonthComboBoxConstraints = new GridBagConstraints();
        startMonthComboBoxConstraints.gridx = 2;
        startMonthComboBoxConstraints.gridy = 4;
        startMonthComboBoxConstraints.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(startMonthComboBox, startMonthComboBoxConstraints);


        endYearComboBox = new JComboBox<>(getYears());
        GridBagConstraints endYearComboBoxConstraints = new GridBagConstraints();
        endYearComboBoxConstraints.gridx = 1;
        endYearComboBoxConstraints.gridy = 5;
        endYearComboBoxConstraints.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(endYearComboBox, endYearComboBoxConstraints);


        endMonthComboBox = new JComboBox<>(getMonths());
        GridBagConstraints endMonthComboBoxConstraints = new GridBagConstraints();
        endMonthComboBoxConstraints.gridx = 2;
        endMonthComboBoxConstraints.gridy = 5;
        endMonthComboBoxConstraints.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(endMonthComboBox, endMonthComboBoxConstraints);
        
        
        
        graphType = new JComboBox<>(getGraphTypes());
        GridBagConstraints graphTypeConstraints = new GridBagConstraints();
        graphTypeConstraints.gridx = 0;
        graphTypeConstraints.gridy = 7;
        graphTypeConstraints.gridwidth = 2;

        // Run button
        runButton = new JButton("RUN");
        runButton.addActionListener(this);
        GridBagConstraints runButtonConstraints = new GridBagConstraints();
        runButtonConstraints.gridx = 0;
        runButtonConstraints.gridy = 8;
        runButtonConstraints.gridwidth = 2;
        runButtonConstraints.insets = new Insets(20, 0, 20, 0);
        
        mainPanel.add(graphType);
        mainPanel.add(runButton, runButtonConstraints);


        frame.add(mainPanel, BorderLayout.CENTER);
        pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeCitiesByProvince() {
        citiesByProvince = new HashMap<>();
        citiesByProvince.put("Canada",new String[] {"None"});
        citiesByProvince.put("Atlantic Region",new String[] {"None"});
        citiesByProvince.put("Newfoundland and Labrador",new String[] {"None", "St. John's"});
        citiesByProvince.put("Prince Edward Island",new String[] {"None", "Charlottetown"});
        citiesByProvince.put("Nova Scotia",new String[] {"None", "Halifax"});
        citiesByProvince.put("New Brunswick",new String[] {"None", "Saint John, Fredricton, and Moncton"});
        citiesByProvince.put("Quebec",new String[] {"None", "Quebec", "Sherbrooke", "Trois-Rivieres", "Montreal","Ottawa-Gatineau, QC"});
        citiesByProvince.put("Ontario", new String[]{"None", "Toronto", "Ottawa", "Hamilton", "Ottawa-Gatineau, ON", "Oshawa", "St. Catherines-Niagara", 
        											 "Kitchener-Cambridge-Waterloo", "Guelph","London","Windsor","Greater Sudbury"});
        citiesByProvince.put("Prairie Region", new String[]{"None"});
        citiesByProvince.put("Manitoba", new String[]{"None", "Winnipeg"});
        citiesByProvince.put("Saskatchewan", new String[]{"None", "Regina", "Saskatoon"});
        citiesByProvince.put("Alberta", new String[]{"None", "Calgary", "Edmonton"});
        citiesByProvince.put("British Columbia", new String[]{"None", "Kelowna", "Vancouver", "Victoria"});
        
    }

    private String[] getGraphTypes() {
    	String[] graphs = {"Select Graph Type", "Scatter Plot", "Line Graph", "Bar Graph"};
    	return graphs;
    }
    private void updateCityComboBox() {
        String selectedProvince = (String) provinceComboBox.getSelectedItem();
        String[] cities = citiesByProvince.get(selectedProvince);
        cityComboBox.setModel(new DefaultComboBoxModel<>(cities));
    }
    private Integer[] getYears() {
        Integer[] years = new Integer[42];
        for (int i = 0; i < 42; i++) {
            years[i] = 1981 + i;
        }
        return years;
    }
    public static String[] getMonths() {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return months;
    }
    private class ProvinceSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateCityComboBox();
        }
    }
    
   public void itemStateChanged(ItemEvent e) {
	   Object source = e.getItemSelectable();
	   
	   if (source == totalButton)
	   {
		   //System.out.println("Total");
		   nhpi = "Total (house and land)";  
	   } else if (source == houseOnlyButton)
	   {
		   //System.out.println("House Only");
		   nhpi = "House only";
	   } else if (source == landOnlyButton)
	   {
		   //System.out.println("Land Only");
		   nhpi = "Land only";
	   }
	   
   }
   
   public static boolean monthsInOrder (String month1, String month2)
    {
       int month1Number = getMonthNumber(month1);
       int month2Number = getMonthNumber(month2);
       return month1Number >= month2Number;
   }

   private static int getMonthNumber (String monthName){
	   String[] monthNames = getMonths();
       
       for (int i = 0; i < monthNames.length; i++) {
    	   if(monthName.equalsIgnoreCase(monthNames[i])) {
               return i + 1;
           }
       }
       return -1;
       }
   
   
   public void actionPerformed(ActionEvent e) {
	   
	   if (nhpi == null)
		   JOptionPane.showMessageDialog(frame, "Please choose one option from House only, Land only, and Total", "NHPI OPTION ERROR", JOptionPane.ERROR_MESSAGE);
	   else {
		   int startYear = (int) startYearComboBox.getSelectedItem();
	       String startMonth = (String) startMonthComboBox.getSelectedItem();
	       
	       int endYear = (int) endYearComboBox.getSelectedItem();
	       String endMonth = (String) endMonthComboBox.getSelectedItem();
	       
	       if (endYear < startYear)
			   JOptionPane.showMessageDialog(frame, "The end year cannot be before starting year", "END YEAR ERROR", JOptionPane.ERROR_MESSAGE);
	       else if (endYear == startYear && !monthsInOrder(endMonth, startMonth))
	       {
			   JOptionPane.showMessageDialog(frame, "The end date cannot be before starting date", "END MONTH ERROR", JOptionPane.ERROR_MESSAGE);
	       }
	       else {
	    	   SimpleDateFormat monthFormatter = new SimpleDateFormat("MM");
		       SimpleDateFormat monthParser = new SimpleDateFormat("MMMM");
		       
		       
		       
		       try {
		           String startMonthNumber = monthFormatter.format(monthParser.parse(startMonth));
		           String endMonthNumber = monthFormatter.format(monthParser.parse(endMonth));
		           
		           String formattedStartDate = String.format("%d-%s-01", startYear, startMonthNumber);
		           String formattedEndDate = String.format("%d-%s-01", endYear, endMonthNumber);
		           
		           //System.out.println("NHPI: " + nhpi);
		           
		           String city;
		           if (((String)cityComboBox.getSelectedItem()).equals("None")) {
		           	city = "";
		           } else
		           	city = (String)cityComboBox.getSelectedItem();
		           	
		           Backend process = new Backend((String)provinceComboBox.getSelectedItem(), city , formattedStartDate, formattedEndDate, nhpi, (String)graphType.getSelectedItem());
		           process.run();
		           
		           //System.out.println(formattedStartDate);
		           //System.out.println(formattedEndDate);
		       } catch (Exception ex) {
		           ex.printStackTrace();
		       }
	       }

	       
	       
	   }
	   
       
       
       
   }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HouseHunterCanada());
    }
}

   

