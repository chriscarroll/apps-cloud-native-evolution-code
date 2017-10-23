package com.example.billing;

import com.example.payments.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Gateway paymentGateway;

    @PostMapping("/reoccurringPayment")
    public ResponseEntity<String> createReoccurringPayment(@RequestBody int amount) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response;
        if (paymentGateway.createReocurringPayment(amount)) {
            response = new ResponseEntity<>("{errors: []}", responseHeaders, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("{errors: ['Unable to create reoccurring payment']}", responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
