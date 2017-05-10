/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author emilgras
 */
public class ExtractCitiesTest {
    
    private static FileHandler handler;
    private static ArrayList<String> file;
    private static String[][] cities;
    private static String[][] geolocations;
    private static String[][] connections;
    
    private final String READ_DIR = "files/cities15000.txt";
    private final String CITY_NODES_DIR = "/files/city_nodes.csv";
    private final String GEO_NODES_DIR = "files/geo_nodes.csv";
    private final String CITY_GEO_EDGES_DIR = "files/city_geo_edges.csv";
    private final String CITY_NODES_HEADER = "city_id,city\n";
    private final String GEO_NODES_HEADER = "geo_id,latitude,longitude\n";
    private final String CITY_GEO_EDGES_HEADER = "city_id,geo_id\n";
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("BeforeClass - initializing FileHandler \n\t\t-------------------- \n");
        handler = new FileHandler();
        System.out.println("BeforeClass - reading file and extracting data to use in tests \n\t\t-------------------- \n");
        file = handler.readFile(FileHandler.READ_DIR);
        cities = handler.extractCitiesFromFile(file);
        geolocations = handler.extractGeoLocationsFromFile(file);
        connections = handler.extractConnectionsFromFile(file);
    }
    
    @AfterClass
    public static void tearDownClass() {
        handler.removeFile(FileHandler.CITY_NODES_DIR);
        handler.removeFile(FileHandler.GEO_NODES_DIR);
        handler.removeFile(FileHandler.CITY_GEO_EDGES_DIR);
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("Starting test... " + description.getMethodName() + "\n");
        }
    };
    
    @Test
    @DisplayName("should return a list with the expected size 23674")
    public void testReadFile () {
        int expectedNumberOfLines = 23674;

        ArrayList<String> file = handler.readFile(READ_DIR);
        int actualNumberOfLines = file.size();
        
        assertThat(expectedNumberOfLines, equalTo(actualNumberOfLines));
    }
    
    @Test
    @DisplayName("should return a 2 dimensional array with expected number og rows and columns")
    public void testExtractCitiesFromFile () {
        int expectedNumberOfRows = 23674;
        int expectedNumberOfColumns = 2;
        
        String[][] cities = handler.extractCitiesFromFile(file);
        int actualNumberOfRows = cities.length;
        int actualNumberOfColumns = cities[0].length;
                
        assertThat(actualNumberOfRows, equalTo(expectedNumberOfRows));
        assertThat(actualNumberOfColumns, equalTo(expectedNumberOfColumns)); 
    }
    
    @Test
    @DisplayName("should return a 2 dimensional array with expected number og rows and columns")
    public void testExtractGeoLocationsFromFile () {
        int expectedNumberOfRows = 23674;
        int expectedNumberOfColumns = 3;
        
        String[][] geolocations = handler.extractGeoLocationsFromFile(file);
        int actualNumberOfRows = geolocations.length;
        int actualNumberOfColumns = geolocations[0].length;
                
        assertThat(actualNumberOfRows, equalTo(expectedNumberOfRows));
        assertThat(actualNumberOfColumns, equalTo(expectedNumberOfColumns)); 
    }
    
    @Test
    @DisplayName("should return a 2 dimensional array with expected number og rows and columns")
    public void testExtractConnectionsFromFile () {
        int expectedNumberOfRows = 23674;
        int expectedNumberOfColumns = 2;
        
        String[][] connections = handler.extractConnectionsFromFile(file);
        int actualNumberOfRows = connections.length;
        int actualNumberOfColumns = connections[0].length;
                
        assertThat(actualNumberOfRows, equalTo(expectedNumberOfRows));
        assertThat(actualNumberOfColumns, equalTo(expectedNumberOfColumns)); 
    }
    
    @Test
    @DisplayName("should return true if all files are created successfully")
    public void testWriteFile () {
        boolean expectedResult = true;

        String filePath = System.getProperty("user.dir") + CITY_NODES_DIR;
        
        boolean actualResult = handler.writeFile(cities, filePath, CITY_NODES_HEADER);

        assertThat(actualResult, equalTo(expectedResult));
    }
    
}
