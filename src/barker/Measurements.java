package barker;

import com.google.gson.annotations.SerializedName;

public class Measurements {
	@SerializedName("temp")
	private float temperature;

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public String toString() {
		return String.format("Temperature: %.1fF", temperature);
	}
}
