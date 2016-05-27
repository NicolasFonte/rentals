package nicolas.trial.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Rental model class. XML seriarizable for rest resources ( back and forth it
 * as json )
 * 
 * @author nicolasfontenele
 */
@XmlRootElement
public class Rental {

	private String id;
	private String city;
	private String province;
	private String country;
	private String zipCode;
	private String type;
	private boolean hasAirCondition;
	private boolean hasGarden;
	private boolean hasPool;
	private boolean isCloseToBeach;
	private double dailyPrice;
	private String currency;
	private int roomsNumber;

	public Rental() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isHasAirCondition() {
		return hasAirCondition;
	}

	public void setHasAirCondition(boolean hasAirCondition) {
		this.hasAirCondition = hasAirCondition;
	}

	public boolean isHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

	public boolean isHasPool() {
		return hasPool;
	}

	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	public boolean isCloseToBeach() {
		return isCloseToBeach;
	}

	public void setCloseToBeach(boolean isCloseToBeach) {
		this.isCloseToBeach = isCloseToBeach;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(int rowsNumber) {
		this.roomsNumber = rowsNumber;
	}

}
