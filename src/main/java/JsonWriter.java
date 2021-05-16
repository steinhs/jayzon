import java.util.Map;

/**
 *
 *
 * */

public class JsonWriter {

    public JsonWriter() {
    }

    //TODO: Check if object in object.
    public static String mapToJson(Map<String, Object> map) {
        System.out.println("----- mapToJsoning -----");

        String json = "{ ";
        int mapSize = map.size();

        boolean bracketBool = false;

        for (Map.Entry<String, Object> entry : map.entrySet()){
            System.out.println("Key: "+entry.getKey() + ", Value = " + entry.getValue());
            String value = entry.getValue().toString();
            String key = entry.getKey().toString();

            //Check if several values trail key.
            if (value.contains("[")){
                value = value.replaceAll("\\[", "").replaceAll("\\]","");
                value = value.replaceAll("([\\w.]+)", "\"$1\"");
                json  = json + "\"" + key + "\": [ " + value + " ] ";
            }

            //Only one value to trail key.
            else
                json =  json + "\"" + key+"\": \"" + value + "\"";


        json = json + ",";
        }

        if ((json != null) && (json.length() > 0)) {
            json = json.substring(0, json.length() - 1);
        }
        json = json + "}";
        System.out.println("\n----- Finished json string -----\n"+json);

        return json;
    }
}
