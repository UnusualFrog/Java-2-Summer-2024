package main.java.ca.nl.cna.java2.project;
/**
 * Download json-simple Jar from here:
 * https://repo1.maven.org/maven2/com/googlecode/json-simple/json-simple/1.1.1/
 *
 */

import org.json.simple.JSONObject;

public class ResponseJSONObject {

    //JSONObject

    static JSONObject getObject(String msg){
        JSONObject obj = new JSONObject();
        obj.put("string", msg);

        return obj;
    }


}
