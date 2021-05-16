import java.util.*;

/**
 * String2json: "String json = something json content"
 * Object object = JsonReader.jsonToJava(json);
 *
 * */

public class JsonReader {

    public JsonReader() {
    }


    public static Object jsonToObject(String json) throws Exception {
        System.out.println(json + "\n");

        reader(json);

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

    private static void reader(String json) throws Exception {
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
    }


}
