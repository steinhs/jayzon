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

    //TODO: JSON STRING TO MAP - RETURNS MAP
    public static Map jsonToMap(String json) throws IOException{
        Map<String, String> jsonMap = new HashMap<String, String>();
        System.out.println("JSON STRING: " + json + "\n");

        Boolean apostBool = false, valueBool = false, bracketBool = false, curlyBool = false;
        String key = "", value = "";

        if (json.charAt(0)!='{'){
            throw new IOException("Can't identify as json. Does not begin with \"{\".");
        } else {
            for (int i = 0; i < json.length(); i++) {
                char c = json.charAt(i);

                //Checks if currently scanning a word
                if (apostBool == true) {
                    //Checks if currently scanning type or data
                    if (valueBool == true) {
                        //Checks if in brackets
                        if (bracketBool.equals(true)){
                            if (c == ']'){
                                apostBool=false; valueBool=false; bracketBool=false;
                                value = value.replaceAll("\"", "");
                                key = key.replaceAll("\"", "");
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
                                value = value.replaceAll("\"", "");
                                key = key.replaceAll("\"", "");
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
                                value = value.replaceAll("\"", "").replaceAll(":", "=");
                                key = key.replaceAll("\"", "").replaceAll(":$", "=");
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
                if (c=='['){
                    bracketBool=true;
                    value=value+"[";
                }
                if (c=='{'){
                    curlyBool=true;
                    value=value+"{";
                }

            }
        }
        System.out.println("END MAP: "+ jsonMap);
        return null;
    }

    //{author=The Universe, title=Stephen Hawkins, chapters=[Space, Time, Universe, Relativity], country={name=Norway, continent=Europe, population=5200000}}
    //{country={name: Norway, continent: Europe, population: 5200000 }, author={Stephen Hawkins, title: The Universe, chapters: [[ Space, Time, Universe, Relativity ]}
    //TODO: MAP TO OBJECT - RETURNS OBJECT
    public static Object jsonToObject(String json) throws IOException {
        Map<String, String> jsonMap = jsonToMap(json);

        return null;
    }

    public static Object jsonToObjectTEMP(String json) throws Exception {
        //System.out.println(json + "\n");

        HashMap<String, String> hashMap = new HashMap<String, String>();
        ArrayList arrayListTypes = new ArrayList();

        /**
         * 1. Check if first char == {. If true, continue, else throw error to user.
         * 2. Read through json characters.
         *      2.1 If ("), add next character to "type" string and keep adding until new (") found.
         *      2.2 If (:), continue till next (") and save next chars to "data" string until next (").
         *      2.3 If (,), add "type" and "data" to hashmap and clear type/data. Continue read and check 2.1.
         *      2.* If (}), stop reading and continue to 3.
         * 3. Create object from hashmap.
         * */

        System.out.println("Reading json file:");
        boolean apostBool = false, colonBool = false;
        String type ="", data="";

        if (json.charAt(0)!='{'){
            throw new Exception("Can't identify as json. Does not begin with \"{\".");
        } else {
            for (int i = 0; i < json.length(); i++) {
                char c = json.charAt(i);

                //Checks if currently scanning a word
                if (apostBool==true){
                    //Checks if currently scanning type or data
                    if (colonBool==true){
                        data=data+c;
                        //Check if done with word
                        if (c=='"'){
                            apostBool=false;
                            colonBool=false;
                            data = data.replaceAll("\"", "");
                            type = type.replaceAll("\"", "");
                            hashMap.put(type, data);
                            arrayListTypes.add(type);
                            data="";
                            type="";
                        }
                    } else {
                        type=type+c;
                        //Check if done with word
                        if (c=='"'){
                            apostBool=false;
                        }
                    }
                    //Check if starting a word, and if it is type or data
                } else {
                    if (c=='"'){
                        apostBool=true;
                        if (colonBool==true)
                            data=data+c;
                        else
                            type=type+c;
                    }
                }
                //Checks if next word will be data
                if (c==':')
                    colonBool=true;
            }
            System.out.println(hashMap + "\n");



        }

        //Split each item by comma
        //String[] words = json.split(",");

        //Removes "'s
        //json = json.replaceAll("\"", "");

        /*for (String word : words){
            System.out.println(word);
        }

         */
        return null;
    }


}
