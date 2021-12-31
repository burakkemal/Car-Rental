package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.PaymentSearchListDto;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface PaymentService {
	DataResult<List<PaymentSearchListDto>> getAll();
	Result add(CreatePaymentRequest createPaymentRequest);
	Result delete(DeletePaymentRequest deletePaymentRequest);
	Result update(UpdatePaymentRequest updatePaymentRequest);
	
}
