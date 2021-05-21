package jayzon;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class JsonReader {


    //Main method for json to map conversion
    public static Map jsonToMap(String json) throws IOException{
        Map<String, Object> jsonMap = new HashMap<>();

        Boolean apostBool = false, valueBool = false, bracketBool = false, curlyBool = false;
        String key = "", value = "";

        if (json.charAt(0)!='{'){
            throw new IOException("Can't identify as json. Does not begin with \"{\".");
        } else {
            for (int i = 1; i < json.length(); i++) {
                char c = json.charAt(i);

                //Checks if currently scanning a word
                if (apostBool == true) {
                    //Checks if currently scanning type or data
                    if (valueBool == true) {
                        //Checks if in brackets
                        if (bracketBool.equals(true)){
                            if (c == ']'){
                                apostBool=false; valueBool=false; bracketBool=false; curlyBool=false;
                                value = value.replaceAll("\"", "").replaceAll(":", "=").trim();
                                key = key.replaceAll("\"", "").replaceAll(":", "=").trim();
                                value=value+"]";
                                jsonMap.put(key, value);
                                value = "";
                                key = "";
                            } else {
                                value=value+c;
                            }
                        } else {
                            value = value + c;
                            //Check if done with word
                            if (c == '"') {
                                apostBool = false;
                                valueBool = false;
                                value = value.replaceAll("\"", "").replaceAll(":", "=").trim();
                                key = key.replaceAll("\"", "").replaceAll(":", "=").trim();
                                jsonMap.put(key, value);
                                value = "";
                                key = "";
                            }
                        }

                    } else {
                        key = key + c;
                        //Check if done with word
                        if (c == '"') {
                            apostBool = false;
                        }
                    }
                    //Check if starting a word, and if it is type or data
                } else {
                    if (c == '"') {
                        apostBool = true;
                        if (valueBool == true)
                            value = value + c;
                        else
                            key = key + c;
                    }
                }
                //Checks if next word will be data
                if (c == ':')
                    valueBool = true;
                else if (c=='['){
                    bracketBool=true;
                    value=value+"[";
                }
                else if (c=='{'){
                    jsonMap = jsonToMap(json, jsonMap, key, i);
                    return jsonMap;
                }
            }
        }
        return jsonMap;
    }

    //Recursive for inner classes in json string. Called in jsonToMap(String) and jsonToMap(String,Map,String,int)
    public static Map jsonToMap(String json, Map<String, Object> jsonMap, String key, int y) throws IOException {
        Boolean apostBool = false, valueBool = true, bracketBool = false, curlyBool = true;
        String value = "{";

        for (int i = y+1; i < json.length(); i++) {
            char c = json.charAt(i);

            //Checks if currently scanning a word
            if (apostBool == true) {
                //Checks if currently scanning type or data
                if (valueBool == true) {
                    //Checks if in brackets
                    if (bracketBool.equals(true)){
                        if (c == ']'){
                            apostBool=false; valueBool=false; bracketBool=false;
                            value = value.replaceAll("\"", "").replaceAll(":", "=").trim();
                            key = key.replaceAll("\"", "").replaceAll(":", "=").trim();
                            value=value+"]";
                            jsonMap.put(key, value);
                            value = "";
                            key = "";
                        } else {
                            value=value+c;
                        }
                    } else if (curlyBool.equals(true)){
                        if (c == '}'){
                            apostBool=false; valueBool=false; curlyBool=false;
                            value = value.replaceAll("\"", "").replaceAll(": ", "=").trim();
                            key = key.replaceAll("\"", "").replaceAll(": ", "=").trim();
                            value=value+"}";
                            jsonMap.put(key, value);
                            value = "";
                            key = "";
                        } else {
                            value=value+c;
                        }
                    } else {
                        value = value + c;
                        //Check if done with word
                        if (c == '"') {
                            apostBool = false;
                            valueBool = false;
                            value = value.replaceAll("\"", "").replaceAll(":", "=").trim();
                            key = key.replaceAll("\"", "").replaceAll(":", "=").trim();
                            jsonMap.put(key, value);
                            value = "";
                            key = "";
                        }
                    }

                } else {
                    key = key + c;
                    //Check if done with word
                    if (c == '"') {
                        apostBool = false;
                    }
                }
                //Check if starting a word, and if it is type or data
            } else {
                if (c == '"') {
                    apostBool = true;
                    if (valueBool == true)
                        value = value + c;
                    else
                        key = key + c;
                }
            }
            //Checks if next word will be data
            if (c == ':')
                valueBool = true;
            else if (c=='['){
                bracketBool=true;
                value=value+"[";
            }
            else if (c=='{'){
                jsonMap = jsonToMap(json, jsonMap, key, i);
                return jsonMap;
            }
        }
        return jsonMap;
    }

    //TODO: JSON TO MAP TO OBJECT - RETURNS OBJECT
    public static <T> T jsonToObject(String json, Class<T> tClass) throws IOException {
        //Converts json string to map
        Map resultMap = jsonToMap(json);

        //Convert map to object
        //COMMENT: This section will deserialize map to an object which is defined by tClass.
        // Solution to this have not been found and using other frameworks have yielded no success.

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return objectMapper.convertValue(resultMap, tClass);
    }

}
