package br.com.fabricio.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.fabricio.model.Survey;

@Path("/survey")
public interface SurveyService {

    @POST
    @Path("/{compositeKey}")
    @Consumes("application/json")
    Response create(@Context UriInfo uriInfo, @PathParam("compositeKey") String compositeKey, Survey survey);

    @GET
    @Path("/{compositeKey}")
    @Produces("application/json")
    Response findOne(@PathParam("compositeKey")String compositeKey);

}
