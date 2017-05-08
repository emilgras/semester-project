
import java.util.List;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Frederik
 */
public interface Mapper {
    
    public List<String> getAllCitiesByBookTitle(String bookTitle);

    public List<String> getAuthorsByCityName(String cityName);

    public List<String> getAllBooksWrittenByAuthor(String author);
    
    public List<String> getBooksMentioningCity(String cityName);
}
