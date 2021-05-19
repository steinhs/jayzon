import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.TypeCastTree;


import java.io.IOException;
import java.sql.Types;
import java.util.*;

        /*jsonMap.put("author", "Stephen Hawkins");
        jsonMap.put("title", "The Universe");
        jsonMap.put("chapters", "[Space, Time, Universe, Relativity]");
        jsonMap.put("country", "{name=Norway, continent=Europe, population=5200000}");*/

public class JsonReader {
    public JsonReader() {
    }

    public static Map jsonToMap(String json) throws IOException{
        Map<String, String> jsonMap = new LinkedHashMap<>();

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

    public static Map jsonToMap(String json, Map<String, String> jsonMap, String key, int y) throws IOException {
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

    //TODO: MAP TO OBJECT - RETURNS OBJECT
    public static <T> T jsonToObject(String json, Class<T> tClass) throws IOException {
        Map<String, String> finishedMap = jsonToMap(json);
        ObjectMapper objectMapper = new ObjectMapper();

        final T tClass1 = objectMapper.convertValue(finishedMap, tClass);

        return tClass1;
    }

}
