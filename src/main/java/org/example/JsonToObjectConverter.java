package org.example;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonToObjectConverter {
    public static DailyData convertJsonToObject(String filePath) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jsonContent.toString(), DailyData.class);
        }
    }




}
