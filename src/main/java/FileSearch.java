
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FileSearch {

    public static void main(String[] args) {
//        // Testing only
//        File file = new File("C:\\Cphbusiness - PBA\\test-data-semester-project\\semester-project\\src\\main\\java\\book1.txt");
//        String city = "søborg";
//        System.out.printf("Result of searching for %s in %s was %b\n", city, file.getName(), FileSearch.containsString(file, city));

        FileSearch searcher = new FileSearch();
//        FileHandler handler = new FileHandler();
//
//        File file = new File("files/1.txt");
//
//        ArrayList<String> fileRead = handler.readFile(FileHandler.READ_DIR);
//        String[][] cities = handler.extractCitiesFromFile(fileRead);
//
//        ArrayList<String> citiesList = new ArrayList();
//        for (String[] city : cities) {
//            String cityName = city[1];
//            citiesList.add(cityName);
//        }
//
//        searcher.findCitiesInFile(file, citiesList);

        String folderPath = "C:\\Users\\Frederik\\Desktop\\5bøger";
        File dir = new File(folderPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.getName().substring(child.getName().length() - 3, child.getName().length()).equals("rdf")) {
                    System.out.println("----------------");
                    File file = new File(folderPath + "\\" + child.getName());
                    String[] res = searcher.getTitleAndAuthorFromBook(file);
                    for(String s:res){
                        System.out.println(s);
                    }
                }
            }
        }
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

            // convenience - print all found cities in the book
            for (String str : foundCities.keySet()) {
                System.out.println("CITY FOUND: " + str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                /* ignore */ }
        }

    }

    public String[] getTitleAndAuthorFromBook(File file) {
        String Authors = "Authors: {";
        String Title = "";
        String BookID = "";
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            
            NodeList nl = document.getElementsByTagName("pgterms:agent");
            for (int i = 0; i < nl.getLength(); i++) {
                Element e = (Element)nl.item(i);
                Authors+= "\"" + e.getElementsByTagName("pgterms:name").item(0).getTextContent()+"\",";
            }
            Authors+="\b}";
            
            Title = "Title: {\"" + document.getElementsByTagName("dcterms:title").item(0).getTextContent() + "\"}";
            
            BookID = "BookID: {\"" + file.getName().substring(2, file.getName().length()-4) + "\"}";

        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] res = {BookID,Authors,Title};
        return res;
    }
}
