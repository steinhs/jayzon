package jayzon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @org.junit.jupiter.api.Test
    void objectToJson() {
        ArrayList<String> chapters = new ArrayList();
        chapters.add("Space");
        chapters.add("Time");
        chapters.add("Universe");
        chapters.add("Relativity");
        Book object = new Book("Stephen Hawkins", "The Universe", chapters, new Country("Norway", "Europe", 5200000));

        String json = JsonWriter.objectToJson(object);
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