package com.insanejamferry.offer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Offer {

    private String description;
    private Map<String, String> prices;

    @JsonCreator
    public Offer(@JsonProperty("description") String description,
                 @JsonProperty("prices") Map<String, String> prices) {
        this.description = description;
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getPrices() {
        return prices;
    }
}
