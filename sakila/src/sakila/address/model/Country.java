package sakila.address.model;

public class Country {
	// 생성자 생성
	public Country() {
		
	}
	// 필드 생성
	private int countryId;
	private String country;
	private String lastUpdate;
	// set, get 메소드 생성
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", country=" + country + ", lastUpdate=" + lastUpdate + "]";
	}
	
}
