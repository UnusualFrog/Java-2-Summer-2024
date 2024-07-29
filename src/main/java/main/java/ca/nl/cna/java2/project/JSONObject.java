package main.java.ca.nl.cna.java2.project;
/**
 * Download json-simple Jar from here:
 * https://repo1.maven.org/maven2/com/googlecode/json-simple/json-simple/1.1.1/
 *
 */

public class JSONObject {

    //JSONObject

    static org.json.simple.JSONObject getObject(String msg){
        org.json.simple.JSONObject obj = new org.json.simple.JSONObject();
        obj.put("string",msg);

        return obj;
    }


}
