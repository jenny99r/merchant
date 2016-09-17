package com.insanejamferry.unit.resources;

import com.insanejamferry.offer.Offer;
import com.insanejamferry.repositories.IOfferRepository;
import com.insanejamferry.resources.OfferResource;
import com.insanejamferry.unit.helpers.TestUriInfo;
import org.hamcrest.core.Is;
import org.junit.Test;

import javax.ws.rs.core.*;
import java.util.Optional;

import static org.junit.Assert.assertThat;

public class OfferResourceTests {

    private static final String PATH = "offer";
    private static final Offer EXPECTED_OFFER = new Offer();

    @Test
    public void createReturnsPathToCreatedOffer() {
        OfferResource offerResource = new OfferResource(new TestOfferRepository());

        Response response = offerResource.createOffer(new Offer(), new TestUriInfo(PATH));

        assertThat(response.getLocation().getPath(), Is.is(PATH + "/5"));
    }

    @Test
    public void getReturnsRequestedOffer() {
        OfferResource offerResource = new OfferResource(new TestOfferRepository());

        Response response = offerResource.getOffer(23);

        Offer offer = (Offer) response.getEntity();
        assertThat(offer, Is.is(EXPECTED_OFFER));
    }

    @Test
    public void getReturns404IfRequestedOfferNotFound() {
        OfferResource offerResource = new OfferResource(new TestOfferRepository());

        Response response = offerResource.getOffer(54);

        assertThat(response.getStatus(), Is.is(404));
    }

    private class TestOfferRepository implements IOfferRepository {
        @Override
        public int create(Offer offer) {
            return 5;
        }

        @Override
        public Optional<Offer> get(int id) {
            if (id == 23) {
                return Optional.of(EXPECTED_OFFER);
            } else {
                return Optional.empty();
            }
        }


    }
}
