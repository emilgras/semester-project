package Mappers;

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

public class SqlMapper implements SqlMapperInterface {
    String host = "jdbc:mysql://localhost:3306/books?zeroDateTimeBehavior=convertToNull&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String uName = "root";
    String uPass = "frederik2000";

    public ResultSet getAllCitiesByBookTitle(String bookTitle) {
        String query = "cities.latitude,cities.longitude from cities\n"
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

        return rs;
    }

    public ResultSet getAuthorsByCityName(String cityName) {
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
        
        return rs;
    }

    public ResultSet getAllBooksWrittenByAuthor(String author) {
        String query = "select books.`Title`, cities.`CityName`,cities.latitude,cities.longitude from books\n"
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
        
        return rs;
    }

    public ResultSet getBooksMentioningCity(float latitude, float longtitude, int vicinity) {
        String query = "select books.`Title` from books\n"
                + "inner join mentions on mentions.`BookId` = books.`BookID`\n"
                + "inner join cities on cities.`CityID` = mentions.`CityId`\n"
                + "where cities.latitude-? < ? and cities.latitude+? > ? and cities.longitude-? < ? and cities.longitude+? > ?;";

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(host, uName, uPass);
            st = con.prepareStatement(query);
            st.setInt(1, vicinity);
            st.setInt(3, vicinity);
            st.setInt(5, vicinity);
            st.setInt(7, vicinity);
            
            st.setFloat(2, latitude);
            st.setFloat(4, latitude);
            
            st.setFloat(6, longtitude);
            st.setFloat(8, longtitude);
            
            rs = st.executeQuery();

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
        
        return rs;
    }
}
