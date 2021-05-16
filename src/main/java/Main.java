public class Main {
    public static void main(String[] args) throws Exception {
        //  WRITE OBJECT TO JSON
        //          Person pers = new Person(....)
        //          String json = JsonWriter.objectToJson(pers);

        // READ JSON STRING TO OBJECT
        //          String2json: "String json = something json content"
        //          Object object = JsonReader.jsonToJava(json);

        //TODO: WRITE JSON STRING FROM OBJECT
        //Book sh = new Book("The Universe", "Stephen Hawkins");

        //TODO: READ JSON STRING TO OBJECT
        String jsonBook = "{\"title\": \"The Universe\", \"author\": \"Stephen Hawkins\" }";
        String jsonBookCHAPTERS = "{ \"title\": \"The Universe\", \"author\": \"Stephen Hawkins\", \"chapters\": [ \"Chapter 1: Relativity\", \"Chapter 2: Time\", \"Chapter 3: Universe\" ] }";
        Book book = (Book) JsonReader.jsonToObject(jsonBook);


    }

    static class Book{
        String author;
        String title;
        String chapters;

        public Book(String title,String author) {
            this.title = title;
            this.author = author;
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

        public String getChapters() {
            return chapters;
        }

        public void setChapters(String chapters) {
            this.chapters = chapters;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", chapters='" + chapters + '\'' +
                    '}';
        }
    }
}
