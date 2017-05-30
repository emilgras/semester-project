package Rest;

import Facades.UserFacade;
import Facades.UserFacadeInterface;
import Mappers.GraphMapper;
import Mappers.MapperInterface;
import Mappers.SqlMapper;
import NewEntities.Author;
import NewEntities.Book;
import NewEntities.City;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

public class API {

    SqlMapper sqlMapper = new SqlMapper();

    
    private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();
    
    static {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        corsHeaders.put("Access-Control-Allow-Origin", "*");
        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        corsHeaders.put("Access-Control-Allow-Credentials", "true");
    }

    public final static void apply() {
        Filter filter = new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                corsHeaders.forEach((key, value) -> {
                    response.header(key, value);
                });
            }
        };
        Spark.after(filter);
    }
    
    public API() {

        
        // 1: Return a list of books with corresponding authors which mentions a given city.
        get("/getBooksByCity/:city", (request, response) -> {

            String city = request.params(":city");

            JSONObject jsonObject = new JSONObject();

            ArrayList<Book> books = sqlMapper.getAuthorsByCityName(city);
            JSONArray jsonArray = new JSONArray();
            for (Book b : books) {
                JSONObject bookObject = new JSONObject();
                JSONArray authorArray = new JSONArray();
                for (Author a : b.getAuthors()) {
                    authorArray.put(a.getName());
                }
                bookObject.put("authors", authorArray);
                bookObject.put("title", b.getTitle());
                jsonArray.put(bookObject);
            }
            jsonObject.put("books", jsonArray);

            return jsonObject;
        });

//            2: Return list of coordinates (lat,lng) of cities mentioned in a given book title
        get("/getCordsByBook/:bookTitle", (request, response) -> {

            String bookTitle = request.params(":bookTitle");
            ArrayList<City> cities = sqlMapper.getAllCitiesByBookTitle(bookTitle);
            JSONArray jsonArray = new JSONArray();
            for (City c : cities) {
                JSONObject city = new JSONObject();
                city.put("lat", c.getLatitude());
                city.put("lng", c.getLongtitude());
                jsonArray.put(city);
            }
            JSONObject jsonObject = new JSONObject();

            

            return jsonArray;
        });

        // 3: Return a list of all books written by author, and a list of coordinates for all cities
        // that the author mention in his books.
        get("/getCordsByAuthor/:author", (request, response) -> {

            String author = request.params(":author");

            // Json object containing a list of all books, and a list of all coordinates
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            ArrayList<Book> books = sqlMapper.getAllBooksWrittenByAuthor(author);
            for (Book b : books) {
                JSONObject book = new JSONObject();
                JSONArray cities = new JSONArray();
                for (City c : b.getCities()) {
                    JSONObject city = new JSONObject();
                    city.put("lat", c.getLatitude());
                    city.put("lng", c.getLongtitude());
                    cities.put(city);
                }
                book.put("book", b.getTitle());
                book.put("cities", cities);
                jsonArray.put(book);
            }
            jsonObject.put("books", jsonArray);

            return jsonObject;
        });

        // 4: Return a list of books mentioning a city in a vicinity of a given geolocation (lat,lng)
        get("getBooksByGeo/:lat/:lng", (request, response) -> {

            float lat = Integer.parseInt(request.params(":lat"));
            float lng = Integer.parseInt(request.params(":lng"));

            JSONObject jsonObject = new JSONObject();
            ArrayList<Book> books = sqlMapper.getBooksByGeoLocation(lat, lng);
            JSONArray jsonArray = new JSONArray();
            for (Book b : books) {
                jsonArray.put(b.getTitle());
            }
            jsonObject.put("books", jsonArray);

            return jsonObject;
        });
        // more routes
    }

    public static void main(String[] args) {
        apply();
        new API();
    }

}
