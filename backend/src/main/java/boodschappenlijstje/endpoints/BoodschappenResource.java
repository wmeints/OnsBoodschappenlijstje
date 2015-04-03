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
@Produces("application/json")
public class BoodschappenResource {
    private LijstRepository lijstRepository;

    @Autowired
    public BoodschappenResource(LijstRepository lijstRepository) {
        this.lijstRepository = lijstRepository;
    }

    @GET
    public Response getBoodschappenLijst() {
        return Response.status(200).entity(lijstRepository.findAll()).build();
    }

    @POST
    @Consumes("application/json")
    public Response createItem(CreateItemData data) {
        Lijst insertedItem = lijstRepository.save(new Lijst(data.getItem(), data.getWinkel()));
        return Response.status(200).entity(insertedItem).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateItem(@PathParam("id") int id, UpdateItemData data) {
        Lijst foundItem = lijstRepository.findById(id);

        if(foundItem == null) {
            GenericError error = new GenericError("Could not find the specified item");
            return Response.status(404).entity(error).build();
        }

        foundItem.setDone(data.getDone());
        foundItem.setItem(data.getItem());
        foundItem.setWinkel(data.getWinkel());

        Lijst savedItem = lijstRepository.save(foundItem);

        return Response.status(200).entity(savedItem).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") int id) {
        Lijst foundItem = lijstRepository.findById(id);

        if(foundItem == null) {
            GenericError error = new GenericError("Could not find the specified item");
            return Response.status(404).entity(error).build();
        }

        lijstRepository.remove(foundItem);

        return Response.noContent().build();
    }
}
