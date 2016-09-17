package com.insanejamferry.application;

import com.insanejamferry.resources.OfferResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class MerchantApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new MerchantApplication().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(new OfferResource());
    }
}
