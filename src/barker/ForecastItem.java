package barker;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ForecastItem {
	@SerializedName("dt_txt")
	private String date;
	@SerializedName("main")
	private Measurements measurements;
	private List<Conditions> weather;
	private Wind wind;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Measurements getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Measurements measurements) {
		this.measurements = measurements;
	}

	public List<Conditions> getWeather() {
		return weather;
	}

	public void setWeather(List<Conditions> weather) {
		this.weather = weather;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
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

		retVal += String.format("%sDATE/TIME: %s\n", indentStr, date);
		retVal += String.format("%s%s\n", indentStr, measurements.toString());
		retVal += String.format("%s%s\n", indentStr, weather.get(0).getDescription());
		retVal += String.format("%s%s\n", indentStr, wind.toString());

		return retVal;
	}
}
