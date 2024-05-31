package main.java.ca.nl.cna.java2.assignment.A02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fitnessSettings")
public class oldFitnessSettings {

    @XmlElement(name="settings")
    private oldSettings settings = new oldSettings();

    public oldSettings getSettings() {
        return this.settings;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int headerLength = 30;
        int choice = -1;
        Path path = Paths.get("./logs/xml/fitnessSettings/Settings.xml");
        oldSettings currentSettings = null;
        oldFitnessSettings fitnessSettings = new oldFitnessSettings();

        if (Files.exists(path)) {
            try (BufferedReader br = Files.newBufferedReader(path)) {
                currentSettings = JAXB.unmarshal(br, oldSettings.class);
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
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Name:" + currentSettings.getName());
                System.out.println("Height:" + currentSettings.getHeight());
                System.out.println("Weight:" + currentSettings.getWeight());
                System.out.println("Birthday:" + currentSettings.getBirthday());
                System.out.println("Functional Threshold Power:" + currentSettings.getPower());
            } else if (choice == 2) {
                try(BufferedWriter output =
                            Files.newBufferedWriter(path)) {
                    System.out.println("Enter Name:");
                    String name = scanner.nextLine();
                    fitnessSettings.getSettings().setName(name);

                    System.out.println("Enter Height:");
                    double height = scanner.nextDouble();
                    fitnessSettings.getSettings().setHeight(height);

                    System.out.println("Enter Weight:");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();
                    fitnessSettings.getSettings().setWeight(weight);

                    System.out.println("Enter Birthday:");
                    String birthday = scanner.nextLine();
                    fitnessSettings.getSettings().setBirthday(birthday);

                    System.out.println("Enter Functional Threshold Power:");
                    double power = scanner.nextDouble();
                    fitnessSettings.getSettings().setPower(power);

                    JAXB.marshal(fitnessSettings, output);
                }
                catch(IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
