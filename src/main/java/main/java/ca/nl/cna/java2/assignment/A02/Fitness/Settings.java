package main.java.ca.nl.cna.java2.assignment.A02.Fitness;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Settings {
        // @XmlElement specifies XML element name for each object in the List
        @XmlElement(name="settings")
        private Setting settings = new Setting(); // stores Accounts

        // Returns the setting object
        public Setting getSettingsInfo() {return settings;}
}
