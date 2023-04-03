import java.util.*;
public class HousingApplication {

	private List<HousingPrice> locations = new ArrayList<HousingPrice>();
	private static HashMap<String, List<HousingPrice>> database;
	private User user;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		database = new HashMap<String, List<HousingPrice>>();
	}
	
	public void setLocations(List<HousingPrice> locations) {
		locations.add();
	}
	
	public void locationType(String region) {
		
	}
	
	public List<HousingPrice> comparePrices(List<String> locations) {
		List<HousingPrice> result = new ArrayList<HousingPrice>();
		return result;
	}
	
	public User getUser() {
		
		return user;
	}

}
