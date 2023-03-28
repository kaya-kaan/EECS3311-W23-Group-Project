import java.util.*;
public class HousingDatabase {

	private HashMap<String, List<HousingPrice>> data;
	public void HousingDatabase(){
		data = new HashMap<String, List<HousingPrice>>();
	}
	
	public void addPrice(HousingPrice price){
		data.put(price.name, price);
	}
	
	//Retrieves list of housing prices of a specified location during a specific time span.
	public HashMap<String, List<HousingPrice>> getCityPrices(String location, LocalDate startDate, LocalDate endDate,){	
		LocalDate date = startDate;
		List<HousingPrice> list = new ArrayList<String>();	//list is the list of all housing prices for the specific location.
		List<HousingPrice> priceList = new ArrayList<String>();	//priceList is the return list.
		int index = 0;
		
		list = data.get(location);
		
		//loop through list, and add items to priceList if the date is within the threshold.
		while (index < list.size() || list.get(index).date < endDate){
			if (list.get(index).date >= startDate && list.get(index).date <= endDate){
				priceList.add(list.get(index));
			}
			index++;
		}
		return priceList;
	}

}
