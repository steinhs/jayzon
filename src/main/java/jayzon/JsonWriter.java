package jayzon;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonWriter {


    //Converts map to json formatted string, which is returned
    public static String mapToJson(Map<String, Object> map) {
        String json = "{ ";
        String value = "", key="";

        for (Map.Entry<String, Object> entry : map.entrySet()){

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

        return json;
    }

    //Converts object to map to json
    public static String objectToJson(Object object){
        //Outsourced Jackson ObjectMapper framework to do the heavylifting of serializing an object to map.
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(object, Map.class);

        //Converts map to json.
        String json = mapToJson(map);

        return json;
    }



}
