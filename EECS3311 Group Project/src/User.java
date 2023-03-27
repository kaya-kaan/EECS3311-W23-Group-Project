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
	
	public void setRegionLevel(String level){	//Sets Region level, options are either Province or City.
		regionLevel=level;
	}
	
	public List<String> getSelectedRegions() {	//Returns locations selected by the user, all of which should belong in one previously-selected region.
		return this.selectedRegions;
	}
	
	public List<String> getVisualizations() {
		return this.selectedVisualizations;
	}
	
	public void selectVisualization(String visual){
		selectedVisualizations.add(visual);
	}
}
