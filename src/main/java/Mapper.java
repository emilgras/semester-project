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

    public static void main(String[] args) {
        System.out.println("From mapper - does exist?: " + getAllCitiesByBookTitle("test"));
        System.out.println("From mapper: " + getAllCitiesByBookTitle("hp"));
    }
}
