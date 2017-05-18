package Rest;


import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class MainService {

    @GET
    @Produces("application/json")
    public Response index () {

        JSONObject jsonObject = new JSONObject();

        String welcome = "Welcome to the semester project endpoint!";

        jsonObject.put("welcome", welcome);

        return Response.status(200).entity(jsonObject).build();

    }

    @Path("/getAllBooks")
    @GET
    @Produces("application/json")
    public Response anotherEndpoint () {

        JSONObject jsonObject = new JSONObject();

        List books = new ArrayList();
        
        jsonObject.put("books", books);

        return Response.status(200).entity(jsonObject).build();

    }
}
