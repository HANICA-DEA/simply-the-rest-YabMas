package nl.han.dea;

import nl.han.dea.services.ItemService;
import nl.han.dea.services.dto.ItemDTO;


import javax.ejb.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/items")
@Singleton
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

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(ItemDTO item){
        itemservice.addItem(item);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

}
