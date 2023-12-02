package com.example.crm_backend.controllers;

import io.sentry.Sentry;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is used to log the responses to the client. It contains two methods, one for logging the response and another for logging the error response.
 */
public class Logger {

    /**
     * Logs the response to the log file. The log file is located in the logs folder by date.
     * @param response  The response to be logged.
     */
    public static void log(String response) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDay = dateFormat.format(currentDate);

        Path logDirectoryPath = Paths.get("logs/" + currentDay);
        try {
            Files.createDirectories(logDirectoryPath);
        } catch (IOException e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter("logs/" + currentDay + "/Log.log", true)) {

            fileWriter.write("\n\n");
            fileWriter.write("Date: " + currentDate + "\n");
            fileWriter.write("Response:\n");
            fileWriter.write(response + "\n");
            fileWriter.write("\n\n<------------------------------------------------------->\n");
        } catch (IOException e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
    }

    /**
     * Logs the response to the error log file. The log file is located in the logs folder by date.
     * @param response  The response to be logged.
     */
    public static void logError(String response) {
        log(response);

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDay = dateFormat.format(currentDate);

        Path logDirectoryPath = Paths.get("main/logs/" + currentDay);
        try {
            Files.createDirectories(logDirectoryPath);
        } catch (IOException e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter("logs/" + currentDay + "/LogError.log", true)) {

            fileWriter.write("\n\n");
            fileWriter.write("Date: " + currentDate + "\n");
            fileWriter.write("Response:\n");
            fileWriter.write(response + "\n");
            fileWriter.write("\n\n<------------------------------------------------------->\n");
        } catch (IOException e) {
            Sentry.captureException(e);
            e.printStackTrace();
        }
    }
}
