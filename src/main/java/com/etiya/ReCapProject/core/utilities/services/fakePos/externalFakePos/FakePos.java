package com.etiya.ReCapProject.core.utilities.services.fakePos.externalFakePos;

import com.etiya.ReCapProject.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;

public class FakePos {

    public boolean payByCreditCard(String cardNumber, String CardName, String cvv, String expirationDate, double price) {
        if (price<=1000){
            return true;
        }
        return false;
    }
}
