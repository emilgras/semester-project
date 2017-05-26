/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import NewEntities.Book;
import NewEntities.City;
import java.util.ArrayList;

/**
 *
 * @author emilgras
 */
public interface UserFacadeInterface {
    public ArrayList<City> getAllCitiesByBookTitle(String bookTitle);

    public ArrayList<Book> getAuthorsByCityName(String cityName);

    public ArrayList<Book> getAllBooksWrittenByAuthor(String author);
    
    public ArrayList<Book> getBooksByGeoLocation(float latitude, float longtitude);
}
