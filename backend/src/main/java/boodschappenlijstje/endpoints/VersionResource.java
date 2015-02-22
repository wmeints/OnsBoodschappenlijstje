package boodschappenlijstje.endpoints;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Provides version information for the app
 */
@Component
@Path("/version")
public class VersionResource {
    @GET
    public Response get(){
        return Response.status(200).entity("1.0").build();
    }
}
