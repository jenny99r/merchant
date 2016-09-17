package com.insanejamferry.acceptance;

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

public class MerchantTests {

    @ClassRule
    public static final DropwizardAppRule<Configuration> RULE =
            new DropwizardAppRule<Configuration>(MerchantApplication.class);


    private static final String SERVICE_URL = "http://localhost:8080";
    private CloseableHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = HttpClients.createDefault();
    }

    @After
    public void tearDown() throws IOException {
        httpClient.close();
    }

    @Test
    public void createsAnOfferWithADescription() throws IOException {
        StringEntity jsonEntity = new StringEntity("{\"description\": \"test offer\"}");

        HttpPost post = new HttpPost(SERVICE_URL + "/offer");
        post.addHeader("content-type", "application/json");
        post.setEntity(jsonEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        Assert.assertThat(response.getStatusLine().getStatusCode(), Is.is(HttpStatus.SC_CREATED));
        response.close();
    }
}
