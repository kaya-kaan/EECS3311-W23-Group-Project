

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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



public class ChartDraw extends JFrame {
	
	//Map<String, Double> data = Backend.getDataToDraw();

    public ChartDraw(String chartType, LinkedHashMap<String, Double> data) {
        JPanel chartPanel;
        switch (chartType) {
            case "bar":
                chartPanel = createBarChartPanel(data);
                break;
            case "scatter":
                chartPanel = createScatterPlotPanel(data);
                break;
            case "line":
            default:
                chartPanel = createLineChartPanel(data);
                break;
        }

        chartPanel.setPreferredSize(new Dimension(5000, 1125));
        setContentPane(chartPanel);
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
    

}


