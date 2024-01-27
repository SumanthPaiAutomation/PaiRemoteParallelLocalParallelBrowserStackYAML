package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReaderWriterUtil {

    private static final String SAMPLE_JSON_FILE_PATH = "path/to/your/json/file.json";

    public static JSONObject readJsonFromFile() throws IOException, FileNotFoundException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(SAMPLE_JSON_FILE_PATH);
        Object obj = parser.parse(reader);
        return (JSONObject) obj;
    }

    public static void writeJsonToFile(JSONObject jsonObject) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(SAMPLE_JSON_FILE_PATH));
        writer.write(jsonObject.toJSONString());
        writer.close();
    }

    public static String getStringField(JSONObject jsonObject, String fieldName) {
        if (jsonObject.containsKey(fieldName)) {
            return (String) jsonObject.get(fieldName);
        }
        return null;
    }

    public static void setStringField(JSONObject jsonObject, String fieldName, String value) {
        jsonObject.put(fieldName, value);
    }

    public static JSONArray getArrayField(JSONObject jsonObject, String fieldName) {
        if (jsonObject.containsKey(fieldName)) {
            return (JSONArray) jsonObject.get(fieldName);
        }
        return null;
    }

    public static void addElementToArrayField(JSONObject jsonObject, String fieldName, Object element) {
        JSONArray jsonArray = getArrayField(jsonObject, fieldName);
        if (jsonArray == null) {
            jsonArray = new JSONArray();
            jsonObject.put(fieldName, jsonArray);
        }
        jsonArray.add(element);
    }
}

//usage
//// Read JSON file and get a specific field
//JSONObject jsonData = JsonReaderWriterUtil.readJsonFromFile();
//String username = JsonReaderWriterUtil.getStringField(jsonData, "username");
//
//// Update a field in the JSON object
//JsonReaderWriterUtil.setStringField(jsonData, "password", "new_password");
//
//// Add an element to an array field
//JSONObject user = new JSONObject();
//user.put("name", "John Doe");
//JsonReaderWriterUtil.addElementToArrayField(jsonData, "users", user);
//
//// Write updated JSON object to file
//JsonReaderWriterUtil.writeJsonToFile(jsonData);