/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mappers;

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

//    public ArrayList<Book> getBooksMentioningCity(String cityName) {  
//        session = driver.session();
//        String cypher = "MATCH (a:Author)-[:WROTE]->(b:Book)-[:MENTIONS]->(c:City) WHERE c.city_name = '" + cityName + "' RETURN b.book_title,a.author_name";
//        ArrayList<Book> books = new ArrayList();
//        StatementResult result = session.run(cypher);
//        while (result.hasNext()) {
//            List<Value> values = result.next().values();
//            String bookTitle = values.get(0).asString();
//            String authorName = values.get(1).asString();
//            Author author = new Author();
//            author.name = authorName;
//            Book book = new Book();
//            book.title = bookeTitle;
//            book.authors.add(author);
//            books.add(book);
//        }
//        session.close();
//    }
//    public /*ArrayList<City>*/ void getAllCitiesByBookTitle(String bookTitle) {
//        session = driver.session();
////        String cypher = "MATCH (c:City)<-[:MENTIONS]-(b:Book) WHERE b.book_title = '" + bookTitle + "' RETURN c.city_name, c.latt, c.long";
//        String cypher = "MATCH (g:Geo)<-[:HAS]-(c:City)<-[:MENTIONS]-(b:Book) WHERE b.book_title = '" + bookTitle + "' RETURN c.city_name, g.latt, g.long";
//        ArrayList<City> cities = new ArrayList();
//        StatementResult result = session.run(cypher);
//        while (result.hasNext()) {
//
//            List<Value> values = result.next().values();
//            String cityName = values.get(0).asString();
//            String lattitude = values.get(1).asString();
//            String longitude = values.get(2).asString();
//            
//            City city = new City();
//            city.setName(cityName);
//            city.setLattitude(lattitude);
//            city.setLongitude(longitude);
//            cities.add(city);
//        }
//        session.close();
//        return cities
//    }
//    public /*ArrayList<Book>*/ void getAllBooksWrittenByAuthor(String author) {
//        session = driver.session();
//        String cypher = "MATCH (g:Geo)<-[:HAS]-(c:City)<-[:MENTIONS]-(b:Book)<-[:WROTE]-(a:Author) WHERE a.author_name = \"Dante Alighieri\" RETURN b.book_title, c.city_name, g.latt, g.long";
//        ArrayList<Book> books = new ArrayList();
//        
//        StatementResult result = session.run(cypher);
//        while (result.hasNext()) {
//
//            List<Value> values = result.next().values();
//            String bookTitle = values.get(0).asString();
//            String cityName = values.get(1).asString();
//            String lattitude = values.get(2).asString();
//            String longitude = values.get(3).asString();
//
//            HashMap<String, Book> books = new HashMap();
//            Book b = books.get(bookTitle);
//            if (b == null) {
//                b = new Book();
//                b.setTitle(bookTitle);
//                City c = new City();
//                c.setCityName(cityName);
//                c.setLatitude(lattitude);
//                c.setLongtitude(longitude);
//                b.getCities().add(c);
//                books.put(rs.getString(1), b);
//            } else {
//                City c = new City();
//                c.setCityName(rs.getString(2));
//                c.setLatitude(rs.getFloat(3));
//                c.setLongtitude(rs.getFloat(4));
//                b.getCities().add(c);
//            }
//            books.add(b);
//
//        }
//        session.close();
////        return cities
//    }

//    public ArrayList<Book> getBooksByGeoLocation(float lattitude, float longtitude) {
//        String cypher = "MATCH (b:Books)-[:MENTIONS]-(c:City)-[]";
//    }
    public static void main(String[] args) {
        GraphMapper mapper = new GraphMapper();
//        mapper.getAllCitiesByBookTitle("La Fiammetta");
    }

}
