package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DailyData dailyData;
        try {
             dailyData = JsonToObjectConverter.convertJsonToObject("flights_and_forecast.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Schedule schedule = new Schedule(dailyData);
        schedule.printDailySummary();
    }
}