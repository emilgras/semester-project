package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FileSearch {

    FileHandler fh = new FileHandler();
    static FileSearch fs = new FileSearch();

    public static void main(String[] args) {
//        String path = "C:\\Users\\Frederik\\Desktop\\5bøger\\TempCSV\\mentions.csv";
        String pathFrom = "/Users/emilgras/Desktop/Books/test/";
        String pathTo = "files/mentions.csv";
        fs.createCSVCityMentions(pathFrom, pathTo);
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

    public HashMap<String, String> findCitiesInFile(File file, ArrayList<String> cityNames) {
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

            //System.out.println("book id: " + 2367);
            // TODO: call method to get the unique id from this current book
            FileHandler handler = new FileHandler();
            String[][] connections = new String[foundCities.size()][2];
            // convenience - print all found cities in the book

            int index = 0;
            for (String city : foundCities.keySet()) {

                int id = index + 1;
                //System.out.println(city + "," + id);
                connections[index][0] = "" + id;
                connections[index][1] = city;
                index++;
            }
            //System.out.println("SIZE: " + connections.length);
            boolean result = handler.writeFile(connections, "files/book_city_edges.csv", "book_id,city_name\n");
            //System.out.println("------ CSV DONE! ------- " + result);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                /* ignore */
            }
        }
        return foundCities;
    }

    public String[][] getTitleAndAuthorFromBook(File file) {
        //this method returns an array of string arrays, because its the easiest
        //way without having to make a specifc book class.

        ArrayList<String> AuthorsList = new ArrayList();
        String Title = "";
        String BookID = "";
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList nl = document.getElementsByTagName("pgterms:agent");
            for (int i = 0; i < nl.getLength(); i++) {
                Element e = (Element) nl.item(i);
                AuthorsList.add(e.getElementsByTagName("pgterms:name").item(0).getTextContent());
            }
            Title = document.getElementsByTagName("dcterms:title").item(0).getTextContent();
            Title = Title.replace("\n", ": ").replace("\r", "");;

            BookID = file.getName().substring(2, file.getName().length() - 4);

        } catch (Exception e) {

        }
        String[] AuthorsA = new String[AuthorsList.size()];
        for (int i = 0; i < AuthorsList.size(); i++) {
            AuthorsA[i] = AuthorsList.get(i);
        }

        String[] TitleA = {Title};
        String[] BookIDA = {BookID};
        String[][] res = {BookIDA, AuthorsA, TitleA};
        return res;
    }

    public void createCSVFromMeta(String csvPath, String folderPath) {
        //A hashmap to keep track of authors with and without id
        HashMap<String, String> Authors = new HashMap<>();
        //this int is used to assign the next id to an author without and id
        int AuthorIDIndex = 0;

        //headers to the csv
        String BookHeader = "book_id|title\n";
        String AuthorHeader = "author_id|author_name\n";
        String WroteHeader = "author_id|book_id\n";

        //arrays for the data for the csv.
        //ArrayList since we do not know the size of the files yet. 
        //these will be converted to normal arrays in the end.
        ArrayList<String[]> BookCSVData = new ArrayList<>();
        ArrayList<String[]> AuthorCSVData = new ArrayList<>();
        ArrayList<String[]> WroteCSVData = new ArrayList<>();

        //create the folder file. and then a list of all the children
        File dir = new File(folderPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            //iterate through the folder
            int limit = 0;
            for (File child : directoryListing) {
                limit++;
                //because the folder you get from unzipping the metadata zip is
                //filled with a folder for each book, which then just have 1 rdf file
                //we need to do the same step agian. i added "new" to the front of the name
                //to indicate its in the book folder
                String newPath = folderPath + "\\" + child.getName();
                File newDir = new File(newPath);
                File[] newDirectoryListing = newDir.listFiles();
                if (newDirectoryListing != null) {
                    for (File newChild : newDirectoryListing) {
                        if (newChild.getName().substring(newChild.getName().length() - 3, newChild.getName().length()).equals("rdf")) {
                            File file = new File(newPath + "\\" + newChild.getName());

                            //To see why the method returns an array of string arrays.
                            //see the method below
                            String[][] res = fs.getTitleAndAuthorFromBook(file);

                            //make a row for the book.
                            String[] bookRow = new String[2];

                            //assign bookID and bookTitle for later use
                            String bookID = res[0][0];
                            String bookTitle = res[2][0];

                            //make row and add it to the list
                            if (!bookTitle.equals("")) {
                                bookRow[0] = bookID;
                                bookRow[1] = bookTitle;
                                BookCSVData.add(bookRow);
                            }
                            //loop through the authors of 1 book.
                            for (String BookAuthor : res[1]) {
                                //make rows for author and wrote
                                String[] authorRow = new String[2];
                                String[] wroteRow = new String[2];
                                //get the authors id. returns null if author
                                //do not yet have an id.
                                String id = Authors.get(BookAuthor);
                                //if null. assign id.
                                if (id == null) {
                                    Authors.put(BookAuthor, AuthorIDIndex + "");
                                    AuthorIDIndex++;
                                    id = Authors.get(BookAuthor);
                                    //make row and add it to the list
                                    authorRow[0] = id;
                                    authorRow[1] = BookAuthor;
                                    AuthorCSVData.add(authorRow);
                                }

                                //make row and add it to the list
                                wroteRow[0] = id;
                                wroteRow[1] = bookID;
                                WroteCSVData.add(wroteRow);
                            }
                        }
                    }
                }
                if (limit > 20) {
                    break;
                }
            }
        }
        String[][] BookArray = new String[BookCSVData.size()][2];
        String[][] AuthorArray = new String[AuthorCSVData.size()][2];
        String[][] WroteArray = new String[WroteCSVData.size()][2];

        for (int i = 0; i < BookCSVData.size(); i++) {
            BookArray[i] = BookCSVData.get(i);
        }
        for (int i = 0; i < AuthorCSVData.size(); i++) {
            AuthorArray[i] = AuthorCSVData.get(i);
        }
        for (int i = 0; i < WroteCSVData.size(); i++) {
            WroteArray[i] = WroteCSVData.get(i);
        }
        fh.writeFile(BookArray, csvPath + "\\books.csv", BookHeader);
        fh.writeFile(AuthorArray, csvPath + "\\authors.csv", AuthorHeader);
        fh.writeFile(WroteArray, csvPath + "\\wrote.csv", WroteHeader);
    }

    public void createCSVCityMentions(String folderPath, String pathTo) {
        File dir = new File(folderPath);
        File[] directoryListing = dir.listFiles();
        ArrayList<String> fileRead = fh.readFile(Utilities.FileHandler.READ_DIR);
        String[][] cities = fh.extractCitiesFromFile(fileRead);
        ArrayList<String[]> dataToCSV = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Queue<File> files = new ArrayDeque();
        for (File file : directoryListing) {
            if (file.getName().substring(file.getName().length() - 4, file.getName().length()).equals(".txt")) {
                files.add(file);
            }
        }

        HashMap<String, String> cityToKey = new HashMap<>();
        for (String[] city : cities) {
            cityToKey.put(city[1], city[0]);
        }

        ArrayList<String> citiesList = new ArrayList();
        for (String[] city : cities) {
            String cityName = city[1];
            citiesList.add(cityName);
        }

        if (directoryListing != null) {
            while (!files.isEmpty()) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        File file = files.poll();
                        if (file != null) {
                            System.out.println(file.getName());
                            String bookID = file.getName().substring(0, file.getName().length() - 4);
                            HashMap<String, String> foundCities = fs.findCitiesInFile(file, citiesList);
                            for (String s : foundCities.keySet()) {
                                String cityID = cityToKey.get(s);
                                String[] row = {bookID, cityID};
                                dataToCSV.add(row);
                            }
                        }
                    }
                };
                executor.execute(t);
            }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
        }
        String[][] dataToCSVArray = new String[dataToCSV.size()][2];
        for (int i = 0; i < dataToCSV.size(); i++) {
            String[] s = dataToCSV.get(i);
            dataToCSVArray[i] = s;
        }
        fh.writeFile(dataToCSVArray, pathTo, "book_id|city_id\n");
    }
}
