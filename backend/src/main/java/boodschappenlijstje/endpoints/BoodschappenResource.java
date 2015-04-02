package boodschappenlijstje.endpoints;

import boodschappenlijstje.entity.Lijst;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

import boodschappenlijstje.entity.LijstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/item")
public class BoodschappenResource {

    @Autowired
    private LijstRepository lijstrepository;

    @GET
    public Response getBoodschappenLijst() {
        return Response.status(200).entity(lijstrepository.findAll()).build();
    }

    @POST
    public Response createItem(CreateItemData data) {
        Lijst insertedItem = lijstrepository.save(new Lijst(data.getItem(), data.getWinkel()));
        return Response.status(200).entity(insertedItem).build();
    }

    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") int id) {
        return Response.status(200).entity(lijstrepository.save(lijstrepository.findById(id))).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") int id) {
        lijstrepository.remove(lijstrepository.findById(id));
        return Response.noContent().build();
    }
}
