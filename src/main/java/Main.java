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


        //COMMENT: WRITE JSON STRING FROM OBJECT
        System.out.println("\n-------- OBJECT to JSON --------");
        List chapters = new ArrayList();
        chapters.add("Space");
        chapters.add("Time");
        chapters.add("Universe");
        chapters.add("Relativity");

        //COMMENT: Object
        Book sh = new Book("The Universe", "Stephen Hawkins");
        //String json1 = JsonWriter.mapToJson(sh);
        //COMMENT: Object with array
        Book shc = new Book("The Universe", "Stephen Hawkins", chapters);
        //String json2 = JsonWriter.mapToJson(shc);
        //COMMENT: Object with array and object
        Book shcc = new Book("The Universe", "Stephen Hawkins", chapters, new Country("Norway", "Europe", 5200000));
        //String json3 = JsonWriter.mapToJson(shcc);

        //System.out.println(json1);
        //System.out.println(json2);
        //System.out.println(json3);




        //COMMENT: READ JSON STRING TO OBJECT
        System.out.println("\n-------- JSON to OBJECT --------");

        String jsonBasic = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\" }";
        String jsonArray = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ] }";
        String jsonArrayObject = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ], \"country\": { \"name\": \"Norway\", \"continent\": \"Europe\", \"population\": \"5200000\" } }";

        //Book book1 = (Book) JsonReader.jsonToObject(jsonBasic);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(shcc, Map.class);
        System.out.println("GOAL MAP: " + map  + "\n");
        //Book book2 = (Book) JsonReader.jsonToObject(jsonArray);
        Book book3 = (Book) JsonReader.jsonToObject(jsonArrayObject);


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
        Country country;

        public Book(String title,String author) {
            this.title = title;
            this.author = author;
        }

        public Book(String author, String title, List<String> chapters) {
            this.author = author;
            this.title = title;
            this.chapters = chapters;
        }

        public Book(String author, String title, List<String> chapters, Country country) {
            this.author = author;
            this.title = title;
            this.chapters = chapters;
            this.country = country;
        }

        public Book(String author, Country country, List<String> chapters, String title) {
            this.author = author;
            this.title = title;
            this.chapters = chapters;
            this.country = country;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
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

    static class Country{
        String name;
        String continent;
        int population;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContinent() {
            return continent;
        }

        public void setContinent(String continent) {
            this.continent = continent;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public Country(String name, String continent, int population) {
            this.name = name;
            this.continent = continent;
            this.population = population;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "name='" + name + '\'' +
                    ", continent='" + continent + '\'' +
                    ", population=" + population +
                    '}';
        }
    }
}
