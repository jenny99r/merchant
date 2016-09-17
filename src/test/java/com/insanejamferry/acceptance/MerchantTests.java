package com.insanejamferry.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.insanejamferry.application.MerchantApplication;
import io.dropwizard.Configuration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.core.Is;
import org.junit.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MerchantTests {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<Configuration>(MerchantApplication.class);


    private static final String SERVICE_URL = "http://localhost:8080";
    private CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        httpClient = HttpClients.createDefault();
    }

    @After
    public void tearDown() throws IOException {
        httpClient.close();
    }

    @Test
    public void createsAnOfferWhichCanThenBeFetched() throws IOException {
        final String offerDescription = "test offer";
        final Map prices = new ImmutableMap.Builder<String, String>()
                .put("GBP", "7.67")
                .put("EUR", "2.34")
                .put("USD", "4.56")
                .build();

        String offerUrl = createOffer(offerDescription, prices);

        Offer offer = getOffer(offerUrl);

        assertThat(offer.description, is(offerDescription));

        assertThat(offer.prices.size(), is(3));
        assertThat(offer.prices, IsMapContaining.hasEntry("GBP", "7.67"));
    }

    private Offer getOffer(String offerUrl) throws IOException {
        return objectMapper.readValue(new URL(offerUrl), Offer.class);
    }

    private String createOffer(String offerDescription, Map prices) throws IOException {
        Offer offer = new Offer(offerDescription, prices);
        final StringEntity jsonEntity = new StringEntity(objectMapper.writeValueAsString(offer));

        final HttpPost post = new HttpPost(SERVICE_URL + "/offer");
        post.addHeader("content-type", "application/json");
        post.setEntity(jsonEntity);

        final CloseableHttpResponse response = httpClient.execute(post);

        assertThat(response.getStatusLine().getStatusCode(), is(HttpStatus.SC_CREATED));

        final String createdOfferUrl = response.getHeaders("location")[0].getValue();
        response.close();

        return createdOfferUrl;
    }

    private static class Offer {
        private String description;
        private Map<String, String> prices;

        public Offer() {}

        public Offer(String description, Map<String, String> prices) {
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
}
