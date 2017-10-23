package com.example.payments;

public class RecurlyGateway implements Gateway {
    public boolean createReoccurringPayment(int paymentMonthlyAmount){
        return true;
    }
}
