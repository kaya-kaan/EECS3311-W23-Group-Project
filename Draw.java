public class Draw {
    public TimeSeriesCollection DrawTimeSeries (parameters) { // Someone who knows the database aspect should create the parameters to make the timeseries.
        TimeSeries series = new TimeSeries("Statistic Name");

        // add data to the series based on the parameters.
        series.add(new Month(1, 2022), 10);
        series.add(new Month(2, 2022), 20);
        series.add(new Month(3, 2022), 30);
        series.add(new Month(4, 2022), 40);
        series.add(new Month(5, 2022), 50);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Table Name", // chart title
            "Time interval", // x axis label
            "Statistic Name", // y axis label
            dataset // data
        );

        ChartFrame frame = new ChartFrame("Time Series Chart", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public JFreeChart DrawScatterPlot (TimeSeriesCollection timeseries, string title, string Xlabel, string YLabel) { 

        JFreeChart chart = ChartFactory.createScatterPlot(
              title, // chart title
              Xlabel, // domain axis label
              Ylabel, // range axis label
              timeseries, // dataset
              PlotOrientation.VERTICAL, // orientation
              true, // include legend
              true, // tooltips
              false // urls
        );
	      
        return chart;

    }

  public JFreeChart DrawBarGraph (TimeSeriesCollection timeseries, string title, string Xlabel, string Ylabel) { 
	
        JFreeChart chart = ChartFactory.createBarChart(
              title, // chart title
              Xlabel, // domain axis label
              Ylabel, // range axis label
              timeseries, // data
              PlotOrientation.VERTICAL, // orientation
              true, // include legend
              true, // tooltips
              false // urls
        );

        return chart;
  }

  public JFreeChart DrawLineGraph (TimeSeriesCollection timeseries, string title, string Xlabel, string Ylabel) { 
	
      JFreeChart chart = ChartFactory.createTimeSeriesChart(
          title, // chart title
          Xlabel, // domain axis label
          Ylabel, // range axis label
          timeseries, // data
          true, // include legend
          true, // tooltips
          false // urls
       );

      XYPlot plot = chart.getXYPlot(); 
      StandardXYItemRenderer renderer = new StandardXYItemRenderer();
      plot.setRenderer(renderer); 

      return chart;

  }

}
