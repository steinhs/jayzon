import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 *
 *
 * */

public class JsonWriter {

    public JsonWriter() {
    }

    public static String mapToJson(Object object) {
        String json = "{ ";
        String value = "", key="";
        //ObjectMapper by Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(object, Map.class);
        System.out.println(map);

        int mapSize = map.size();

        boolean bracketBool = false;

        for (Map.Entry<String, Object> entry : map.entrySet()){
            //System.out.println("Key: "+entry.getKey() + ", Value = " + entry.getValue());

            //Checks if value does not contain anything (null)
            if (entry.getValue()!=null){
                value = entry.getValue().toString();
                key = entry.getKey().toString();

                //Check if value has brackets.
                if (value.contains("[") && !(value.contains("{"))){
                    //Formatting
                    value = value.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("([\\w.]+)", "\"$1\"");
                    //Adding key/value to json-string
                    json  = json + "\"" + key + "\": [ " + value + " ], ";
                }
                //Check if value has curly brackets.
                else if (value.contains("{")  && !(value.contains("["))){
                    //Formatting
                    value = value.replaceAll("\\{", "").replaceAll("\\}","").replaceAll("\\=", ": ").replaceAll("([\\w.]+)", "\"$1\"");
                    //Adding key/value to json-string
                    json  = json + "\"" + key + "\": { " + value + " }, ";
                }

                //Doesn't contain any special-characters.
                else
                    json =  json + "\"" + key+"\": \"" + value + "\", ";

            }
        }

        //Remove last comma when finished, then close json-string with "}"
        if ((json != null) && (json.length() > 0)) {
            json = json.substring(0, json.length() - 1);
            json = json.substring(0, json.length() - 1);
        }
        json = json + " }";

        //System.out.println("\n-- Finished json string --\n"+json);

        return json;
    }


}
