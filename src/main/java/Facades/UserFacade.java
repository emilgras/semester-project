/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import Mappers.MapperInterface;
import NewEntities.Book;
import NewEntities.City;
import java.util.ArrayList;

/**
 *
 * @author emilgras
 */
public class UserFacade implements UserFacadeInterface {

    private MapperInterface mapper;
    
    public UserFacade(MapperInterface mapper) { 
        this.mapper = mapper;
    }
    
    
    
    @Override
    public ArrayList<City> getAllCitiesByBookTitle(String bookTitle) {
        return mapper.getAllCitiesByBookTitle(bookTitle);
    }

    @Override
    public ArrayList<Book> getAuthorsByCityName(String cityName) {
        return mapper.getAuthorsByCityName(cityName);
    }

    @Override
    public ArrayList<Book> getAllBooksWrittenByAuthor(String author) {
        return mapper.getAllBooksWrittenByAuthor(author);
    }

    @Override
    public ArrayList<Book> getBooksByGeoLocation(float latitude, float longtitude) {
        return mapper.getBooksByGeoLocation(latitude, longtitude);
    }
    
}
