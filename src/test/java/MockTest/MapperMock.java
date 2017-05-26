/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MockTest;

import Facades.UserFacadeInterface;
import NewEntities.Author;
import NewEntities.Book;
import NewEntities.City;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author emilgras
 */
public class MapperMock implements Mappers.MapperInterface {

    // Add fake database data here
    // 
    private HashMap<String, ArrayList<City>> cititesByBookTitle;
    private HashMap<String, ArrayList<Book>> booksByCityName;
    private HashMap<String, ArrayList<Book>> booksByAuthor;
    private HashMap<String, ArrayList<Book>> booksByGeoLocation;
    
    public MapperMock() {
        // Fake entities
        City c1 = new City(123, "Copenhagen", "", "");
        City c2 = new City(123, "Berlin", "", "");
        City c3 = new City(123, "Amsterdam", "", "");
        City c4 = new City(123, "Bornholm", "", "");
        City c5 = new City(123, "Madrid", "", "");
        
        Book b1 = new Book("3456", "I am a little butterfly");
        Book b2 = new Book("2374", "I am a little butterfly");
        Book b3 = new Book("9823", "I am a little butterfly");
        Book b4 = new Book("6894", "I am a little butterfly");
        Book b5 = new Book("5703", "I am a little butterfly");
        
        
        
        
        
        // Query 1
        this.cititesByBookTitle = new HashMap();
        ArrayList<City> cities = new ArrayList();
        cities.add(c1);
        cities.add(c2);
        cities.add(c3);
        cities.add(c4);
        cities.add(c5);
        this.cititesByBookTitle.put("Copenhagen", cities);  
    }
    
    @Override
    public ArrayList<City> getAllCitiesByBookTitle(String bookTitle) {
        return cititesByBookTitle.get(bookTitle);
    }

    @Override
    public ArrayList<Book> getAuthorsByCityName(String cityName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Book> getAllBooksWrittenByAuthor(String author) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Book> getBooksByGeoLocation(float latitude, float longtitude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
