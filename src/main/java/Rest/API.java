package Rest;

import Mappers.SqlMapper;
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

        path("/api/*", () -> {
            before("/*", (q, a) -> System.out.println("Received Api call"));

            // Return a list of books with corresponding authors which mentions a given city.
            get("/:city", (request, response) -> {

                String city = request.params(":city");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("city", city);

                return Response.status(200).entity(jsonObject).build();
            });

            // Return list of coordinates (lat,lng) of cities mentioned in a given book title
            get("/:bookTitle", (request, response) -> {

                String bookTitle = request.params(":bookTitle");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("bookTitle", bookTitle);

                return Response.status(200).entity(jsonObject).build();
            });

            // Return a list of all books mentioned by author, and a list of coordinates for all cities
            // that the author mention in his books.
            get("/:author", (request, response) -> {

                String author = request.params(":author");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("author", author);

                return Response.status(200).entity(jsonObject).build();
            });

            // Return a list of books mentioning a city in a vicinity of a given geolocation (lat,lng)
            get("/:lat/:lng", (request, response) -> {

                int lat = Integer.parseInt(request.params(":lat"));
                int lng = Integer.parseInt(request.params(":lng"));

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("lat", lat);
                jsonObject.put("lng", lng);

                return Response.status(200).entity(jsonObject).build();
            });
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
