package com.insanejamferry.repositories;

import com.insanejamferry.offer.Offer;

import java.util.Optional;

public interface IOfferRepository {

    String create(Offer offer);
    Optional<Offer> get(String id);
}
