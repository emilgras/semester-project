import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {


    public static List<String> getAllCitiesByBookTitle(String bookTitle) {

        List<String> cities = new ArrayList<String>();

        Map<String, List<String>> bookMap = new HashMap<String, List<String>>();

        cities.add("London");
        cities.add("Madrid");
        cities.add("Berlin");
        cities.add("Paris");
        cities.add("Copenhagen");

        bookMap.put("hp", cities);


        if (bookMap.get(bookTitle) != null) {
             return bookMap.get(bookTitle);
        }

        // If not found, return empty Array
        return new ArrayList<String>();
    }

    public static List<String> getAuthorsByCityName(String cityName) {

        List<String> authors = new ArrayList<String>();

        Map<String, List<String>> cityMap = new HashMap<String, List<String>>();

        authors.add("Obama");
        authors.add("Putin");
        authors.add("Michael Jackson");
        authors.add("Lars Løkke Rasmussen");
        authors.add("Anders Hemmingsen");

        cityMap.put("copenhagen", authors);

        if (cityMap.get(cityName) != null) {
            return cityMap.get(cityName);
        }

        return new ArrayList<String>();
    }

    public List<String> getAllBooksWrittenByAuthor(String author) {

        List<String> books = new ArrayList<String>();

        Map<String, List<String>> booksMap = new HashMap<String, List<String>>();

        books.add("My best hours");
        books.add("Becoming a pirate");
        books.add("Best of Disney's");

        booksMap.put("Frank hansen", books);

        if (booksMap.get(author) != null) {
            return booksMap.get(author);
        }

        return books;
    }

    public static void main(String[] args) {
        System.out.println("From mapper - does exist?: " + getAllCitiesByBookTitle("test"));
        System.out.println("From mapper: " + getAllCitiesByBookTitle("hp"));

        System.out.println("From mapper - does exist?: " + getAuthorsByCityName("test"));
        System.out.println("From mapper: " + getAuthorsByCityName("copenhagen"));
    }

}
