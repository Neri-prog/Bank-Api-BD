package br.com.neri.rest;

import br.com.neri.persistence.dto.UserDto;
import br.com.neri.persistence.model.User;
import br.com.neri.services.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Path("/v1/users")
public class UserController {

    @Inject
    UserService service;

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) throws Exception {
        User user = this.service.getUser(id).orElseThrow(() -> new Exception(String.format("Usuario com codigo %s nao encontrado ", id.toString())));
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    public Response listUser()
    {
        List<User> user = this.service.getUsers();
        return Response.status(Response.Status.OK).entity(user).build();
    }
    @POST
    public Response addUser(@Valid UserDto userData) throws InvocationTargetException, IllegalAccessException {
        this.service.addUser(userData);
        return Response.status(Response.Status.CREATED).entity("Ok").build();
    }
    @PATCH
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, @Valid UserDto userData) throws Exception {
        User user = this.service.updateUser(id,userData);
        return Response.status(Response.Status.OK).entity(user).build();

    }
    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) throws Exception {
        this.service.deleteUser(id);
        return Response.status(Response.Status.OK).build();
    }



}