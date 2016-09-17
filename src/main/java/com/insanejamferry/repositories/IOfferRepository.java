package com.insanejamferry.repositories;

import com.insanejamferry.offer.Offer;

import java.util.Optional;

public interface IOfferRepository {

    int create(Offer offer);
    Optional<Offer> get(int id);
}
