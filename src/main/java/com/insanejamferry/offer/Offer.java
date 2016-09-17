package com.insanejamferry.offer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Offer {

    private String description;

    @JsonCreator
    public Offer(@JsonProperty("description") String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
