package Rest;

import Mappers.SqlMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;

import static spark.Spark.*;

public class API {
    
    SqlMapper sqlMapper = new SqlMapper();

    public API() {
//
//        get("/:city", new Route() {
//            @Override
//            public Object handle(Request request, Response response) {
//
//                return request.params(":city");
//            }
//        });
//
//        get("/:city", new Route() {
//            @Override
//            public Object handle(Request request, Response response) {
//
//                return request.params(":city");
//            }
//        });

            // 1: Return a list of books with corresponding authors which mentions a given city.
            get("/getBooksByCity/:city", (request, response) -> {

                String city = request.params(":city");
                
                JSONObject jsonObject = new JSONObject();
                
                
                jsonObject.put("city", city);

                return jsonObject;
            });

//            2: Return list of coordinates (lat,lng) of cities mentioned in a given book title
            get("/getCordsByBook/:bookTitle", (request, response) -> {

                String bookTitle = request.params(":bookTitle");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("bookTitle", bookTitle);

                return jsonObject;
            });

            // 3: Return a list of all books written by author, and a list of coordinates for all cities
            // that the author mention in his books.
            get("/getCordsByAuthor/:author", (request, response) -> {

                String author = request.params(":author");

                // Json object containing a list of all books, and a list of all coordinates
                JSONObject jsonObject = new JSONObject();

                // Fill this array with a list of all books
                JSONArray books = new JSONArray();

                // Fill this array with a list of all coordinates for cities mentioned in books by the author
                JSONArray coordinates = new JSONArray();

                jsonObject.put("books", books);
                jsonObject.put("coordinates", coordinates);

                return jsonObject;
            });

            // 4: Return a list of books mentioning a city in a vicinity of a given geolocation (lat,lng)
            get("getBooksByGeo/:lat/:lng", (request, response) -> {

                int lat = Integer.parseInt(request.params(":lat"));
                int lng = Integer.parseInt(request.params(":lng"));

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("lat", lat);
                jsonObject.put("lng", lng);

                return jsonObject;
            });
        // more routes
    }

    public static void main(String[] args) {
        new API();
    }

    // if not found, return status 400
    public Response entityNotFound () {

        String errorMessage = "";

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("errorMessage", errorMessage);
        return Response.status(400).entity(jsonObject).build();
    }

}
