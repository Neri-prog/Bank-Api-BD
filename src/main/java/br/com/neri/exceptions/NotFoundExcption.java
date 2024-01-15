package br.com.neri.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class NotFoundExcption extends WebApplicationException {
    public NotFoundExcption(String message) {
        super(Response.status(Response.Status.NOT_FOUND).entity(message).build());
    }
}
