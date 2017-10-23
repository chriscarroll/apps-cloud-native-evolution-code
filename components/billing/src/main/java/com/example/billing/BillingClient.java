package com.example.billing;

import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private final RestTemplate restTemplate;
    private final String serviceEndpoint;

    public BillingClient(RestTemplate template, String endpoint) {
        this.restTemplate = template;
        this.serviceEndpoint = endpoint;
    }

    public void billUser(String userId, int amount) {
        restTemplate.postForEntity(serviceEndpoint + "/reoccurringPayment", amount, String.class);
    }
}
