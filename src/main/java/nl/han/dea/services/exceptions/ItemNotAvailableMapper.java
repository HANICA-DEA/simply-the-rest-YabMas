package nl.han.dea.services.exceptions;

import nl.han.dea.services.dto.ExceptionDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ItemNotAvailableMapper implements ExceptionMapper<ItemNotAvailableException> {

    @Override
    public Response toResponse(ItemNotAvailableException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ExceptionDTO(e.getMessage()))
                .build();
    }
}
