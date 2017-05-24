package Mappers;


import java.sql.ResultSet;
import java.util.List;

public interface SqlMapperInterface {
    
    public ResultSet getAllCitiesByBookTitle(String bookTitle);

    public ResultSet getAuthorsByCityName(String cityName);

    public ResultSet getAllBooksWrittenByAuthor(String author);
    
    public ResultSet getBooksMentioningCity(float latitude, float longtitude, int vicinity);
    
}
