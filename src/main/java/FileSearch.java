
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FileSearch {

    public static void main(String[] args) {
//        // Testing only
//        File file = new File("C:\\Cphbusiness - PBA\\test-data-semester-project\\semester-project\\src\\main\\java\\book1.txt");
//        String city = "søborg";
//        System.out.printf("Result of searching for %s in %s was %b\n", city, file.getName(), FileSearch.containsString(file, city));

        FileSearch searcher = new FileSearch();
        FileHandler handler = new FileHandler();

        File file = new File("files/1.txt");

        ArrayList<String> fileRead = handler.readFile(FileHandler.READ_DIR);
        String[][] cities = handler.extractCitiesFromFile(fileRead);

        ArrayList<String> citiesList = new ArrayList();
        for (String[] city : cities) {
            String cityName = city[1];
            citiesList.add(cityName);
        }

        searcher.findCitiesInFile(file, citiesList);
    }

    public static boolean containsString(File file, String searchString) {
        boolean result = false;
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(file));
            while (in.hasNextLine() && !result) {
                result = in.nextLine().toLowerCase().indexOf(searchString.toLowerCase()) >= 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                /* ignore */ }
        }
        return result;
    }

    public void findCitiesInFile(File file, ArrayList<String> cityNames) {
        HashMap<String, String> foundCities = new HashMap();
        
        Scanner in = null;
        try {
            // try to read file
            in = new Scanner(new FileReader(file));
            
            // we will map ALL words in a hashmap. Easy to search for cities later
            HashMap<String, String> wordMapping = new HashMap();
            while (in.hasNextLine()) {
                
                // read one line at a time
                String line = in.nextLine();
                
                // split line into array of words
                String[] columns = line.split(" ");

                
                for (String col : columns) {
                    // for every word remove special chars
                    String trimmed = col.replaceAll("[+.^:,;]", "").toLowerCase();
                    
                    // map the word into the hashmap
                    wordMapping.put(trimmed, trimmed);
                }

            }
            
            // now let's search for all the cities mentioned in the book
            // we will loop through all 23+ thousands cities, and for every cityname
            for (String cityName : cityNames) {
                // if the current city is mentioned in the word hashmap, then
                if (wordMapping.containsKey(cityName.toLowerCase())) {
                    // add it to the final found cities map
                    foundCities.put(cityName, cityName);
                }
            }
            
            
            
            System.out.println("book id: " + 2367);
            
            // TODO: call method to get the unique id from this current book
            
            FileHandler handler = new FileHandler();
            String[][] connections = new String[foundCities.size()][2];
            // convenience - print all found cities in the book
            
            
            int index = 0;
            for (String city : foundCities.keySet()) {
                
                int id = index + 1;
                System.out.println(city + "," + id);
                connections[index][0] = "" + id;
                connections[index][1] = city;
                index++;
            }
            System.out.println("SIZE: " + connections.length);
            boolean result = handler.writeFile(connections, "files/book_city_edges.csv", "book_id,city_name\n");
            System.out.println("------ CSV DONE! ------- " + result);

            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                /* ignore */ 
            }
        }

    }

}
