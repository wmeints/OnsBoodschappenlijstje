package boodschappenlijstje.endpoints;

import entity.Lijst;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import entity.LijstRepository;
import javax.ws.rs.*;

@Path("/item")
public class BootschappenResource {

    LijstRepository lijstrepository = new LijstRepository();

    @GET
    public Response getBoodschappenLijst() {
        return Response.status(200).entity(lijstrepository.getItems()).build();
    }

    @POST
    public Response createItem(CreateItemData data) {
        Lijst insertedItem = lijstrepository.CreateOrChangeItem(new Lijst(data.getItem(), data.getWinkel()));
        return Response.status(200).entity(insertedItem).build();
    }

    @PUT
    @Path("{id}")
    public Response updateItem(@PathParam("id") int id) {
        return Response.status(200).entity(lijstrepository.CreateOrChangeItem(lijstrepository.findByID(id))).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") int id) {
        lijstrepository.delete(lijstrepository.findByID(id));
        return Response.noContent().build();
    }  

}
