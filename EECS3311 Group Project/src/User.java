import java.util.*;

public class User {

	private String name;
	private String password;
	private List<String> selectedRegions = new ArrayList<String>();

	public String getName() {
		return this.name;
	}
	
	public List<String> getSelectedRegions() {
		return this.selectedRegions;
	}
}
