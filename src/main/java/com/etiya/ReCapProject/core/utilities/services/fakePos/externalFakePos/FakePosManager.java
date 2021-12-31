package com.etiya.ReCapProject.core.utilities.services.fakePos.externalFakePos;

import com.etiya.ReCapProject.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FakePosManager implements FakePosService {

    private FakePos fakePos;

    @Autowired
    public FakePosManager(FakePos fakePos) {
        this.fakePos = fakePos;
    }
    @Override
    public boolean payByCreditCard(CreatePaymentRequest createPaymentRequest) {
        String cardNumber=createPaymentRequest.getCardNumber();
        String cardName=createPaymentRequest.getCardName();
        String cvv=createPaymentRequest.getCvv();
        String expirationDate=createPaymentRequest.getExpirationDate();
        return this.fakePos.payByCreditCard(cardNumber,cardName,cvv,expirationDate,createPaymentRequest.getPrice());
    }
}
