package com.insanejamferry.repositories;

import com.insanejamferry.offer.Offer;

import java.util.Optional;

public class MapOfferRepository implements IOfferRepository {
    @Override
    public String create(Offer offer) {
        return "0";
    }

    @Override
    public Optional<Offer> get(String id) {
        return Optional.empty();
    }
}
