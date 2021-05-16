import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tar i bruk Jackson biblotek "ObjectMapper" til å konvertere Java objekt til Map og vice versa.
 *
 *
 * */

public class Main {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.spacer();

        //  WRITE OBJECT TO JSON
        //          Person pers = new Person(....)
        //          String json = JsonWriter.objectToJson(pers);

        // READ JSON STRING TO OBJECT
        //          String2json: "String json = something json content"
        //          Object object = JsonReader.jsonToJava(json);

        //TODO: WRITE JSON STRING FROM OBJECT
        System.out.println("-------- OBJECT to JSON --------");
        Book sh = new Book("The Universe", "Stephen Hawkins");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(sh, Map.class);
        JsonWriter.mapToJson(map);

        main.spacer();

        /*
        List chapters = new ArrayList();
        chapters.add("Space");
        chapters.add("Time");
        chapters.add("Universe");
        chapters.add("Relativity");
        Book shc = new Book("The Universe", "Stephen Hawkins", chapters);
        ObjectMapper objectMapper2 = new ObjectMapper();
        Map<String, Object> map2 = objectMapper2.convertValue(shc, Map.class);
        System.out.println(map2);
        main.spacer();
        String json = JsonWriter.mapToJson(map2);
        */

        main.spacer();


        //TODO: READ JSON STRING TO OBJECT
        /*
        System.out.println("-------- JSON to OBJECT --------");
        String jsonBook = "{\"title\": \"The Universe\", \"author\": \"Stephen Hawkins\" }";
        String jsonBookCHAPTERS = "{ \"title\": \"The Universe\", \"author\": \"Stephen Hawkins\", \"chapters\": [ \"Chapter 1: Relativity\", \"Chapter 2: Time\", \"Chapter 3: Universe\" ] }";
        Book book = (Book) JsonReader.jsonToObject(jsonBook);
         */


    }

    public Main() {
    }

    //Space maker, for cleaner output and code.
    public void spacer(){
        System.out.println("");
    }

    static class Book{
        String author;
        String title;
        List<String> chapters;

        public Book(String title,String author) {
            this.title = title;
            this.author = author;
        }

        public Book(String author, String title, List<String> chapters) {
            this.author = author;
            this.title = title;
            this.chapters = chapters;
        }

        public List<String> getChapters() {
            return chapters;
        }

        public void setChapters(List<String> chapters) {
            this.chapters = chapters;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        @Override
        public String toString() {
            return "Book{" +
                    "author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
