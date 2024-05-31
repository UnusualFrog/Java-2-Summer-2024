//package main.java.ca.nl.cna.java2.exercise.ex4_IO.book;
//
//import javax.xml.bind.JAXB;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class AnalyzeLogs {
//    public static void main(String[] args) {
//        Path path = Paths.get("./logs");
//        if (Files.exists(path))
//        {
//            System.out.println("Folder exists");
//
//            try {
//                DirectoryStream<Path> directoryStream =
//                        Files.newDirectoryStream(path);
//                int totalFiles = 0;
//                int totalGuesses = 0;
//                for (Path p : directoryStream) {
//                    totalFiles++;
//                    System.out.println(p);
//
//                    try( BufferedReader br = Files.newBufferedReader(p)) {
//                        GameLog gameLog = JAXB.unmarshal(br, GameLog.class);
//                        totalGuesses += gameLog.getGuesses().size();
//                    }
//                }
//                System.out.println("Total guesses " + totalGuesses);
//                System.out.println("Total files " + totalFiles);
//                System.out.println("Average guesses: " + (float)totalGuesses/totalFiles);
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//                System.err.println(e.getMessage());
//            }
//        }
//        else {
//            System.out.println("Can't find folder to process logs");
//        }
//
//
//    }
//}
