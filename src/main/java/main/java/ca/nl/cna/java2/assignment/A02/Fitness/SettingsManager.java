package main.java.ca.nl.cna.java2.assignment.A02.Fitness;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SettingsManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int headerLength = 30;
        int choice = -1;
        Path path = Paths.get("./logs/xml/fitnessSettings/Settings.xml");
        Settings settings = null;

        // load settings data if settings.xml already exists
        if (Files.exists(path)) {
            try (BufferedReader input = Files.newBufferedReader(path)) {
                // load data into settings object
                settings = JAXB.unmarshal(input, Settings.class);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        else {
            // Create new settings object if no existing data
            settings = new Settings();
        }

        // Menu options
        while (choice != 0) {
            System.out.println("-".repeat(headerLength));
            System.out.println("Choose an option: ");
            System.out.println("1. View Settings");
            System.out.println("2. Update Settings");
            System.out.println("0. Exit");
            System.out.println("-".repeat(headerLength));
            choice = scanner.nextInt();
            scanner.nextLine();

            // View settings
            if (choice == 1) {
                // View settings only available if Settings.xml already exists
                if (Files.exists(path)) {
                    System.out.println("Name: " + settings.getSettingsInfo().getName());
                    System.out.println("Height: " + settings.getSettingsInfo().getHeight());
                    System.out.println("Weight: " + settings.getSettingsInfo().getWeight());
                    System.out.println("Birthday: " + settings.getSettingsInfo().getBirthday());
                    System.out.println("Functional Threshold Power: " + settings.getSettingsInfo().getPower());
                } else {
                    System.out.println("Settings does not exist. Create it first with 'update' before viewing");
                }
            }
            else if (choice == 2) {
                // Accept user input to update Settings.xml
                try(BufferedWriter output =
                            Files.newBufferedWriter(path)) {
                    System.out.println("Enter Name: ");
                    String name = scanner.nextLine();
                    settings.getSettingsInfo().setName(name);

                    System.out.println("Enter Height: ");
                    double height = scanner.nextDouble();
                    settings.getSettingsInfo().setHeight(height);

                    System.out.println("Enter Weight: ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();
                    settings.getSettingsInfo().setWeight(weight);

                    System.out.println("Enter Birthday(yyyy-MM-dd): ");
                    String birthday = scanner.nextLine();
                    try {
                        Date birthdayDate = dateFormat.parse(birthday);
                        settings.getSettingsInfo().setBirthday(birthdayDate);
                    } catch (ParseException e) {
                        System.err.println("Invalid date format. Please use yyyy-MM-dd.");
                    }

                    System.out.println("Enter Functional Threshold Power: ");
                    double power = scanner.nextDouble();
                    settings.getSettingsInfo().setPower(power);

                    // Save settings and update Settings.xml
                    System.out.println("Settings successfully saved!");
                    JAXB.marshal(settings, output);
                }
                catch(IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
