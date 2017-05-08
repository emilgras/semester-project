
import java.sql.ResultSet;
import java.util.List;

public class NoSqlMapper implements MapperInterface{

    @Override
    public ResultSet getBooksAndAuthorsByMentionsOfCityName(String CityName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getGeoLocationByBookTitle(String bookTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getAllBooksAndGeoLocationsWrittenByAuthor(String Authorname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getAllBooksMentioningCityAtGeoLocation(float longtitude, float latitude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
