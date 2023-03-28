import java.util.*;
import java.time.LocalDate;

public class HousingPrice {

	private String regionLevel;
	private double price;
	private LocalDate date;
	private String name;
	
	public String getRegion() {
		return this.regionLevel;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getRegion() {
		return this.regionLevel;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void getDate(LocalDate date) {
		this.date = date;
	}
	
	public void setName() {
		this.name = name;
	}
}
