package Rest;

import static spark.Spark.get;

import spark.Request;
import spark.Response;
import spark.Route;
import Mappers.SqlMapper;

public class API {
    
    SqlMapper sqlMapper = new SqlMapper();

    public API() {

        get("/books/:city", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                sqlMapper.getAuthorsByCityName(cityName);
                return request.params(":city");
            }
        });
        
        get("/:city", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                
                return request.params(":city");
            }
        });
        
        // more routes
    }

    public static void main(String[] args) {
        new API();
    }
}
