package com.example.billing;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class BillingClient {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final RestTemplate restTemplate;
    private final String serviceEndpoint;

    public BillingClient(RestTemplate template, String endpoint) {
        this.restTemplate = template;
        this.serviceEndpoint = endpoint;
    }

    @HystrixCommand(fallbackMethod = "defaultBillUser")
    public void billUser(String userId, int amount) {
        restTemplate.postForEntity(serviceEndpoint + "/reoccurringPayment", amount, String.class);
    }

    public void defaultBillUser(String userId, int amount) {
        logger.info("billUser has failed for userId [{}] with amount [{}]", userId, amount);
    }
}
