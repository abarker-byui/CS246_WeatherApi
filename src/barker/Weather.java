package barker;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import com.google.gson.Gson;

public class Weather {
    private static final String CURRENT_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String API_KEY = "_FILLER_";
    private static Scanner _scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("What city would you like to see the weather for? ");
        String city = _scanner.nextLine();

        CurrentWeather conditions = getWeather(city, API_KEY);
	    if (conditions == null)
	        return;
	    System.out.println(conditions.toString());

	    WeatherForecast forecast = getForecast(city, API_KEY);
	    if (forecast == null)
	        return;
	    System.out.println(forecast.toString());
    }

    /**
     * Get 5 day weather forecast
     * @param city      The city to get the weather forecast for.
     * @param apiKey    The API key to use for retrieving the weather forecast.
     * @return          The forecast conditions in a {@link WeatherForecast} object.
     */
    private static WeatherForecast getForecast(String city, String apiKey) {
        Gson gson = new Gson();
        String queryStr = buildQueryString(city, apiKey);
        if (queryStr == null) {
            return null;
        }

        try {
            URL url = new URL(FORECAST_URL + "?" + queryStr);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
            InputStream responseStream = connection.getInputStream();

            try (Scanner scanner = new Scanner(responseStream)) {
                String response = scanner.useDelimiter("\\A").next();
                System.out.println(response);
                WeatherForecast forecast = gson.fromJson(response, WeatherForecast.class);
                return forecast;
            }
        } catch (MalformedURLException e) {
            System.out.println("\nError building URL");
            return null;
        } catch (IOException e) {
            System.out.println("\nError opening connection");
            return null;
        }
    }

    /**
     * Get the current weather conditions for a specific city
     * @param city      The city to get the weather conditions for.
     * @param apiKey    The API key to use for retrieving the weather conditions.
     * @return          The current conditions in a {@link CurrentWeather} object.
     */
    private static CurrentWeather getWeather(String city, String apiKey) {
        Gson gson = new Gson();
        String queryStr = buildQueryString(city, apiKey);
        if (queryStr == null) {
            return null;
        }

        try {
            URL url = new URL(CURRENT_URL + "?" + queryStr);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
            InputStream responseStream = connection.getInputStream();

            try (Scanner scanner = new Scanner(responseStream)) {
                String response = scanner.useDelimiter("\\A").next();

                CurrentWeather conditions = gson.fromJson(response, CurrentWeather.class);
                return conditions;
            }
        } catch (MalformedURLException e) {
            System.out.println("\nError building URL");
            return null;
        } catch (IOException e) {
            System.out.println("\nError opening connection");
            return null;
        }
    }

    /**
     * Build the necessary query string for connecting to the API.
     * @param city      The city to be referenced in the query string.
     * @param apiKey    The API key for using the API.
     * @return          The complete query string.
     */
    private static String buildQueryString(String city, String apiKey) {
        try {
            return String.format("q=%s&apiKey=%s&units=imperial",
                    URLEncoder.encode(city, StandardCharsets.UTF_8.name()),
                    URLEncoder.encode(apiKey, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            System.out.println("\nError building query string");
            return null;
        }
    }
}
