package main.java.ca.nl.cna.java2.assignment.A01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Q3 {
    public static void cat(File file) {
         //input = null;
        String line = null;
        try (RandomAccessFile input = new RandomAccessFile(file, "r");){
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
