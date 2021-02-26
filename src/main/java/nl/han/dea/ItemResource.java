package nl.han.dea;

import nl.han.dea.services.ItemService;
import nl.han.dea.services.dto.ItemDTO;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/items")
public class ItemResource {

    private final ItemService itemservice;

    public ItemResource() {
        this.itemservice = new ItemService();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItemsAsText() {
        return Response
                .status(Response.Status.OK)
                .entity(itemservice.getAll())
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificItemAsJson(@PathParam("id") int id) {
        return Response
                .status(Response.Status.OK)
                .entity(itemservice.getItem(id))
                .build();
    }

}
