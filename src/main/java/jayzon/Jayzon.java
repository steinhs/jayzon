package jayzon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jayzon {
    public Jayzon() {
    }

    public static void main(String[] args) throws IOException {
        //Dummy-objects
        List chapters = new ArrayList(); chapters.add("Space"); chapters.add("Time"); chapters.add("Universe"); chapters.add("Relativity");
        Book simpleObject = new Book("The Universe", "Stephen Hawkins");
        Book arrayObject = new Book("Stephen Hawkins", "The Universe", chapters);
        System.out.println(arrayObject);
        Book arrayNestedObject = new Book("Stephen Hawkins", "The Universe", chapters, new Country("Norway", "Europe", 5200000));

        /** READ OBJECT TO JSON STRING (using dummy-book-objects) */
        System.out.println("\n-------- OBJECT to JSON --------");
        String simpleObjectJSON = JsonWriter.objectToJson(simpleObject);
        String arrayObjectJSON = JsonWriter.objectToJson(arrayObject);
        String arrayNestedObjectJSON = JsonWriter.objectToJson(arrayNestedObject);
        System.out.println(simpleObjectJSON + "\n" + arrayObjectJSON + "\n" + arrayNestedObjectJSON);

        /** READ JSON STRING TO OBJECT */
        System.out.println("\n-------- JSON to MAP --------");
        String simpleJSON = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\" }";
        String arrayJSON = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ] }";
        String arrayNestedJSON = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ], \"country\": { \"name\": \"Norway\", \"continent\": \"Europe\", \"population\": \"5200000\" } }";
        Map<String, Object> simpleJsonMap = JsonReader.jsonToMap(simpleJSON);
        Map<String, Object> arrayJsonMap = JsonReader.jsonToMap(arrayJSON);
        Map<String, Object> arrayNestedJsonMap = JsonReader.jsonToMap(arrayNestedJSON);
        System.out.println(simpleJsonMap + "\n" + arrayJsonMap + "\n" + arrayNestedJsonMap);

        System.out.println("\n-------- JSON to OBJECT --------");
        Book book = JsonReader.jsonToObject(simpleJSON, Book.class);
        System.out.println(book);
        Book book2 = JsonReader.jsonToObject(arrayJSON, Book.class);
        System.out.println(book2);
        Book book3 = JsonReader.jsonToObject(arrayNestedJSON, Book.class);

        System.out.println(book);
        System.out.println(book2);
        System.out.println(book3);

    }

    public static class Book{
        public String author;
        public String title;
        public List<String> chapters;
        public Country country;

        public Book() {
        }

        public Book(String author, String title ) {
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
                    ", chapters=" + chapters +
                    ", country=" + country +
                    '}';
        }
    }

    public static class Country{
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

        public Country() {
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