package br.com.fabricio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.fabricio.model.User;

@Path("/user")
public interface UserService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response create(@Context UriInfo uriInfo, User user) throws Exception;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findOne(@PathParam("id") int idUser);
    
    @PUT
    @Path("/id")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(User user);
 
}
