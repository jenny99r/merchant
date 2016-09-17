package com.insanejamferry.repositories;

import com.insanejamferry.offer.Offer;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapOfferRepositoryTests {

    @Test
    public void returnsEmptyIfNoOfferWithSpecifiedId() {

        MapOfferRepository mapOfferRepository = new MapOfferRepository();

        Optional<Offer> result = mapOfferRepository.get("doesnotexist");

        assertThat(result.isPresent(), is(false));
    }

    @Test
    public void returnsTheOfferIfOfferHasBeenCreated() {
        Offer offer = new Offer("");
        MapOfferRepository mapOfferRepository = new MapOfferRepository();

        String offerId = mapOfferRepository.create(offer);

        Optional<Offer> offerGot = mapOfferRepository.get(offerId);

        assertThat(offerGot.isPresent(), is(true));
        assertThat(offerGot.get(), is(offer));
    }
}
