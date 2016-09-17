package com.insanejamferry.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insanejamferry.application.MerchantApplication;
import io.dropwizard.Configuration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.core.Is;
import org.junit.*;

import java.io.IOException;
import java.net.URL;

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
    public void createsAnOfferWithADescriptionWhichCanThenBeFetched() throws IOException {
        final String offerDescription = "test offer";

        String offerUrl = createOffer(offerDescription);

        Offer offer = getOffer(offerUrl);

        assertThat(offer.description, Is.is(offerDescription));
    }

    private Offer getOffer(String offerUrl) throws IOException {
        return objectMapper.readValue(new URL(offerUrl), Offer.class);
    }

    private String createOffer(String offerDescription) throws IOException {
        final StringEntity jsonEntity = new StringEntity(objectMapper.writeValueAsString(new Offer(offerDescription)));

        final HttpPost post = new HttpPost(SERVICE_URL + "/offer");
        post.addHeader("content-type", "application/json");
        post.setEntity(jsonEntity);

        final CloseableHttpResponse response = httpClient.execute(post);

        assertThat(response.getStatusLine().getStatusCode(), Is.is(HttpStatus.SC_CREATED));

        final String createdOfferUrl = response.getHeaders("location")[0].getValue();
        response.close();

        return createdOfferUrl;
    }

    private static class Offer {
        private String description;

        public Offer() {}

        public Offer(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
