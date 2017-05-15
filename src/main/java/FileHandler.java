
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author emilgras
 */
public class FileHandler {

    // MARK: - Public Constants
    static final String READ_DIR = "files/cities15000.txt";
    static final String CITY_NODES_DIR = "files/city_nodes.csv";
    static final String GEO_NODES_DIR = "files/geo_nodes.csv";
    static final String CITY_GEO_EDGES_DIR = "files/city_geo_edges.csv";

    static final String CITY_NODES_HEADER = "city_id,city\n";
    static final String GEO_NODES_HEADER = "geo_id,latitude,longitude\n";
    static final String CITY_GEO_EDGES_HEADER = "city_id,geo_id\n";
    
    // MARK: - Private Constants
    private final String DELIMITER = "\t";
    private final int CITY_POSITION = 1;
    private final int LATITUDE_POSITION = 4;
    private final int LONGITUDE_POSITION = 5;
    private final int ROWS = 23674;

    public FileHandler() {
    }

    // MARK: - File CRUD operations
    public ArrayList<String> readFile(String dir) {
        ArrayList<String> lines = new ArrayList();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(dir))) {
            stream.forEach(row -> lines.add(row));
        } catch (IOException e) {
            e.printStackTrace();
            return lines;
        }
        return lines;
    }

    public boolean writeFile(String[][] data, String dir, String header) {
        try {

            // Does file exist? Else create
            File file = new File(dir);
            if (!file.exists()) {
                System.out.println("File does not exist! - creating file: " + dir);
                file.createNewFile();
            }

            // Initialize writer
            FileOutputStream fileWriter = new FileOutputStream(dir);

            fileWriter.write(header.getBytes());

            String csvRow = "";

            // add all the cities and geolocations
            for (int i = 0; i < data.length; i++) {

                for (int j = 0; j < data[i].length; j++) {
                    if (j != data[i].length - 1) {
                        csvRow += data[i][j];
                    } else {
                        // this is the last column - we add a new line
                        csvRow += data[i][j] + "\n";
                    }
                }
                fileWriter.write(csvRow.getBytes());
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeFile(String dir){
        File file=new File(dir);
	if(file.exists()){
            if(file.delete()) {
		return true;
            }
        }
        return false;
    }

    // MARK: - Helper Methods
    public String[][] extractCitiesFromFile(ArrayList<String> file) {
        String[][] cities = new String[ROWS][2];
        for (int i = 0; i < file.size(); i++) {
            String row = file.get(i);
            String[] data = row.split(DELIMITER);
            int id = i + 1;
            cities[i][0] = "" + id;
            cities[i][1] = data[CITY_POSITION];
        }
        return cities;
    }

    public String[][] extractGeoLocationsFromFile(ArrayList<String> file) {
        String[][] geolocations = new String[ROWS][3];
        for (int i = 0; i < file.size(); i++) {
            String row = file.get(i);
            String[] data = row.split(DELIMITER);
            int id = i + 1;
            geolocations[i][0] = "" + id;
            geolocations[i][1] = data[LATITUDE_POSITION];
            geolocations[i][2] = data[LONGITUDE_POSITION];
        }
        return geolocations;
    }

    public String[][] extractConnectionsFromFile(ArrayList<String> file) {
        String[][] connections = new String[ROWS][2];
        for (int i = 0; i < file.size(); i++) {
            int id = i + 1;
            connections[i][0] = "" + id;
            connections[i][1] = "" + id;
        }
        return connections;
    }

    // MARK: - Main
    public static void main(String[] args) {
        FileHandler handler = new FileHandler();

//        // read file
//        ArrayList<String> file = handler.readFile(FileHandler.READ_DIR);
//        System.out.println("FILE SIZE=" + file.size() + "\n\n");
//
//        // extract cities
//        String[][] cities = handler.extractCitiesFromFile(file);
//        System.out.println("CITY_ROWS=" + cities.length + ", CITY_CLUMNS=" + cities[0].length);
//        System.out.println("FIRST ROW=" + cities[0][0] + "," + cities[0][1]);
//        
//        // write file to csv format
//        boolean writeResult = handler.writeFile(cities, FileHandler.CITY_NODES_DIR, FileHandler.CITY_NODES_HEADER);
//        System.out.println("FILE_CREATED=" + writeResult);
//        
//        // delete file
//        boolean removeResult = handler.removeFile(FileHandler.CITY_NODES_DIR);
//        System.out.println("FILE_REMOVED=" + removeResult);

        // Run this to create necessary CSV files
        ArrayList<String> file = handler.readFile(FileHandler.READ_DIR);
        
        String[][] cities = handler.extractCitiesFromFile(file);
        boolean writeResult1 = handler.writeFile(cities, FileHandler.CITY_NODES_DIR, FileHandler.CITY_NODES_HEADER);
        System.out.println("FILE_CREATED=" + writeResult1);
        
        String[][] geolocations = handler.extractGeoLocationsFromFile(file);
        boolean writeResult2 = handler.writeFile(geolocations, FileHandler.GEO_NODES_DIR, FileHandler.GEO_NODES_HEADER);
        System.out.println("FILE_CREATED=" + writeResult2);
        
        String[][] connections = handler.extractConnectionsFromFile(file);
        boolean writeResult3 = handler.writeFile(connections, FileHandler.CITY_GEO_EDGES_DIR, FileHandler.CITY_GEO_EDGES_HEADER);
        System.out.println("FILE_CREATED=" + writeResult3);
    }

}
