package Mappers;


import java.util.List;

public interface SqlMapperInterface {
    
    public List<String> getAllCitiesByBookTitle(String bookTitle);

    public List<String> getAuthorsByCityName(String cityName);

    public List<String> getAllBooksWrittenByAuthor(String author);
    
    public List<String> getBooksMentioningCity(String cityName);
}
