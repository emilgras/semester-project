package Mappers;

import Entities.nosql.BookGraphEntity;
import Entities.nosql.CityGraphEntity;
import Entities.nosql.GeoLocation;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author emilgras
 */
public interface NoSqlMapperInterface {
    public List<BookGraphEntity> getBooksMentioningCity(String cityName);
    
    public List<CityGraphEntity> getAllCitiesByBookTitle(String bookTitle);
    
    public List<BookGraphEntity> getAllBooksWrittenByAuthor(String author);
    
    public List<BookGraphEntity> getAuthorsByCityName(GeoLocation geo);
}
