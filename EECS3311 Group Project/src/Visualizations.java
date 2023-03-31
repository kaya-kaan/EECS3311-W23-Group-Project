
public class Visualizations {
// import the Jfreechart library 
	private HousingDatabase database;
	
	public ArrayList<ChartFrame> loadVisualizations(User name) {
		ArrayList<ChartFrame> list = new ArrayList<ChartFrame>();
		int length = name.selectedVisualizations.length; 
		for (int i = 0; i < length; i++) { 
			if (name.selectedVisualizations[i] == "piechart") { 
				ChartFrame piechart = createPieChart(Table) // make piechart from the original table of the data	
				list.add(piechart);
			} 
			// else if statements for other visuals (bar graph, line chart, scatter plot etc) 
			
		}
			
	}
	
	public void tallySelections() { // Need to figure out a threshhold value
		int length = name.selectedVisualizations.length; 
		if (length > threshold) // error message
		else // good 
	}
	
	public void ClearVisuals(ArrayList<ChartFrame> list) { // clear all visualizations
		int length = list.length;
		for (int i = 0; i < length; i++) { 
			list[i].setVisible(false);	
		}
	}
	
	public void ClearVisual(ChartFrame visual) { // clear one specific visual 
		visual.setVisible(false);
		
	}
		
	public void presentVisualization(Chartframe visual){ // make a specified visualization visible on the frame
		visual.pack(); 
		visual.setVisible(true);
	}
	
	public void presentVisualizations(ArrayList<ChartFrame> list) { // make all visualizations visible on the frame
		int length = list.length; 
		for (int i = 0; i < length; i++) { 
			list[i].setVisible(true);	
		}
	}
}
