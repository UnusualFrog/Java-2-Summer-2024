package main.java.ca.nl.cna.java2.exercise.ex5_Serialization.guessingGame;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class GameLog {
    // @XmlElement specifies XML element name for each object in the List
    @XmlElement(name="guess")
    private List<Integer> guessList = new ArrayList<>(); // stores Accounts

    // returns the List<Accounts>
    public List<Integer> getGuessList() {return guessList;}
}
