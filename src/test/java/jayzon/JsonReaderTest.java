package jayzon;

import org.junit.jupiter.api.parallel.Execution;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @org.junit.jupiter.api.Test
    void jsonToMap_does_not_start_as_json() throws IOException {
        String json = "\"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ], \"country\": { \"name\": \"Norway\", \"continent\": \"Europe\", \"population\": \"5200000\" } }";
        try {
            Map<String, Object> arrayNestedJsonMap = JsonReader.jsonToMap(json);
        } catch (Exception e) {
            System.out.println("Not correct json string.");
        }
    }

    @org.junit.jupiter.api.Test
    void testJson_with_inner_class_to_map() throws IOException {
        String json = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ], \"country\": { \"name\": \"Norway\", \"continent\": \"Europe\", \"population\": \"5200000\" }, , \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ] }";
        Map<String, Object> arrayNestedJsonMap = JsonReader.jsonToMap(json);
    }

    @org.junit.jupiter.api.Test
    void testJson_with_two_inner_class_to_map() throws IOException {
        String json = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ], \"country\": { \"name\": \"Norway\", \"continent\": \"Europe\", \"population\": \"5200000\" }, \"country\": { \"name\": \"Norway\", \"continent\": \"Europe\", \"population\": \"5200000\" }, \"title\": \"The Universe\" }";
        Map<String, Object> arrayNestedJsonMap = JsonReader.jsonToMap(json);
    }

    @org.junit.jupiter.api.Test
    void testjson_to_object_with_array() throws IOException {
        String arrayJSON = "{ \"author\": \"Stephen Hawkins\", \"title\": \"The Universe\", \"chapters\": [ \"Space\", \"Time\", \"Universe\", \"Relativity\" ] }";
        Book book2 = JsonReader.jsonToObject(arrayJSON, Book.class);

    }


    private static class Book{
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

    private static class Country{
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