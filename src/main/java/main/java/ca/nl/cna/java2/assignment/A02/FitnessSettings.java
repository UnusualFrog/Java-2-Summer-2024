package main.java.ca.nl.cna.java2.assignment.A02;

import main.java.ca.nl.cna.java2.exercise.ex5_Serialization.book.Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;

public class FitnessSettings {

    @XmlElement(name="settings")
    private Settings settings = new Settings();

    public Settings getSettings() {
        return this.settings;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int headerLength = 30;
        int choice = -1;
        Path path = Paths.get("./logs/xml/fitnessSettings/Settings.xml");
        Settings currentSettings = null;
        FitnessSettings fitnessSettings = new FitnessSettings();

        if (Files.exists(path)) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                currentSettings = JAXB.unmarshal(br, Settings.class);
                System.out.println(currentSettings);
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        while (choice != 0) {
            System.out.println("-".repeat(headerLength));
            System.out.println("Choose an option: ");
            System.out.println("1. View Settings ");
            System.out.println("2. Update Settings ");
            System.out.println("0. Save and Exit ");
            System.out.println("-".repeat(headerLength));
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println(currentSettings);
            } else if (choice == 2) {
                try(BufferedWriter output =
                            Files.newBufferedWriter(path)) {
                    fitnessSettings.getSettings().setName("Paul");
                }
                catch(IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
