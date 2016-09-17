package com.insanejamferry.repositories;

import com.insanejamferry.offer.Offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapOfferRepository implements IOfferRepository {

    private final Map<String, Offer> offers = new HashMap<String, Offer>();

    @Override
    public String create(Offer offer) {
        String offerId = java.util.UUID.randomUUID().toString();

        offers.put(offerId, offer);
        return offerId;
    }

    @Override
    public Optional<Offer> get(String id) {
        if (offers.containsKey(id)) {
            return Optional.of(offers.get(id));
        }
        return Optional.empty();
    }
}
