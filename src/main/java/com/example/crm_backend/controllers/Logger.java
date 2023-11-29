package com.example.crm_backend.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void log(String response) {
        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDay = dateFormat.format(currentDate);

        // Create directory if it doesn't exist
        Path logDirectoryPath = Paths.get("logs/" + currentDay);
        try {
            Files.createDirectories(logDirectoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the message to the file along with the current date
        try (FileWriter fileWriter = new FileWriter("logs/" + currentDay + "/Log.log", true)) {

            fileWriter.write("\n\n");
            fileWriter.write("Date: " + currentDate + "\n");
            fileWriter.write("Response:\n");
            fileWriter.write(response + "\n");
            fileWriter.write("\n\n<------------------------------------------------------->\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logError(String response) {
        // Log the error in the log file
        log(response);

        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDay = dateFormat.format(currentDate);

        // Create directory if it doesn't exist
        Path logDirectoryPath = Paths.get("main/logs/" + currentDay);
        try {
            Files.createDirectories(logDirectoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the error message to the error file along with the current date
        try (FileWriter fileWriter = new FileWriter("main/logs/" + currentDay + "/LogError.log", true)) {

            fileWriter.write("\n\n");
            fileWriter.write("Date: " + currentDate + "\n");
            fileWriter.write("Response:\n");
            fileWriter.write(response + "\n");
            fileWriter.write("\n\n<------------------------------------------------------->\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
