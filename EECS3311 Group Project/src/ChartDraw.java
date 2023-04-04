package project;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

//import project.Backend;

public class ChartDraw extends JFrame {
	
	//Map<String, Double> data = Backend.getDataToDraw();

    public ChartDraw(String chartType, HashMap<String, Double> data) {
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

        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }



	private JPanel createLineChartPanel(HashMap<String, Double> data) {
        String chartTitle = "Line Graph Representation";
        String categoryAxisLabel = "Key";
        String valueAxisLabel = "Value";

        CategoryDataset dataset = createCategoryDataset(data);

        JFreeChart chart = ChartFactory.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel createBarChartPanel(HashMap<String, Double> data) {
        String chartTitle = "Bar Graph Representation";
        String categoryAxisLabel = "Key";
        String valueAxisLabel = "Value";

        CategoryDataset dataset = createCategoryDataset(data);

        JFreeChart chart = ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }

    private JPanel createScatterPlotPanel(HashMap<String, Double> data) {
        String chartTitle = "Scatter Plot Representation";
        String xAxisLabel = "Key";
        String yAxisLabel = "Value";

        XYDataset dataset = createXYDataset(data);

        JFreeChart chart = ChartFactory.createScatterPlot(chartTitle, xAxisLabel, yAxisLabel,
                dataset, PlotOrientation.VERTICAL, true, true, false);

        return new ChartPanel(chart);
    }

    private CategoryDataset createCategoryDataset(HashMap<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Date", entry.getKey());
            System.out.println(dataset.getValue("Date", entry.getKey().toString()));
        }

        return dataset;
    }

    private XYDataset createXYDataset(HashMap<String, Double> data) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] seriesData = new double[2][data.size()];
        int i = 0;

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            seriesData[0][i] = Double.parseDouble(entry.getKey());
            seriesData[1][i] = entry.getValue();
            i++;
        }

        dataset.addSeries("Date", seriesData);
        return dataset;
    }

//    public static void main(String[] args) {
//        HashMap<String, Double> hashMap = new HashMap<>();
//        hashMap.put("1", 3.0);
//        hashMap.put("2", 7.0);
//        hashMap.put("3", 5.0);
//        hashMap.put("4", 8.0);
//        hashMap.put("5", 4.0);
//
//        ChartDraw lineChart = new ChartDraw("line", hashMap);
//        lineChart.pack();
//        lineChart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        lineChart.setVisible(true);
//
//        ChartDraw barChart = new ChartDraw("bar", hashMap);
//        barChart.pack();
//        barChart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        barChart.setVisible(true);
//
//        ChartDraw scatterPlot = new ChartDraw("scatter", hashMap);
//        scatterPlot.pack();
//        scatterPlot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        scatterPlot.setVisible(true);
//    }
}


