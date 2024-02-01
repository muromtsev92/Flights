package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DailyData dailyData = new DailyData();
        String filePath = "flights_and_forecast.json";

        try {
             dailyData = JsonToObjectConverter.convertJsonToObject(filePath);
        } catch (IOException e) {
            System.out.println("файл с исходными данными отсутствует в указанной директории");
        }

        ScheduleManager scheduleManager = new ScheduleManager(dailyData);
        scheduleManager.printDailySummary();
    }
}