package com.insanejamferry.resources;

import com.insanejamferry.offer.Offer;
import com.insanejamferry.repositories.IOfferRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Optional;

import static java.lang.String.valueOf;

@Path("/offer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OfferResource {

    private IOfferRepository offerRepository;

    public OfferResource(IOfferRepository offerRepository) {

        this.offerRepository = offerRepository;
    }

    @POST
    public Response createOffer(Offer offer,
                                @Context UriInfo uriInfo) {
        int offerId = offerRepository.create(offer);
        URI createdOfferUri = uriInfo.getAbsolutePathBuilder().path(valueOf(offerId)).build();
        return Response.created(createdOfferUri).build();
    }

    @GET
    @Path("/{id}")
    public Response getOffer(@PathParam("id") int id) {
        Optional<Offer> offer = offerRepository.get(id);
        if (!offer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(offer.get()).build();
    }
}
