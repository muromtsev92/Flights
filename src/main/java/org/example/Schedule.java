package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    DailyData dailyData;
    private static Map<String, Integer> timeZoneCorrection;

    static {
        timeZoneCorrection = new HashMap<>();
        timeZoneCorrection.put("moscow", -3);
        timeZoneCorrection.put("novosibirsk", -7);
        timeZoneCorrection.put("omsk", -6);
    }

    public Schedule(DailyData dailyData) {
        this.dailyData = dailyData;
    }

    public void printDailySummary(){
        for (Flight f: dailyData.getFlights()){
            if (checkFlight(f, dailyData.getForecast())) {
                System.out.println(f.getNo() + " | " + f.getFrom() + " -> " + f.getTo() + " | по расписанию");
            } else {
                System.out.println(f.getNo() + " | " + f.getFrom() + " -> " + f.getTo() + " | отменен");
            }
        }
    }

    public boolean checkFlight(Flight flight, Map<String, List<Forecast>> forecast){
        Integer gmtDepartureTime = flight.getDeparture() + timeZoneCorrection.get(flight.getFrom());
        Integer gmtArrivalTime = gmtDepartureTime + flight.getDuration();
        Integer localDepartureTime = flight.getDeparture();
        Integer localArrivalTime = gmtArrivalTime - timeZoneCorrection.get(flight.getTo());
        Forecast weather_from = forecast.get(flight.getFrom()).get(localDepartureTime);
        Forecast weather_to = forecast.get(flight.getTo()).get(localArrivalTime);
        if(weatherIsGood(weather_from) && weatherIsGood(weather_to)){
            return true;
        }
        return false;
    }

    public boolean weatherIsGood(Forecast forecast){
        if(forecast.getWind() < 30 && forecast.getVisibility() > 200){
            return true;
        }
        return false;
    }

}
