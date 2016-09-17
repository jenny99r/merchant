package com.insanejamferry.repositories;

import com.insanejamferry.offer.Offer;

import java.util.Optional;

public class MapOfferRepository implements IOfferRepository {
    @Override
    public int create(Offer offer) {
        return 0;
    }

    @Override
    public Optional<Offer> get(int id) {
        return Optional.empty();
    }
}
