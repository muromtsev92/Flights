package org.example;

import java.util.HashMap;
import java.util.Map;

public class ScheduleManager {
    DailyData dailyData;
    private static final Map<String, Integer> timeZoneCorrection;

    static {
        timeZoneCorrection = new HashMap<>();
        timeZoneCorrection.put("moscow", -3);
        timeZoneCorrection.put("novosibirsk", -7);
        timeZoneCorrection.put("omsk", -6);
    }

    public ScheduleManager(DailyData dailyData) {
        this.dailyData = dailyData;
    }

    public void printDailySummary(){
        for (Flight f: dailyData.getFlights()){
            if (checkFlight(f)) {
                System.out.println(f.getNo() + " | " + f.getFrom() + " -> " + f.getTo() + " | по расписанию");
            } else {
                System.out.println(f.getNo() + " | " + f.getFrom() + " -> " + f.getTo() + " | отменен");
            }
        }
    }

    public boolean checkFlight(Flight flight){
        int gmtDepartureTime = flight.getDeparture() + timeZoneCorrection.get(flight.getFrom());
        int localDepartureTime = flight.getDeparture();

        int gmtArrivalTime = gmtDepartureTime + flight.getDuration();
        int localArrivalTime = gmtArrivalTime - timeZoneCorrection.get(flight.getTo());

        Forecast weather_from = this.dailyData.getForecast().get(flight.getFrom()).get(localDepartureTime);
        Forecast weather_to = this.dailyData.getForecast().get(flight.getTo()).get(localArrivalTime);

        return weatherIsGood(weather_from) && weatherIsGood(weather_to);
    }

    public boolean weatherIsGood(Forecast forecast){
        return forecast.getWind() < 30 && forecast.getVisibility() > 200;
    }
}
