package barker;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecast {
	public City city;
	@SerializedName("list")
	private List<ForecastItem> items;

	public List<ForecastItem> getItems() {
		return items;
	}

	public void setItems(List<ForecastItem> items) {
		this.items = items;
	}

	public String toString() {
		return toString(0);
	}

	public String toString(int indentLevel) {
		String retVal = "";
		String indentStr = "";
		for (int i = 0; i < indentLevel; i++) {
			indentStr += "\t";
		}

		retVal += String.format("%sForecast for: %s\n", indentStr, city.getName());

		for (ForecastItem item : items) {
			retVal += item.toString(indentLevel + 1) + "\n";
		}
		return retVal;
	}
}
