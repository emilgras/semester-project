
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

    public String getAuthorFromFile(File file) {
        Scanner in = null;

        try {
            in = new Scanner(new FileReader(file));
            // try to read file
            boolean done = false;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                // split line into array of words
                String[] columns = line.split(" +");

                for (int i = 0; i < columns.length; i++) {
                    if (columns[i].equals("Author:")) {
                        String Author = "";
                        for (int j = 1; j < columns.length; j++) {
                            Author += columns[j] + " ";
                        }
                        Author += "\b";
                        return Author;
                    }
                }
            }
        } catch (Exception e) {

        }

        try {
            in = new Scanner(new FileReader(file));
            // try to read file
            boolean done = false;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                // split line into array of words
                String[] columns = line.split(" +");

                if (columns.length == 1) {
                    String nextLine = in.nextLine();
                    String endLine = in.nextLine();

                    String[] nextColumns = nextLine.split(" +");
                    String[] endColumns = endLine.split(" +");

                    if (nextColumns[0].toLowerCase().equals("by") && endColumns.length == 1) {
                        String Author = "";
                        for (int i = 1; i < nextColumns.length; i++) {
                            Author += nextColumns[i] + " ";
                        }
                        Author += "\b";
                        return Author;
                    }
                }

            }

        } catch (Exception e) {

        }
        return null;
    }

    public String getTitleFromFile(File file) {
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(file));
            // try to read file
            boolean done = false;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                // split line into array of words
                String[] columns = line.split(" +");

                for (int i = 0; i < columns.length; i++) {
                    if (columns[i].equals("Title:")) {
                        String Title = "";
                        for (int j = 1; j < columns.length; j++) {
                            Title += columns[j] + " ";
                        }
                        Title += "\b";
                        return Title;
                    }
                }
            }
        } catch (Exception e) {

        }

        try {
            in = new Scanner(new FileReader(file));
            // try to read file
            boolean done = false;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String nextLinee = in.next();
                //split line into array of words
                String[] words = nextLinee.split(" +");

//                while (words.length == 1 && words[0].length() == 1) {
//                    line = in.nextLine();
//                    nextLinee = in.next();
//                    words = nextLinee.split(" +");
//                }
                String[] columns = line.split(" +");

                if (columns.length == 1) {
                    //this line will contain the title
                    String nextLine = in.nextLine();
                    //this should then be empty
                    String emptyLine = in.nextLine();
                    //this should start with a "by"
                    String byLine = in.nextLine();

                    String[] nextColumns = nextLine.split(" +");
                    String[] emptyColumns = emptyLine.split(" +");
                    String[] byColumns = byLine.split(" +");
//                    if(emptyColumns.length == 1){
//                    System.out.println("...........");
//                    System.out.print("next:  ");
//                    for (String word : nextColumns) {
//                        System.out.print(word);
//                    }
//                    System.out.println("");
//                    System.out.print("empty:  ");
//                    for (String word : emptyColumns) {
//                        System.out.print(word);
//                    }
//                    System.out.println("");
//                    System.out.print("by:  ");
//                    for (String word : byColumns) {
//                        System.out.print(word);
//                    }
//                    System.out.println("");
//                    }
                    if (emptyColumns.length == 1 && byColumns[0].toLowerCase().equals("by")) {
                        String Title = "";
                        for (String word : nextColumns) {
                            Title += word + " ";
                        }
                        Title += "\b";
                        return Title;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
