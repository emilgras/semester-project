package Mappers;


import NewEntities.Book;
import NewEntities.City;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public interface MapperInterface {
    
    public ArrayList<City> getAllCitiesByBookTitle(String bookTitle);

    public ArrayList<Book> getAuthorsByCityName(String cityName);

    public ArrayList<Book> getAllBooksWrittenByAuthor(String author);
    
    public ArrayList<Book> getBooksByGeoLocation(float latitude, float longtitude);
    
}
