package com.example.billing;

import com.example.payments.Gateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private Gateway paymentGateway;

    public Controller(Gateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @PostMapping(value = "/reoccurringPayment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillingResponse> createReoccurringPayment(@RequestBody int amount) {
        if (paymentGateway.createReoccurringPayment(amount)) {
            return new ResponseEntity<>(new BillingResponse(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new BillingResponse().addError("Unable to create reoccurring payment"), HttpStatus.BAD_REQUEST);
        }
    }
}

class BillingResponse {
    private final List<String> errors = new ArrayList();

    public BillingResponse addError(String error) {
        errors.add(error);
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }
}
