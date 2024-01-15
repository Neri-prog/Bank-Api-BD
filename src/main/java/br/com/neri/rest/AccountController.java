package br.com.neri.rest;

import br.com.neri.persistence.dto.AccountDto;
import br.com.neri.persistence.model.Account;
import br.com.neri.services.AccountService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

@Path("/v1/accounts")
public class AccountController {

    @Inject
    AccountService service;

    @GET
    @Path("/{id}")
    public Response getAccount(@PathParam("id") Long id) throws Exception {
        Account account = this.service.getAccount(id).orElseThrow(() -> new Exception(String.format("Conta com codigo %s nao encontrado ", id.toString())));
        return Response.status(Response.Status.OK).entity(account).build();
    }

    @GET
    public Response listAccount()
    {
        List<Account> accounts = this.service.getAccounts();
        return Response.status(Response.Status.OK).entity(accounts).build();
    }
    @POST
    public Response addAccount (@Valid AccountDto accountData, @RestQuery Long userId)
    {
        this.service.addAccount(accountData, userId);
        return Response.status(Response.Status.CREATED).entity("Ok").build();
    }
    @PATCH
    @Path("/{id}/deposit")
    public Response depositAccount(@Valid AccountDto accountData, @PathParam("id") Long id) throws Exception {
        Account account = this.service.depositAccount(id, accountData.getValue());
        return Response.status(Response.Status.OK).entity(account).build();

    }

    @PATCH
    @Path("/{id}/withdraw")
    public Response withdrawAccount(@Valid AccountDto accountData, @PathParam("id") Long id) throws Exception {
        Account account = this.service.withdrawAccount(id, accountData.getValue());
        return Response.status(Response.Status.OK).entity(account).build();

    }
    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") Long id) throws Exception {
        this.service.deleteAccount(id);
        return Response.status(Response.Status.OK).build();
    }



}