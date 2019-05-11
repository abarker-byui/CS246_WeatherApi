package barker;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/**
 * Contains information about current weather conditions in a given location.
 */
public class CurrentWeather {
	protected String cityId;
	@SerializedName("name")
	protected String city;
	@SerializedName("main")
	private Map<String, Float> measurements;

	public String getCityId() { return cityId; }

	public void setCityId(String cityId) { this.cityId = cityId; }

	public String getCity() { return city; }

	public void setCity(String city) { this.city = city; }

	public Map<String, Float> getMeasurements() { return measurements; }

	public void setMeasurements(Map<String, Float> measurements) { this.measurements = measurements; }

	public String toString() {
		String retVal = "Weather Conditions:\n\tID: " + cityId + "\n\tName: " + city + "\n\tMeasurements:\n";
		for (Map.Entry<String, Float> pair : measurements.entrySet()) {
			retVal += String.format("\t\t%s: %.1f\n", pair.getKey(), pair.getValue());
		}

		return retVal;
	}

	public String toString(int indent) {
		String retVal = "";
		String indentStr = "";
		for (int i = 0; i < indent; i++) {
			indentStr += "\tF";
		}

		retVal += String.format("%sCURRENT CONDITIONS FOR: %s\n", indentStr, city);
		retVal += indentStr + "Measurements:\n";
		for (Map.Entry<String, Float> pair : measurements.entrySet()) {
			retVal += String.format("$s\t%s: %.1f\n", indentStr, pair.getKey(), pair.getValue());
		}
		return retVal;
	}
}
