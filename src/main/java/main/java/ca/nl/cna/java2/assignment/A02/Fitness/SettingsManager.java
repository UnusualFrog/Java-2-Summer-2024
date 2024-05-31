package main.java.ca.nl.cna.java2.assignment.A02.Fitness;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SettingsManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int headerLength = 30;
        int choice = -1;
        Path path = Paths.get("./logs/xml/fitnessSettings/Settings.xml");
        Settings settings = null;

        if (Files.exists(path)) {
            try (BufferedReader input = Files.newBufferedReader(path)) {
                settings = JAXB.unmarshal(input, Settings.class);
//                System.out.println(settings.getSettingsInfo().getName());
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        else {
            settings = new Settings();
        }

        while (choice != 0) {
            System.out.println("-".repeat(headerLength));
            System.out.println("Choose an option: ");
            System.out.println("1. View Settings ");
            System.out.println("2. Update Settings ");
            System.out.println("0. Save and Exit ");
            System.out.println("-".repeat(headerLength));
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Name:" + settings.getSettingsInfo().getName());
                System.out.println("Height:" + settings.getSettingsInfo().getHeight());
                System.out.println("Weight:" + settings.getSettingsInfo().getWeight());
                System.out.println("Birthday:" + settings.getSettingsInfo().getBirthday());
                System.out.println("Functional Threshold Power:" + settings.getSettingsInfo().getPower());
            }
            else if (choice == 2) {
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

                    System.out.println("Enter Birthday: ");
                    String birthday = scanner.nextLine();
                    settings.getSettingsInfo().setBirthday(birthday);

                    System.out.println("Enter Functional Threshold Power: ");
                    double power = scanner.nextDouble();
                    settings.getSettingsInfo().setPower(power);

                    JAXB.marshal(settings, output);
                }
                catch(IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
