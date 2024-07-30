package main.java.ca.nl.cna.java2.project;

import org.json.simple.JSONObject;

// Download json-simple Jar from here:
// https://repo1.maven.org/maven2/com/googlecode/json-simple/json-simple/1.1.1/

/**
 * Utility class for creating JSON objects with a given message.
 * This class uses the json-simple library to construct JSON objects.
 *
 * @author Noah.Forward
 */
public class ResponseJSONObject {

    /**
     * Creates a JSON object with a specified message.
     *
     * @param msg the message to include in the JSON object
     * @return a JSON object containing the provided message
     */
    static JSONObject getObject(String msg) {
        JSONObject obj = new JSONObject();
        obj.put("string", msg);
        return obj;
    }
}
