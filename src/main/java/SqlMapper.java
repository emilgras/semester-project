
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlMapper implements MapperInterface {

    Connection con;

    public List<String> getAllCitiesByBookTitle(String bookTitle) {

        List<String> cities = new ArrayList<String>();

        Map<String, List<String>> bookMap = new HashMap<String, List<String>>();

        cities.add("London");
        cities.add("Madrid");
        cities.add("Berlin");
        cities.add("Paris");
        cities.add("Copenhagen");

        bookMap.put("hp", cities);

        if (bookMap.get(bookTitle) != null) {
            return bookMap.get(bookTitle);
        }

        // If not found, return empty Array
        return new ArrayList<String>();
    }

    public List<String> getAuthorsByCityName(String cityName) {

        List<String> authors = new ArrayList<String>();

        Map<String, List<String>> cityMap = new HashMap<String, List<String>>();

        authors.add("Obama");
        authors.add("Putin");
        authors.add("Michael Jackson");
        authors.add("Lars Løkke Rasmussen");
        authors.add("Anders Hemmingsen");

        cityMap.put("copenhagen", authors);

        if (cityMap.get(cityName) != null) {
            return cityMap.get(cityName);
        }

        return new ArrayList<String>();
    }

    public List<String> getAllBooksWrittenByAuthor(String author) {

        List<String> books = new ArrayList<String>();

        Map<String, List<String>> booksMap = new HashMap<String, List<String>>();

        books.add("My best hours");
        books.add("Becoming a pirate");
        books.add("Best of Disney's");

        booksMap.put("Frank hansen", books);

        if (booksMap.get(author) != null) {
            return booksMap.get(author);
        }

        return books;
    }

    public List<String> getBooksMentioningCity(String cityName) {

        FileSearch fileSarch = new FileSearch();

        List<String> books = new ArrayList<>();

        List<File> files = new ArrayList<>();

        File book1 = new File(System.getProperty("user.dir") + "/src/main/java/book1.txt");
        File book2 = new File(System.getProperty("user.dir") + "/src/main/java/book2.txt");
        File book3 = new File(System.getProperty("user.dir") + "/src/main/java/book3.txt");

        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        files.add(book1);
        files.add(book2);
        files.add(book3);

        if (files != null) {

            for (File file : files) {
                if (fileSarch.containsString(file, cityName)) {
                    System.out.println(file + " was true for city: " + cityName);
                    books.add(file.getName());
                }
            }
        }

        return books;
    }

    @Override
    public ResultSet getBooksAndAuthorsByMentionsOfCityName(String CityName) {
        ResultSet rs = null;
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "lalelarsen", "frederik2000");
            String query = "Select books.`Title`, authors.`AuthorName`  from books  \n"
                    + "join mentions on books.`ID` = mentions.`BookId` \n"
                    + "join cities on mentions.`CityId` = cities.`ID`\n"
                    + "join authors on books.`AuthorID` = authors.`ID`\n"
                    + "where cities.`CityName` = '" + CityName + "'";
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
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

    public static void main(String[] args) throws SQLException {
        SqlMapper sm = new SqlMapper();
        ResultSet rs = sm.getBooksAndAuthorsByMentionsOfCityName("Copenhagen");
        while (rs.next()) {
            System.out.println(rs.getString(0)+ "," + rs.getString(1));
        }
    }
}
