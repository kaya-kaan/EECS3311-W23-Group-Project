import java.util.*;

public class User {

	private String name;
	private String password;
	private String regionLevel;
	private List<String> selectedRegions = new ArrayList<String>();
	private List<String> selectedVisualizations = new ArrayList<String>();

	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setRegionLevel(String level){
		regionLevel=level;
	}
	
	public List<String> getSelectedRegions() {
		return this.selectedRegions;
	}
	
	public List<String> getVisualizations() {
		return this.selectedVisualizations;
	}
	
	public void selectVisualization(String visual){
		selectedVisualizations.add(visual);
	}
}
