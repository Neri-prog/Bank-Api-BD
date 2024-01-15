package br.com.neri.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class AccountNotFoundException extends WebApplicationException {
    public AccountNotFoundException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(message).build());
    }
}
