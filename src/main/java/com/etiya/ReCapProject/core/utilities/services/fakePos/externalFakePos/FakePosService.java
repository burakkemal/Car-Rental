package com.etiya.ReCapProject.core.utilities.services.fakePos.externalFakePos;

import com.etiya.ReCapProject.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;

public interface FakePosService {

    boolean payByCreditCard(CreatePaymentRequest createPaymentRequest);

}
