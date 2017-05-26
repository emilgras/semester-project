package Mappers;

import Entities.sql.Authors;
import Entities.sql.Books;
import Entities.sql.Wrote;
import NewEntities.Author;
import NewEntities.Book;
import NewEntities.City;
import Utilities.FileSearch;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SqlMapper implements MapperInterface{

<<<<<<< Updated upstream
    
    String host = "jdbc:mysql://localhost:3306/booksdb";
    String uName = "root";
    String uPass = "pwd";
    
//    String host = "jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    String uName = "root";
//    String uPass = "frederik2000";
=======
    String host = "jdbc:mysql://localhost:3307/books?zeroDateTimeBehavior=convertToNull&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String uName = "root";
    String uPass = "pwd";
>>>>>>> Stashed changes

    public ArrayList<City> getAllCitiesByBookTitle(String bookTitle) {
        ArrayList<City> result = new ArrayList();

        String query = "select cities.`CityName`,cities.latitude,cities.longitude from cities\n"
                + "inner join mentions on mentions.`CityId` = cities.`CityID`\n"
                + "inner join books on books.`BookID` = mentions.`BookId`\n"
                + "where books.`Title` = ?;";

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(host, uName, uPass);
            st = con.prepareStatement(query);
            st.setString(1, bookTitle);
            rs = st.executeQuery();

            try {
                while (rs.next()) {
                    City c = new City();
                    c.setCityName(rs.getString(1));
                    c.setLatitude(""+rs.getFloat(2));
                    c.setLongtitude(""+rs.getFloat(3));
                    result.add(c);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SqlMapper.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            }
        }

        return result;
    }

    public ArrayList<Book> getAuthorsByCityName(String cityName) {
        ArrayList<Book> result = new ArrayList();
        String query = "select books.`Title`, authors.`AuthorName` from books \n"
                + "inner join wrote on wrote.`BookId` = books.`BookID`\n"
                + "inner join authors on authors.`AuthorID` = wrote.`AuthorId`\n"
                + "inner join mentions on mentions.`BookId` = books.`BookID`\n"
                + "inner join cities on cities.`CityID` = mentions.`CityId`\n"
                + "where cities.`CityName` = ?;";

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(host, uName, uPass);
            st = con.prepareStatement(query);
            st.setString(1, cityName);
            rs = st.executeQuery();

            HashMap<String, Book> books = new HashMap();
            try {
                while (rs.next()) {
                    Book b = books.get(rs.getString(1));
                    if (b == null) {
                        b = new Book();
                        b.setTitle(rs.getString(1));
                        Author a = new Author();
                        a.setName(rs.getString(2));
                        b.getAuthors().add(a);
                        books.put(rs.getString(1), b);
                    } else {
                        Author a = new Author();
                        a.setName(rs.getString(2));
                        b.getAuthors().add(a);
                    }
                    result.add(b);

                }
            } catch (SQLException ex) {
                Logger.getLogger(SqlMapper.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            }
        }

        return result;
    }

    public ArrayList<Book> getAllBooksWrittenByAuthor(String author) {
        ArrayList<Book> result = new ArrayList();
        String query = "select books.Title, cities.`CityName`,cities.latitude,cities.longitude from books\n"
                + "inner join mentions on mentions.`BookId` = books.`BookID`\n"
                + "inner join cities on cities.`CityID` = mentions.`CityId`\n"
                + "inner join wrote on wrote.`BookId` = books.`BookID`\n"
                + "inner join authors on authors.`AuthorID` = wrote.`AuthorId`\n"
                + "where authors.`AuthorName` = ?;";

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(host, uName, uPass);
            st = con.prepareStatement(query);
            st.setString(1, author);
            rs = st.executeQuery();

            HashMap<String, Book> books = new HashMap();
            try {
                while (rs.next()) {
                    Book b = books.get(rs.getString(1));
                    if (b == null) {
                        b = new Book();
                        b.setTitle(rs.getString(1));
                        City c = new City();
                        c.setCityName(rs.getString(2));
                        c.setLatitude(""+rs.getFloat(3));
                        c.setLongtitude(""+rs.getFloat(4));
                        b.getCities().add(c);
                        books.put(rs.getString(1), b);
                    } else {
                        City c = new City();
                        c.setCityName(rs.getString(2));
                        c.setLatitude(""+rs.getFloat(3));
                        c.setLongtitude(""+rs.getFloat(4));
                        b.getCities().add(c);
                    }
                    result.add(b);

                }
            } catch (SQLException ex) {
                Logger.getLogger(SqlMapper.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            }
        }

        return result;
    }

    public ArrayList<Book> getBooksByGeoLocation(float latitude, float longtitude) {
        ArrayList<Book> result = new ArrayList();
        String query = "select books.`Title` from books\n"
                + "inner join mentions on mentions.`BookId` = books.`BookID`\n"
                + "inner join cities on cities.`CityID` = mentions.`CityId`\n"
                + "where cities.latitude-10 < ? and cities.latitude+10 > ? and cities.longitude-10 < ? and cities.longitude+10 > ?;";

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(host, uName, uPass);
            st = con.prepareStatement(query);

            st.setFloat(1, latitude);
            st.setFloat(2, latitude);
            st.setFloat(3, longtitude);
            st.setFloat(4, longtitude);

            rs = st.executeQuery();

            try {
                while (rs.next()) {
                    Book b = new Book();
                    b.setTitle(rs.getString(1));
                    result.add(b);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SqlMapper.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                System.err.println(ex);
            }
        }

        return result;
    }
<<<<<<< Updated upstream

    public static void main(String[] args) {
        //Anonymous
        //Tenterhooks
        //Federal
        SqlMapper s = new SqlMapper();
        System.out.println(s.getAllCitiesByBookTitle("Tenterhooks").size());
        System.out.println(s.getAuthorsByCityName("Federal").size());
        System.out.println(s.getAllBooksWrittenByAuthor("Anonymous").size());
//        System.out.println(s.getBooksMentioningCity(15, 0).size());
    }
=======
    
    public List<Authors> getAll(){
             EntityManagerFactory entityManagerFactory;
              entityManagerFactory = Persistence.createEntityManagerFactory("pu_testDB");
         EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
   
    List<Authors> loadedAuthor = entityManager.createNamedQuery("Authors.findAll", Authors.class).getResultList();

    entityManager.close();
    return loadedAuthor;
    }
    public String getAllBooksWrittenByAuthorWithJPA(String author) {
         EntityManagerFactory entityManagerFactory;
              entityManagerFactory = Persistence.createEntityManagerFactory("pu_testDB");
         EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
     Authors authors = entityManager.createNamedQuery("Authors.findByAuthorName", Authors.class).setParameter("authorName", author).getSingleResult();
     
    entityManager.close();
        return authors.getAuthorName();
    }
    
    
    

    public static void main(String[] args) {
       //Anonymous
      //Tenterhooks
     //Federal
     SqlMapper s = new SqlMapper();
       //System.out.println(s.getAllCitiesByBookTitle("Tenterhooks").size());
       // System.out.println(s.getAuthorsByCityName("Federal").size());
       // System.out.println(s.getAllBooksWrittenByAuthor("Dante Alighieri").size());
       // System.out.println(s.getBooksMentioningCity(15, 0).size());
       
   
       
        //System.out.println(s.getAll().get(0).getAuthorName());
        System.out.println(s.getAllBooksWrittenByAuthorWithJPA("Dante Alighieri"));
   }
>>>>>>> Stashed changes
}
