package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.requests.creditCardRequests.DeleteCreditCardRequest;
import com.etiya.ReCapProject.business.requests.creditCardRequests.UpdateCreditCardRequest;
import com.etiya.ReCapProject.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface CreditCardService {
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	Result add(CreateCreditCardRequest createCreditCardRequest);
}
