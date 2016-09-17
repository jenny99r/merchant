package com.insanejamferry.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/offer")
@Consumes(MediaType.APPLICATION_JSON)
public class OfferResource {

    @POST
    public Response createOffer() {
        return Response.status(Response.Status.CREATED).build();
    }
}
