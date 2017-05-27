/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappers;

import NewEntities.Author;
import NewEntities.Book;
import NewEntities.City;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;   
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;

/**
 *
 * @author emilgras
 */
public class GraphMapper {

    private Driver driver;
    private Session session;

    public GraphMapper() {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "class"));
    }

    // Given a city name your application returns all book titles with corresponding authors that mention this city.
    public ArrayList<Book> getBooksMentioningCity(String cityName) {  
        session = driver.session();
        String cypher = "MATCH (a:Author)-[:WROTE]->(b:Book)-[:MENTIONS]->(c:City) WHERE c.city_name = '" + cityName + "' RETURN b.book_title,a.author_name";
        ArrayList<Book> books = new ArrayList();
        StatementResult result = session.run(cypher);
        while (result.hasNext()) {
            List<Value> values = result.next().values();
            String bookTitle = values.get(0).asString();
            String authorName = values.get(1).asString();
            Author author = new Author();
            author.setName(authorName);
            Book book = new Book();
            book.setTitle(bookTitle);
            book.getAuthors().add(author);
            books.add(book);
        }
        session.close();
        return books;
    }
    
    // Given a book title, your application plots all cities mentioned in this book onto a map.
    public ArrayList<City> getAllCitiesByBookTitle(String bookTitle) {
        session = driver.session();
        String cypher = "MATCH (c:City)<-[:MENTIONS]-(b:Book) WHERE b.book_title = \"" + bookTitle + "\" RETURN c.city_name, c.latt, c.long";
//        String cypher = "MATCH (g:Geo)<-[:HAS]-(c:City)<-[:MENTIONS]-(b:Book) WHERE b.book_title = \"" + bookTitle + "\" RETURN c.city_name, g.latt, g.long";
        ArrayList<City> cities = new ArrayList();
        StatementResult result = session.run(cypher);
        while (result.hasNext()) {

            List<Value> values = result.next().values();
            String cityName = values.get(0).asString();
            String lattitude = values.get(1).asString();
            String longitude = values.get(2).asString();
            City city = new City();
            city.setCityName(cityName);
            city.setLatitude(lattitude);
            city.setLongtitude(longitude);
            cities.add(city);
        }
        session.close();
        return cities;
    }
    
    // Given an author name your application lists all books written by that author and plots all cities mentioned in any of the books onto a map.
    public ArrayList<Book> getAllBooksWrittenByAuthor(String author) {
        session = driver.session();
        String cypher = "MATCH (c:City)<-[:MENTIONS]-(b:Book)<-[:WROTE]-(a:Author) WHERE a.author_name = \"" + author + "\" RETURN b.book_title, c.city_name, c.latt, c.long";
        ArrayList<Book> books = new ArrayList();
        Map<String, Book> booksTemp = new HashMap();
        StatementResult result = session.run(cypher);
        while (result.hasNext()) {

            List<Value> values = result.next().values();
            String bookTitle = values.get(0).asString();
            String cityName = values.get(1).asString();
            String lattitude = values.get(2).asString();
            String longitude = values.get(3).asString();

            
            Book b = booksTemp.get(bookTitle);
            if (b == null) {
                b = new Book();
                b.setTitle(bookTitle);
                City c = new City();
                c.setCityName(cityName);
                c.setLatitude(lattitude);
                c.setLongtitude(longitude);
                b.getCities().add(c);
                booksTemp.put(bookTitle, b);
                books.add(b);
            } else {
                City c = new City();
                c.setCityName(cityName);
                c.setLatitude(lattitude);
                c.setLongtitude(longitude);
                b.getCities().add(c);
            }
            

        }
        session.close();
        return books;
    }

    // Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.
    public ArrayList<Book> getBooksByGeoLocation(float lattitude, float longtitude) {
        int distance = 1000;
        session = driver.session();
                
        String cypher = "" +
            "MATCH (b:Book)-[:MENTIONS]->(c:City)\n" +
            "WITH point({ longitude: toInteger(c.long), latitude: toInteger(c.latt) }) AS aPoint, point({ longitude: " + longtitude + ", latitude: " + lattitude + " }) AS bPoint, b, c\n" +
            "WITH DISTINCT round(distance(aPoint, bPoint)) AS distance, b, c\n" +
            "ORDER BY distance DESC\n" +
            "WHERE distance / 1000 < "+ distance +"\n" +
            "RETURN b.book_title";

        ArrayList<Book> books = new ArrayList();
        StatementResult result = session.run(cypher);
        while (result.hasNext()) {
            List<Value> values = result.next().values();
            String bookTitle = values.get(0).asString();
            Book book = new Book();
            book.setTitle(bookTitle);
            books.add(book);
        }
        session.close();
        return books;
    }
    
    public static void main(String[] args) {
        GraphMapper mapper = new GraphMapper();
        // Test Query 1
        System.out.println("Query 1: " + mapper.getBooksMentioningCity("Much").size());
        
        // Test Query 2
        System.out.println("Query 2: " + mapper.getAllCitiesByBookTitle("Divine Comedy, Cary's Translation, Hell").size());
        
        // Test Query 3
        ArrayList<Book> books = mapper.getAllBooksWrittenByAuthor("Dante Alighieri");
        for (Book book : books) {
            System.out.println("title: " + book.getTitle() + ", city size: " + book.getCities().size());
        }
    
        // Test Query 4
        System.out.println("Query 4: " + mapper.getBooksByGeoLocation((float)52.52437, (float)13.41053).get(0).getTitle());
        
    }

}
