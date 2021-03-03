package nl.han.dea.services.exceptions;

import nl.han.dea.services.dto.ExceptionDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IdAlreadyInUseMapper implements ExceptionMapper<IdAlreadyInUseException> {

    @Override
    public Response toResponse(IdAlreadyInUseException e) {
        return Response
                .status(Response.Status.NOT_IMPLEMENTED)
                .entity(new ExceptionDTO(e.getMessage()))
                .build();
    }
}
