package com.example.billing;

import org.springframework.web.client.RestTemplate;

public class Client {

    private final RestTemplate restTemplate;
    private final String serviceEndpoint;

    public Client(String serviceEndpoint) {
        this.restTemplate = new RestTemplate();
        this.serviceEndpoint = serviceEndpoint;
    }

    public void billUser(String userId, int amount) {
        restTemplate.postForEntity(serviceEndpoint + "/reoccurringPayment", amount, String.class);
    }
}
