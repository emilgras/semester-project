
import java.sql.ResultSet;
import java.util.List;

public interface MapperInterface {
    
    public ResultSet getBooksAndAuthorsByMentionsOfCityName(String CityName);
    
    public ResultSet getGeoLocationByBookTitle(String bookTitle);
    
    public ResultSet getAllBooksAndGeoLocationsWrittenByAuthor(String Authorname);
    
    public ResultSet getAllBooksMentioningCityAtGeoLocation(float longtitude, float latitude);
    
}
