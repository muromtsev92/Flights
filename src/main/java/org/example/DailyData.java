package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyData {
    private List<Flight> flights = new ArrayList<>();
    private Map<String, List<Forecast>> forecast = new HashMap<>();

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Map<String, List<Forecast>> getForecast() {
        return forecast;
    }

    public void setForecast(Map<String, List<Forecast>> forecast) {
        this.forecast = forecast;
    }
}
