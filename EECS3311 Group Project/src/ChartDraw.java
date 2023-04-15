

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.chart.renderer.category.ScatterRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.DefaultMultiValueCategoryDataset;
import org.jfree.data.statistics.MultiValueCategoryDataset;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import java.text.NumberFormat;
import javax.swing.AbstractAction;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;


public class ChartDraw extends JFrame implements ActionListener{
	
	
	//Map<String, Double> data = Backend.getDataToDraw();

    static String currentGraph;
    private JPanel chartPanel;
    private LinkedHashMap<String, Double> currData = new LinkedHashMap<String, Double>();

	public ChartDraw(String chartType, LinkedHashMap<String, Double> data) {
        JFrame frame = new JFrame();
        createChart(chartType, data);
        currData = data;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(0, 5));
        frame.add(chartPanel, BorderLayout.CENTER);
        
        JPanel panel = new JPanel (new FlowLayout(FlowLayout.RIGHT));
        panel.add(chooseGraphType());
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

    }
	
	private void createChart(String chartType, LinkedHashMap<String, Double> data) {
		
		switch (chartType) {
        case "bar":
        	this.currentGraph = "bar";
            chartPanel = createBarChartPanel(data);
            break;
        case "scatter":
        	this.currentGraph = "scatter";
            chartPanel = createScatterPlotPanel(data);
            break;
        case "line":
        default:
        	this.currentGraph = "line";
            chartPanel = createLineChartPanel(data);
            break;
            
            
    }
// Opens an unnecessary window. Could be used again if switching between graphs is not necessary. 
//    chartPanel.add(chooseGraphType());
//    chartPanel.setPreferredSize(new Dimension(1280, 720));
//    setContentPane(chartPanel);
	}

    private JComboBox chooseGraphType() {
        final JComboBox chooseGraph = new JComboBox();
        final String[] graphTypes = {"Scatter Plot", "Bar Graph", "Line Graph"};
        chooseGraph.setModel(new DefaultComboBoxModel(graphTypes));
        chooseGraph.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphTypes[0].equals(currentGraph)) {
                	ChartDraw scatterDraw = new ChartDraw("scatter", currData);
                	//createChart("scatter", currData);
                } else if (graphTypes[1].equals(currentGraph)){
                	ChartDraw barDraw = new ChartDraw("bar", currData);
                	//createChart("bar", currData);
                } else {
                	ChartDraw lineDraw = new ChartDraw("line", currData);
                	//createChart("line", currData);
                }
            }
        });
        return chooseGraph;
    }


	private JPanel createLineChartPanel(LinkedHashMap<String, Double> data) {
        String chartTitle = "Line Graph Representation";
        String categoryAxisLabel = "Date";
        String valueAxisLabel = "Value";

        CategoryDataset dataset = createCategoryDataset(data);

        JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
        
        // Get the plot and category axis
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis xAxis = plot.getDomainAxis();
        
        // Set the font size and rotation for the x-axis labels
        Font currentFont = xAxis.getTickLabelFont();
        Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), 10);
        xAxis.setTickLabelFont(newFont);
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));

        return new ChartPanel(chart);
    }

    private JPanel createBarChartPanel(LinkedHashMap<String, Double> data) {
        String chartTitle = "Bar Graph Representation";
        String categoryAxisLabel = "Date";
        String valueAxisLabel = "Value";

        CategoryDataset dataset = createCategoryDataset(data);

        JFreeChart chart = ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);
        
        // Get the plot and category axis
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis xAxis = plot.getDomainAxis();
        
        // Set the font size and rotation for the x-axis labels
        Font currentFont = xAxis.getTickLabelFont();
        Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), 10);
        xAxis.setTickLabelFont(newFont);
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));

        return new ChartPanel(chart);
    }

    private JPanel createScatterPlotPanel(LinkedHashMap<String, Double> data) {
    	String chartTitle = "Scatter Plot Representation";
        String xAxisLabel = "Date";
        String yAxisLabel = "Value";

        MultiValueCategoryDataset dataset = createMultiCategoryDataset(data);

        JFreeChart chart = ChartFactory.createLineChart(chartTitle, xAxisLabel, yAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Customize the renderer to create a scatter plot effect
        AbstractCategoryItemRenderer renderer = new ScatterRenderer();
        plot.setRenderer(renderer);

        CategoryAxis xAxis = plot.getDomainAxis();

        // Set the font size and rotation for the x-axis labels
        Font currentFont = xAxis.getTickLabelFont();
        Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), 10);
        xAxis.setTickLabelFont(newFont);
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));
        
        return new ChartPanel(chart);
    }

    private CategoryDataset createCategoryDataset(LinkedHashMap<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Date", entry.getKey());
            //System.out.println(dataset.getValue("Date", entry.getKey().toString()));
        }

        return dataset;
    }
    
    private MultiValueCategoryDataset createMultiCategoryDataset(LinkedHashMap<String, Double> data) {
        DefaultMultiValueCategoryDataset dataset = new DefaultMultiValueCategoryDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            List<Double> values = new ArrayList<>();
            values.add(entry.getValue());
            dataset.add(values, "Date", entry.getKey());
        }

        return dataset;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    

}


